package com.stfn2.ggas.services;

import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.core.models.DadosAuditoria;
import com.stfn2.ggas.core.utils.Log;
import com.stfn2.ggas.domain.*;
import com.stfn2.ggas.domain.dtos.ArrecadadorCarteiraCobrancaDTO;
import com.stfn2.ggas.domain.dtos.ArrecadadorContratoConvenioDTO;
import com.stfn2.ggas.domain.dtos.ArrecadadorContratoDTO;
import com.stfn2.ggas.domain.dtos.ArrecadadorDTO;
import com.stfn2.ggas.domain.dtos.basic.ArrecadadorBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.ArrecadadorFilterDTO;
import com.stfn2.ggas.domain.enumTypes.CodigoCarteiraCobrancaEnum;
import com.stfn2.ggas.domain.enumTypes.TipoCarteiraCobrancaEnum;
import com.stfn2.ggas.domain.enumTypes.TipoConvenioBancarioEnum;
import com.stfn2.ggas.repositories.ArrecadadorRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class ArrecadadorService extends BaseService<Arrecadador, ArrecadadorDTO, ArrecadadorBasicDTO, ArrecadadorFilterDTO, ArrecadadorRepository> {

    @Autowired
    protected ArrecadadorContratoService arrecadadorContratoService;

    @Autowired
    protected ArrecadadorCarteiraCobrancaService arrecadadorCarteiraCobrancaService;

    @Autowired
    protected ArrecadadorContratoConvenioService arrecadadorContratoConvenioService;

    @Autowired
    protected ClienteService clienteService;

    @Autowired
    protected BancoService bancoService;

    @Autowired
    protected DocumentoTipoService documentoTipoService;

    @Autowired
    protected DocumentoLayoutService documentoLayoutService;

    private Log log = new Log(this.getClass());

    private boolean isAlterar;
    private String descricaoCliente;
    private String descricaoBanco;
    private final LocalDate data = LocalDate.now();
    
    private static final Long BANCO_ID = 128L;
    private static final int NUMERO_CARTEIRA = 109;
    private static final Long NUMERO_CHAVE_CONTA = 7L;
    private static final Long SEQUENCIA_COBRANCA_GERADO = 1L;
    private static final Long SEQUENCIA_COBRANCA_INICIO = 1L;
    private static final Long SEQUENCIA_COBRANCA_FIM = 99999999999999L;
    private static final Long LAYOUT = 13L;
    private static final String COB_BANCARIA_MOVIMENTO = " cobBancariaMovimento ";

    private static final String CHAVE_PRIMARIA = "chavePrimaria";
    private static final String CLIENTE_DEBITO_AUTOMATICO = " clienteDebitoAutomatico ";
    private static final String ARRECADADOR_CONTRATO_CONVENIO = " arrecadadorContratoConvenio ";
    private static final String SEPARATOR = File.separator;
    private static final String ENCODING_ISO_8859_1 = "ISO-8859-1";
    private static final String FILTRO_POR_CLIENTE = "cliente";
    private static final String FILTRO_POR_IMOVEL = "imovel";
    private static final String FILTRO_POR_CONTRATO = "contrato";
    private static final int PERCENTUAL = 100;
    private static final int DIVISOR = 6;
    private static final int DUAS_CASAS_DECIMAIS = 2;
    private static final int DIVISOR_DIAS = 6;
    private static final String PREFIXO_ARQUIVO_REMESSA_DEBITO_AUTOMATICO = "DA";
    private static final String PREFIXO_ARQUIVO_DEBITO_AUTOMATICO_NAO_PROCESSADO = "NP";
    private static final String DIRETORIO_ARQUIVOS_REMESSA = "DIRETORIO_ARQUIVOS_REMESSA";
    private static final String SUFIXO_ARQUIVO_REMESSA = "txt";
    private static final String ACCONVENIO = " acConvenio ";
    private static final String CHAVE_ARRECADADOR = "CHAVE_ARRECADADOR";
    private static final String DEBITO_AUTOMATICO = " debitoAutomatico ";
    private static final String DEBITO_AUTOMATICO_MOVIMENTO = " debitoAutomaticoMovimento ";
    private static final String CHAVE_ARRECADADOR_UM = "chaveArrecadador";
    private static final String CODIGO_BANCO_DAYCOVAL = "707";
    private static final String SIGLA_DAYCOVAL = "WMJ";
    private static final String ERRO_NEGOCIO_CONVENIO_SEM_NOSSO_NUMERO = "ERRO_NEGOCIO_CONVENIO_SEM_NOSSO_NUMERO";
    private static final String ERRO_NEGOCIO_INSERIR_MOVIMENTO_OCORRENCIA_ENVIO = "ERRO_NEGOCIO_INSERIR_MOVIMENTO_OCORRENCIA_ENVIO";

    private Map<Long, DocumentoTipo> tipoDocumentoCache = new HashMap<>();

    @Override
    public Page<ArrecadadorBasicDTO> findAll(ArrecadadorFilterDTO filter, Pageable pageable) {
        String cliente = filter.getCliente();
        String banco = filter.getBanco();
        String codigoAgente = filter.getCodigoAgente();
        Boolean habilitado = filter.getHabilitado();
        Page<ArrecadadorBasicDTO> result = this.repository.findAll(cliente, banco, codigoAgente, habilitado, pageable).map(projection -> MapperImpl.parseObject(projection, ArrecadadorBasicDTO.class));
        return result;
    }

//    @Override
//    public ArrecadadorBasicDTO entityToBasicDTO(Arrecadador entity) {
//        ArrecadadorBasicDTO dto = super.entityToBasicDTO(entity);
//        if(entity.getBanco() != null) {
//            dto.setBancoNome(entity.getBanco().getNome());
//        }
//        if(entity.getCliente() != null) {
//            dto.setClienteNome(entity.getCliente().getNome());
//        }
//        return dto;
//    }

    @Override
    public ArrecadadorDTO createOrUpdate(ArrecadadorDTO dto) {
        Arrecadador arrecadador = getById(dto.getId());
        if(arrecadador == null){
            arrecadador = new Arrecadador();

            Banco banco = bancoService.getById(dto.getBancoId());
            arrecadador.setBanco(banco);

            Cliente cliente = clienteService.getById(dto.getClienteId());
            arrecadador.setCliente(cliente);

            arrecadador.setCodigoAgente(dto.getCodigoAgente());
            arrecadador = super.save(arrecadador);

            ArrecadadorContrato arrecadadorContrato = new ArrecadadorContrato();
            arrecadadorContrato.setArrecadador(arrecadador);
            arrecadadorContrato.setNumeroContrato(dto.getCodigoAgente());
            arrecadadorContrato.setDataInicioContrato(data);
            arrecadadorContratoService.save(arrecadadorContrato);
//            arrecadadorContratoService.createOrUpdate(arrecadadorContratoService.entityToDTO(arrecadadorContrato));
//
            ArrecadadorCarteiraCobranca arrecadadorCarteiraCobranca = getArrecadadorCarteiraCobranca(arrecadador);
            arrecadadorCarteiraCobrancaService.save(arrecadadorCarteiraCobranca);
            arrecadadorCarteiraCobrancaService.createOrUpdate(arrecadadorCarteiraCobrancaService.entityToDTO(arrecadadorCarteiraCobranca));
//
            ArrecadadorContratoConvenio arrecadadorContratoConvenio = getArrecadadorContratoConvenio(arrecadadorContrato, arrecadadorCarteiraCobranca );
            arrecadadorContratoConvenioService.save(arrecadadorContratoConvenio);
            arrecadadorContratoConvenioService.createOrUpdate(arrecadadorContratoConvenioService.entityToDTO(arrecadadorContratoConvenio));

            return entityToDTO(arrecadador);
        }else{
            Banco banco = bancoService.getById(dto.getBancoId());
            arrecadador.setBanco(banco);

            Cliente cliente = clienteService.getById(dto.getClienteId());
            arrecadador.setCliente(cliente);

            arrecadador.setCodigoAgente(dto.getCodigoAgente());

            ArrecadadorContrato arrecadadorContrato = this.repository.obterArrecadadorContratoPorArrecadador(dto.getId());
            if(arrecadadorContrato != null) {
                ArrecadadorContratoDTO arrecadadorContratoDTO = new ArrecadadorContratoDTO();
                arrecadadorContratoDTO.setId(arrecadadorContrato.getId());
                arrecadadorContratoDTO.setArrecadador(arrecadador);
                arrecadadorContratoDTO.setNumeroContrato(dto.getCodigoAgente());
                arrecadadorContratoDTO.setDataInicioContrato(arrecadadorContrato.getDataInicioContrato());
                arrecadadorContratoService.createOrUpdate(arrecadadorContratoDTO);

                ArrecadadorCarteiraCobranca arrecadadorCarteiraCobranca = this.repository.obterArrecadadorCarteiraCobrancaPorArrecadador(dto.getId());

                ArrecadadorCarteiraCobrancaDTO arrecadadorCarteiraCobrancaDTO = new ArrecadadorCarteiraCobrancaDTO();
                arrecadadorCarteiraCobrancaDTO.setId(arrecadadorCarteiraCobranca.getId());
                arrecadadorCarteiraCobrancaDTO.setArrecadador(arrecadador);
                arrecadadorCarteiraCobrancaDTO.setTipoCarteira(TipoCarteiraCobrancaEnum.ESCRITURAL);
                arrecadadorCarteiraCobrancaDTO.setCodigoCarteira(CodigoCarteiraCobrancaEnum.E);
                arrecadadorCarteiraCobrancaDTO.setDescricao(cliente.getDescricao() + " " + banco.getNome());
                arrecadadorCarteiraCobrancaDTO.setNumero(NUMERO_CARTEIRA);
                arrecadadorCarteiraCobrancaDTO.setIndicadorFaixaNossoNumero(true);
                arrecadadorCarteiraCobrancaDTO.setIndicadorNossoNumeroLivre(true);
                arrecadadorCarteiraCobrancaDTO.setIndicadorEmissao(false);
                arrecadadorCarteiraCobrancaDTO.setIndicadorProtesto(false);
                arrecadadorCarteiraCobrancaDTO.setIndicadorEntrega(false);
                arrecadadorCarteiraCobrancaService.createOrUpdate(arrecadadorCarteiraCobrancaDTO);

                ContaBancaria contaConvenio = new ContaBancaria();
                Long contaConvenioPadrao = 7L;
                contaConvenio.setId(contaConvenioPadrao);

                ContaBancaria contaCredito = new ContaBancaria();
                Long contaCreditoPadrao = 7L;
                contaCredito.setId(contaCreditoPadrao);

                DocumentoLayout documentoLayout = new DocumentoLayout();
                Long leiautePadrao = LAYOUT;
                documentoLayout.setId(leiautePadrao);

                Long numeroRemessaRetornoPadrao = 1L;
                Boolean indicadorPadrao = false;

                ArrecadadorContratoConvenio arrecadadorContratoConvenio = this.repository.obterArrecadadorContratoConvenio(arrecadadorContrato.getId());

                ArrecadadorContratoConvenioDTO arrecadadorContratoConvenioDTO = new ArrecadadorContratoConvenioDTO();
                arrecadadorContratoConvenioDTO.setId(arrecadadorContratoConvenio.getId());
                arrecadadorContratoConvenioDTO.setArrecadadorContrato(arrecadadorContrato);
                arrecadadorContratoConvenioDTO.setArrecadadorCarteiraCobranca(arrecadadorCarteiraCobranca);
                arrecadadorContratoConvenioDTO.setTipoConvenio(TipoConvenioBancarioEnum.BOLETO);
                arrecadadorContratoConvenioDTO.setCodigoConvenio(arrecadadorContrato.getNumeroContrato());
                arrecadadorContratoConvenioDTO.setContaConvenio(contaConvenio);
                arrecadadorContratoConvenioDTO.setContaCredito(contaCredito);
                arrecadadorContratoConvenioDTO.setNsaRemessa(numeroRemessaRetornoPadrao);
                arrecadadorContratoConvenioDTO.setNsaRetorno(numeroRemessaRetornoPadrao);
                arrecadadorContratoConvenioDTO.setSequencialCobrancaFim(SEQUENCIA_COBRANCA_FIM);
                arrecadadorContratoConvenioDTO.setSequencialCobrancaInicio(SEQUENCIA_COBRANCA_INICIO);
                arrecadadorContratoConvenioDTO.setIndicadorPadrao(indicadorPadrao);
                arrecadadorContratoConvenioDTO.setLeiaute(documentoLayout);

                arrecadadorContratoConvenioService.createOrUpdate(arrecadadorContratoConvenioDTO);
            }
        }
        return entityToDTO(arrecadador);
    }
    @Override
    public ArrecadadorDTO entityToDTO(Arrecadador entity) {
        ArrecadadorDTO dto = super.entityToDTO(entity);
        dto.setId(entity.getId());
        if(entity.getBanco() != null){
            dto.setBancoId(entity.getBanco().getId());

        }
        if(entity.getCliente() != null) {
            dto.setClienteId(entity.getCliente().getId());

        }
        return dto;
    }

    private @NotNull ArrecadadorCarteiraCobranca getArrecadadorCarteiraCobranca(Arrecadador entity) {

        Cliente cliente = clienteService.getById(entity.getCliente().getId());
        descricaoCliente = cliente.getNomeFantasia();

        Banco banco = bancoService.getById(entity.getBanco().getId());
        descricaoBanco = banco.getNome();

        ArrecadadorCarteiraCobranca arrecadadorCarteiraCobranca = new ArrecadadorCarteiraCobranca();
        arrecadadorCarteiraCobranca.setArrecadador(entity);
        arrecadadorCarteiraCobranca.setTipoCarteira(TipoCarteiraCobrancaEnum.ESCRITURAL);
        arrecadadorCarteiraCobranca.setCodigoCarteira(CodigoCarteiraCobrancaEnum.E);
        arrecadadorCarteiraCobranca.setDescricao(descricaoCliente + " " + descricaoBanco);
        arrecadadorCarteiraCobranca.setNumero(NUMERO_CARTEIRA);
        arrecadadorCarteiraCobranca.setIndicadorFaixaNossoNumero(true);
        arrecadadorCarteiraCobranca.setIndicadorNossoNumeroLivre(true);
        arrecadadorCarteiraCobranca.setIndicadorEmissao(false);
        arrecadadorCarteiraCobranca.setIndicadorProtesto(false);
        arrecadadorCarteiraCobranca.setIndicadorEntrega(false);
        return arrecadadorCarteiraCobranca;
    }

    private @NotNull ArrecadadorContratoConvenio getArrecadadorContratoConvenio(ArrecadadorContrato arrecadadorContrato, ArrecadadorCarteiraCobranca arrecadadorCarteiraCobranca) {

        ContaBancaria contaConvenio = new ContaBancaria();
        Long contaConvenioPadrao = 7L;
        contaConvenio.setId(contaConvenioPadrao);

        ContaBancaria contaCredito = new ContaBancaria();
        Long contaCreditoPadrao = 7L;
        contaCredito.setId(contaCreditoPadrao);

        DocumentoLayout documentoLayout = new DocumentoLayout();
        Long leiautePadrao = LAYOUT;
        documentoLayout.setId(leiautePadrao);

        Long sequencialCobrancaFim = SEQUENCIA_COBRANCA_FIM;
        Long sequencialCobrancaInicio = SEQUENCIA_COBRANCA_INICIO;
        Long sequenciaCobrancaGerado = SEQUENCIA_COBRANCA_GERADO;
        Long numeroRemessaRetornoPadrao = 1L;
        Boolean indicadorPadrao = false;

        ArrecadadorContratoConvenio arrecadadorContratoConvenio = new ArrecadadorContratoConvenio();
        arrecadadorContratoConvenio.setArrecadadorContrato(arrecadadorContrato);
        arrecadadorContratoConvenio.setArrecadadorCarteiraCobranca(arrecadadorCarteiraCobranca);
        arrecadadorContratoConvenio.setTipoConvenio(TipoConvenioBancarioEnum.BOLETO);
        arrecadadorContratoConvenio.setCodigoConvenio(arrecadadorContrato.getNumeroContrato());
        arrecadadorContratoConvenio.setContaConvenio(contaConvenio);
        arrecadadorContratoConvenio.setContaCredito(contaCredito);
        arrecadadorContratoConvenio.setNsaRemessa(numeroRemessaRetornoPadrao);
        arrecadadorContratoConvenio.setNsaRetorno(numeroRemessaRetornoPadrao);
        arrecadadorContratoConvenio.setSequencialCobrancaFim(sequencialCobrancaFim);
        arrecadadorContratoConvenio.setSequencialCobrancaInicio(sequencialCobrancaInicio);
        arrecadadorContratoConvenio.setSequenciaCobrancaGerado(sequenciaCobrancaGerado);
        arrecadadorContratoConvenio.setIndicadorPadrao(indicadorPadrao);
        arrecadadorContratoConvenio.setLeiaute(documentoLayout);
        return arrecadadorContratoConvenio;
    }

    public DocumentoTipo obterTipoDocumento(Long documentoTipoId) {

        DocumentoTipo tipoDocumento = null;
        if (!tipoDocumentoCache.containsKey(documentoTipoId)) {

            tipoDocumento = documentoTipoService.getById(documentoTipoId);
            tipoDocumentoCache.put(documentoTipoId, tipoDocumento);
        } else {
            tipoDocumento = tipoDocumentoCache.get(documentoTipoId);
        }
        return tipoDocumento;
    }

    public ArrecadadorContratoConvenio obterArrecadadorContratoConvenioParaBoletoBancario() {
        return repository.obterArrecadadorContratoConvenioParaBoletoBancario();
    }

    public synchronized Long calcularNossoNumeroComValidacao(ArrecadadorContratoConvenio arrecadadorContratoConvenio,
                                                             DadosAuditoria dadosAuditoria) throws NegocioException {

        ArrecadadorCarteiraCobranca arrecadadorCarteiraCobranca = obterCarteiraCobrancaDeConvenio(arrecadadorContratoConvenio.getId());

        arrecadadorContratoConvenio.setLeiaute(documentoLayoutService
                .obterDocumentoLayoutPorConvenio(arrecadadorContratoConvenio.getId()));

        /**
         * O "Nosso Numero" sera zerado quando nao for da responsabilidade da empresa gera-lo.
         * Isso sera definido atraves da coluna "ARCA_IN_NOSSO_NUMERO_LIVRE" da tabela "ARRECADADOR_CARTEIRA_COBRANCA"
         * */
        if (arrecadadorCarteiraCobranca != null && !arrecadadorCarteiraCobranca.getIndicadorNossoNumeroLivre()) {
            return 0L;
        }

        Long inicioSequencia = arrecadadorContratoConvenio.getSequencialCobrancaInicio();
        Long fimSequencia = arrecadadorContratoConvenio.getSequencialCobrancaFim();

        if(inicioSequencia == null){
            inicioSequencia = 0L;
        }

        if(fimSequencia == null){
            fimSequencia = 0L;
        }

        Long ultimoNumero = null;
        if (arrecadadorContratoConvenio.getSequenciaCobrancaGerado() != null) {
            ultimoNumero = arrecadadorContratoConvenio.getSequenciaCobrancaGerado() + 1;
        } else {
            throw new NegocioException(ERRO_NEGOCIO_CONVENIO_SEM_NOSSO_NUMERO, true);
        }

        if ((arrecadadorCarteiraCobranca != null) && (arrecadadorCarteiraCobranca.getIndicadorFaixaNossoNumero())
                && (ultimoNumero > fimSequencia)) {
            ultimoNumero = inicioSequencia;
        }

        arrecadadorContratoConvenio.setSequenciaCobrancaGerado(ultimoNumero);
        arrecadadorContratoConvenio.setDadosAuditoria(dadosAuditoria);

        try {
           arrecadadorContratoConvenioService.save(arrecadadorContratoConvenio);
        } catch (Exception e) {
            log.erro("Entidade com Versao Desatualizada", e.getMessage());
        }

        return ultimoNumero;
    }

    public ArrecadadorCarteiraCobranca obterCarteiraCobrancaDeConvenio(Long arrecadadorConvenioId) {
        ArrecadadorCarteiraCobranca arrecadadorCarteiraCobranca = new ArrecadadorCarteiraCobranca();

        arrecadadorCarteiraCobranca = repository.obterCarteiraCobrancaDeConvenio(arrecadadorConvenioId);

        return arrecadadorCarteiraCobranca;
    }
//    @Override
//    public Arrecadador afterSave(Arrecadador entity) {
//
//        ArrecadadorContrato arrecadadorContratoExistente = this.repository.obterArrecadadorContratoPorArrecadador(entity.getId());
//        if(arrecadadorContratoExistente == null) {
//            ArrecadadorContrato arrecadadorContrato = new ArrecadadorContrato();
//            arrecadadorContrato.setArrecadador(entity);
//            arrecadadorContrato.setNumeroContrato(entity.getCodigoAgente());
//            arrecadadorContrato.setDataInicioContrato(data);
//            arrecadadorContratoService.save(arrecadadorContrato);
//
//            ArrecadadorCarteiraCobranca arrecadadorCarteiraCobranca = getArrecadadorCarteiraCobranca(entity);
//            arrecadadorCarteiraCobrancaService.save(arrecadadorCarteiraCobranca);
//
//            ArrecadadorContratoConvenio arrecadadorContratoConvenio = getArrecadadorContratoConvenio(arrecadadorContrato, arrecadadorCarteiraCobranca);
//            arrecadadorContratoConvenioService.save(arrecadadorContratoConvenio);
//        }
//        return super.afterSave(entity);
//    }
}