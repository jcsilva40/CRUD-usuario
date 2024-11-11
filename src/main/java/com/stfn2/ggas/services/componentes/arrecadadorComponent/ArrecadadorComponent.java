package com.stfn2.ggas.services.componentes.arrecadadorComponent;

import com.stfn2.ggas.domain.*;
import com.stfn2.ggas.domain.enumTypes.CodigoCarteiraCobrancaEnum;
import com.stfn2.ggas.domain.enumTypes.TipoCarteiraCobrancaEnum;
import com.stfn2.ggas.domain.enumTypes.TipoConvenioBancarioEnum;
import com.stfn2.ggas.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class ArrecadadorComponent {

   @Autowired
   private ClienteService clienteService;
   
   @Autowired
   private PontoConsumoService pontoConsumoService;
   
   @Autowired
   private BancoService bancoService;
   
   @Autowired
   private ArrecadadorService arrecadadorService;
   
   @Autowired
   private ArrecadadorContratoService arrecadadorContratoService;
   
   @Autowired
   private ArrecadadorContratoConvenioService arrecadadorContratoConvenioService;
   
   @Autowired
   private ContaBancariaService contaBancariaService;
   
   @Autowired
   private DocumentoLayoutService documentoLayoutService;
   
   @Autowired
   private ArrecadadorCarteiraCobrancaService arrecadadorCarteiraCobrancaService;

   private static final Long BANCO_ID = 128L;
   private static final int NUMERO_CARTEIRA = 109;
   private static final Long NUMERO_CHAVE_CONTA = 7L;
   private static final Long SEQUENCIA_COBRANCA_GERADO = 1L;
   private static final Long SEQUENCIA_COBRANCA_INICIO = 1L;
   private static final Long SEQUENCIA_COBRANCA_FIM = 99999999999999L;
   private static final Long LAYOUT = 13L;

   public ArrecadadorContratoConvenio gerarArrecadador(Long clienteId, Long pontoConsumoId) {
       
       //Setar valores Cliente e Ponto de Consumo a partir do Id recebido
       Cliente cliente = clienteService.getById(clienteId);
       PontoConsumo pontoConsumo = pontoConsumoService.getById(pontoConsumoId);

      /***************************************** Arrecadador ************************************************/

      Arrecadador arrecadador = new Arrecadador();
      arrecadador.setCodigoAgente(pontoConsumo.getCil());
      arrecadador.setCliente(cliente);

      Banco banco = bancoService.getById(BANCO_ID);
      arrecadador.setBanco(banco);

      arrecadador = arrecadadorService.save(arrecadador);

      /**************************************** ContratoArrecadador ************************************************/

      ArrecadadorContrato arrecadadorContrato = new ArrecadadorContrato();
      arrecadadorContrato.setArrecadador(arrecadador);
      arrecadadorContrato.setNumeroContrato(arrecadador.getCodigoAgente());
      arrecadadorContrato.setDataInicioContrato(LocalDate.now());

      arrecadadorContrato = arrecadadorContratoService.save(arrecadadorContrato);

      /**************************************** CarteiraCobranca ************************************************/

      ArrecadadorCarteiraCobranca arrecadadorCarteiraCobranca = new ArrecadadorCarteiraCobranca();

      arrecadadorCarteiraCobranca.setDescricao(pontoConsumo.getDescricao() + " " + banco.getNome());
      arrecadadorCarteiraCobranca.setNumero(NUMERO_CARTEIRA);
      arrecadadorCarteiraCobranca.setIndicadorEmissao(false);
      arrecadadorCarteiraCobranca.setIndicadorEntrega(false);
      arrecadadorCarteiraCobranca.setIndicadorFaixaNossoNumero(true);
      arrecadadorCarteiraCobranca.setIndicadorNossoNumeroLivre(true);
      arrecadadorCarteiraCobranca.setIndicadorProtesto(false);
      arrecadadorCarteiraCobranca.setArrecadador(arrecadador);
      arrecadadorCarteiraCobranca.setCodigoCarteira(CodigoCarteiraCobrancaEnum.E);
      arrecadadorCarteiraCobranca.setTipoCarteira(TipoCarteiraCobrancaEnum.ESCRITURAL);
      arrecadadorCarteiraCobranca = arrecadadorCarteiraCobrancaService.save(arrecadadorCarteiraCobranca);

      /***************************************** ConvênioArrecadador ************************************************/

      ArrecadadorContratoConvenio arrecadadorContratoConvenio = new ArrecadadorContratoConvenio();
      arrecadadorContratoConvenio.setCodigoConvenio(pontoConsumo.getCil());
      arrecadadorContratoConvenio.setTipoConvenio(TipoConvenioBancarioEnum.BOLETO);

      ContaBancaria contaConvenio = getContaBancaria();
      arrecadadorContratoConvenio.setContaConvenio(contaConvenio);
      arrecadadorContratoConvenio.setContaCredito(contaConvenio);

      arrecadadorContratoConvenio.setSequenciaCobrancaGerado(SEQUENCIA_COBRANCA_GERADO);
      arrecadadorContratoConvenio.setSequencialCobrancaFim(SEQUENCIA_COBRANCA_FIM);
      arrecadadorContratoConvenio.setSequencialCobrancaInicio(SEQUENCIA_COBRANCA_INICIO);

      arrecadadorContratoConvenio.setArrecadadorContrato(arrecadadorContrato);
      arrecadadorContratoConvenio.setArrecadadorCarteiraCobranca(arrecadadorCarteiraCobranca);
      arrecadadorContratoConvenio.setNsaRetorno(1L);
      arrecadadorContratoConvenio.setNsaRemessa(1L);
      arrecadadorContratoConvenio.setIndicadorPadrao(Boolean.FALSE);
      arrecadadorContratoConvenio.setLeiaute(documentoLayoutService.getById(LAYOUT));

      arrecadadorContratoConvenio = arrecadadorContratoConvenioService.save(arrecadadorContratoConvenio);            
      return arrecadadorContratoConvenio;
   }

   private ContaBancaria getContaBancaria() {
      ContaBancaria contaBancaria = contaBancariaService.getById(NUMERO_CHAVE_CONTA);
      return contaBancaria;
   }

   /*public ArrecadadorContratoConvenio obterArrecadadorContratoConvenioPorClienteBancoCil(Long clienteId, Long bancoId, String cil) {
      ArrecadadorContratoConvenio arrecadadorContratoConvenio =
              arrecadadorContratoConvenioService.obterArrecadadorContratoConvenioPorClienteBancoCil(clienteId, bancoId, cil);

      return arrecadadorContratoConvenio;
   }*/
   
   public Boolean obterArrecadadorContratoConvenioPorClienteBancoCil(Long clienteId, Long pontoConsumoId) {
        Long bancoId = BANCO_ID;
        PontoConsumo pontoConsumo = pontoConsumoService.getById(pontoConsumoId);
        String cil = pontoConsumo.getCil();
        return arrecadadorContratoConvenioService.obterArrecadadorContratoConvenioPorClienteBancoCil(clienteId, bancoId, cil, TipoConvenioBancarioEnum.BOLETO);
   }
}
