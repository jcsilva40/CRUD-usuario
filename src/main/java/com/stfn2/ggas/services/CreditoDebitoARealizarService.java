package com.stfn2.ggas.services;

import com.stfn2.ggas.config.security.domain.UserServices;
import com.stfn2.ggas.core.abstractClasses.combo.ComboDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.*;
import com.stfn2.ggas.domain.dtos.EnderecoDTO;
import com.stfn2.ggas.domain.dtos.RubricaDTO;
import com.stfn2.ggas.domain.enumTypes.*;
import com.stfn2.ggas.repositories.*;
import com.stfn2.ggas.tools.ToolDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.dtos.CreditoDebitoARealizarDTO;
import com.stfn2.ggas.domain.dtos.filter.CreditoDebitoARealizarFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.CreditoDebitoARealizarBasicDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CreditoDebitoARealizarService extends BaseService<CreditoDebitoARealizar, CreditoDebitoARealizarDTO, CreditoDebitoARealizarBasicDTO, CreditoDebitoARealizarFilterDTO, CreditoDebitoARealizarRepository> {

    @Autowired
    CreditoDebitoNegociadoRepository creditoDebitoNegociadoRepository;
    @Autowired
    CreditoDebitoDetalhamentoRepository credDebDetalhamentoRepository;

    @Autowired
    FaturaItemRepository faturaItemRepository;
    @Autowired
    PontoConsumoRepository pontoConsumoRepository;
    @Autowired
    RubricaRepository rubricaRepository;
    @Autowired
    RubricaService rubricaService;
    @Autowired
    private UserServices userService;

    public void alterarCobranca(CreditoDebitoARealizarDTO dto){

        CreditoDebitoARealizar realizar = getById(dto.getId());
        CreditoDebitoNegociado negociado = realizar.getCreditoDebitoNegociado();
        List<CreditoDebitoDetalhamento>  detalhamento= credDebDetalhamentoRepository.findByCreditoDebitoARealizarId(dto.getId());
        CreditoDebitoDetalhamento credDet = detalhamento.get(0);

        realizar.setAnoMesFaturamento(dto.getAnoMesFaturamento());
        negociado.setAnoMesInclusao(dto.getAnoMesFaturamento());
        realizar.setCiclo(dto.getCiclo());

        if(dto.getCancelar()){
            setCancelarCobranca(realizar);
        }

        if (dto.getReativar()) {
            setReativarCobranca(realizar);
        }

        negociado.setDescricao(dto.getCredDebNegComplemento());

        BigDecimal valor = dto.getValor();

        if(valor != null ){
            realizar.setValor(valor);
            negociado.setValor(valor);
            credDet.setValor(valor);
        }

        creditoDebitoNegociadoRepository.save(negociado);
        credDebDetalhamentoRepository.save(credDet);
        this.repository.save(realizar);

    }


    public void setCancelarCobranca(CreditoDebitoARealizar credDebAReallizar){
        if(validarSeCobrancaFaturada(credDebAReallizar.getId())){
            credDebAReallizar.setCreditoDebitoSituacao(CreditoDebitoSituacaoEnum.CANCELADA);
        }
    }

    public boolean validarSeCobrancaFaturada(Long idCredDebAReallizar){
        List<FaturaItem> items = faturaItemRepository.findByCreditoDebitoARealizarId(idCredDebAReallizar);
        // Retorna true se a lista estiver vazia
        return items.isEmpty();
    }

    private void setReativarCobranca(CreditoDebitoARealizar credDebAReallizar) {
        credDebAReallizar.setCreditoDebitoSituacao(CreditoDebitoSituacaoEnum.NORMAL);
    }

    @Override
    public Page<CreditoDebitoARealizarBasicDTO> findAll(CreditoDebitoARealizarFilterDTO filter, Pageable pageable) {

        Integer anoMesFaturamento =  filter.getAnoMesFaturamento();
        Integer ciclo = filter.getCiclo();
        Long creditoDebitoSituacao = filter.getCreditoDebitoSituacao();
        CreditoDebitoSituacaoEnum creditoDebitoSituacaoEnum = null;
        String pontoConsumoCil = filter.getPontoConsumoCil();
        String credDebNegComplemento = filter.getCredDebNegComplemento();
        Boolean executada = filter.getExecutada();

        if(!Objects.isNull(creditoDebitoSituacao)){
            creditoDebitoSituacaoEnum = CreditoDebitoSituacaoEnum.toEnum(creditoDebitoSituacao);
        }

        Boolean habilitado = filter.getHabilitado();

        return this.repository.findAll(pontoConsumoCil, anoMesFaturamento, ciclo, creditoDebitoSituacaoEnum, credDebNegComplemento, executada, pageable).map(projection -> MapperImpl.parseObject(projection, CreditoDebitoARealizarBasicDTO.class));
    }


    @Override
    public CreditoDebitoARealizarDTO entityToDTO(CreditoDebitoARealizar entity){
        CreditoDebitoNegociado negociado = entity.getCreditoDebitoNegociado();
        PontoConsumo pc = negociado.getPontoConsumo();
        Rubrica rub = negociado.getRubrica();

        CreditoDebitoARealizarDTO dto = new CreditoDebitoARealizarDTO();

        dto.setPontoConsumoDescricao(pc.getDescricao());
        dto.setPontoConsumoCil(pc.getCil());
        dto.setCredDebNegComplemento(negociado.getDescricao());
        dto.setTipoCobrancaRubrDesc(rub.getDescricao());
        dto.setPontoConsumoId((pc.getId()));
        dto.setId(entity.getId());
        dto.setCiclo(entity.getCiclo());
      //  dto.setCreditoDebitoNegociadoId(entity.getCreditoDebitoNegociado().getId());
        dto.setValor(entity.getValor());
        dto.setAnoMesCobranca(entity.getAnoMesCobranca());
        dto.setAnoMesFaturamento(entity.getAnoMesFaturamento());
        dto.setCreditoDebitoSituacao(entity.getCreditoDebitoSituacao());
        dto.setNumeroPrestCobrada(negociado.getQuantidadePrestacaoCobrada());

        if (CreditoDebitoSituacaoEnum.NORMAL.equals(dto.getCreditoDebitoSituacao())) {
            dto.setExecutada(dto.getNumeroPrestCobrada() != null  && dto.getNumeroPrestCobrada() > 0);
        } else {
            dto.setExecutada(true);
        }

        return dto;
    }

    public Cliente findClienteByPontoConsumoId(Long pontoConsumoId) {
        Optional<Cliente>  cliente = pontoConsumoRepository.findClienteByPontoConsumo(pontoConsumoId);
        return cliente.orElse(null);
    }

    public Map<String, Object> obterEnderecoECnpjCpfPorPontoConsumoId(Long pontoConsumoId) {
        EnderecoDTO enderecoDTO = pontoConsumoRepository.findEnderecoByPontoConsumo(pontoConsumoId);
        Cliente cliente = findClienteByPontoConsumoId(pontoConsumoId);
        Map<String, Object> result = new HashMap<>();
        result.put("endereco", enderecoDTO);
        result.put("cpfCnpj", cliente != null ? (cliente.getCnpj() != null ? cliente.getCnpj() : cliente.getCpf()) : null);
        return result;
    }

    public RubricaDTO obterDadosDaRubrica(Long rubricaId) {
        Rubrica rubrica = rubricaRepository.findById(rubricaId)
                .orElseThrow(() -> new IllegalArgumentException("Rubrica não encontrada"));

        RubricaDTO rubricaDTO = new RubricaDTO();
        rubricaDTO.setMaximoParcela(rubrica.getMaximoParcela());
        rubricaDTO.setMinimoEntrada(rubrica.getMinimoEntrada());
        rubricaDTO.setValorReferencia(rubrica.getValorReferencia());
        rubricaDTO.setValorMaximo(rubrica.getValorMaximo());
        rubricaDTO.setEntradaObrigatoria(rubrica.getEntradaObrigatoria());

        return rubricaDTO;
    }

    public List<ComboDTO> findRubricasPorTipo(String tipo) {
       return rubricaService.findRubricasPorTipo(tipo);
    }
    public CreditoDebitoARealizarDTO gerarSalvarParcelas(CreditoDebitoARealizarDTO dto) {
        validarDadosEntrada(dto);

        Rubrica rubrica = rubricaRepository.findById(dto.getRubricaId())
                .orElseThrow(() -> new IllegalArgumentException("Rubrica não encontrada"));

        validarRubrica(rubrica, dto);

        Cliente cliente = findClienteByPontoConsumoId(dto.getPontoConsumoId());
        PontoConsumo pontoConsumo = pontoConsumoRepository.findById(dto.getPontoConsumoId())
                .orElseThrow(() -> new IllegalArgumentException("Ponto consumo não encontrado"));

        CreditoDebitoNegociado negociado = criarCreditoDebitoNegociado(dto, rubrica, cliente, pontoConsumo);
        creditoDebitoNegociadoRepository.save(negociado);

        List<CreditoDebitoARealizar> parcelas = gerarParcelas(dto, negociado);
        repository.saveAll(parcelas);

        parcelas.forEach(parcela -> {
            CreditoDebitoDetalhamento detalhamento = criarDetalhamento(parcela, rubrica);
            credDebDetalhamentoRepository.save(detalhamento);
        });

        return dto;
    }

    private void validarDadosEntrada(CreditoDebitoARealizarDTO dto) {
        if (dto.getRubricaId() == null || dto.getClienteId() == null || dto.getNumeroParcelas() <= 0) {
            throw new IllegalArgumentException("Dados de entrada inválidos");
        }
    }

    private CreditoDebitoNegociado criarCreditoDebitoNegociado(CreditoDebitoARealizarDTO dto, Rubrica rubrica, Cliente cliente,  PontoConsumo pontoConsumo) {
        CreditoDebitoNegociado negociado = new CreditoDebitoNegociado();

        if (rubrica != null && rubrica.getAmortizacao() != null) {
            negociado.setAmortizacao(rubrica.getAmortizacao());
        }

        negociado.setCliente(cliente);
        negociado.setRubrica(rubrica);
        negociado.setValor(dto.getValorTotal());
        negociado.setDataCobranca(dto.getDataInicioCobranca());
        negociado.setPeriodicidadeCobranca(dto.getPeriodicidadeCobranca());
        negociado.setPeriodicidadeJuros(dto.getPeriodicidadeJuros());
        negociado.setValorEntrada(dto.getValorEntrada());
        negociado.setDescricao(dto.getCredDebNegComplemento());
        negociado.setPontoConsumo(pontoConsumo);
        negociado.setSegmento(pontoConsumo.getSegmento());
        negociado.setDiaVencimento(dto.getMelhorDiaVencimento());
        negociado.setDataFinanciamento(LocalDateTime.now());
        negociado.setStatus(StatusAutorizacaoEnum.AUTORIZADO);
        negociado.setHabilitado(true);
        negociado.setQuatidadePrestacoes(1);
        negociado.setFinanciamentoTipo(Objects.requireNonNull(rubrica).getFinanciamentoTipo());
        negociado.setAnoMesInclusao(ToolDate.anoMesCorrente());
        negociado.setFormaCobranca(FormaCobrancaParcelamentoEnum.FATURA);
        negociado.setQuantidadePrestacaoCobrada(0);
        //negociado.setDadosAuditoria();
        negociado.setUsuario(userService.getAuthenticatedUsername());
        negociado.setVersao(1);

        return negociado;
    }

    private List<CreditoDebitoARealizar> gerarParcelas(CreditoDebitoARealizarDTO dto, CreditoDebitoNegociado negociado) {
        BigDecimal valorParcela = dto.getValorTotal().divide(BigDecimal.valueOf(dto.getNumeroParcelas()), RoundingMode.HALF_UP);
        List<CreditoDebitoARealizar> parcelas = new ArrayList<>();

        for (int i = 1; i <= dto.getNumeroParcelas(); i++) {
            CreditoDebitoARealizar parcela = new CreditoDebitoARealizar();
            parcela.setCreditoDebitoNegociado(negociado);
            parcela.setValor(valorParcela);
            parcela.setNumeroPrestacao(BigDecimal.valueOf(i));
            parcela.setAnoMesFaturamento(calcularAnoMesFaturamento(dto.getDataInicioCobranca()));
            parcela.setCiclo(calcularCiclo(dto.getDataInicioCobranca(), dto.getPeriodicidadeCobranca(), dto.getPontoConsumoId()));
            parcela.setCreditoDebitoSituacao(CreditoDebitoSituacaoEnum.NORMAL);
            parcela.setSituacaoPagamento(SituacaoPagamentoEnum.PENDENTE);
            parcela.setQuantidade(dto.getQuantidade());
            parcela.setAntecipacao(false);
            parcela.setHabilitado(true);
            parcelas.add(parcela);
        }
        return parcelas;
    }

    private CreditoDebitoDetalhamento criarDetalhamento(CreditoDebitoARealizar parcela, Rubrica rubrica) {
        CreditoDebitoDetalhamento detalhamento = new CreditoDebitoDetalhamento();
        detalhamento.setCreditoDebitoARealizar(parcela);
        detalhamento.setValor(parcela.getValor());
        detalhamento.setLancamentoItemContabil(rubrica.getLancamentoItemContabil());
        return detalhamento;
    }


    private void validarRubrica(Rubrica rubrica, CreditoDebitoARealizarDTO dto) {

        BigDecimal valor = dto.getValorTotal();
        BigDecimal valorEntrada = dto.getValorEntrada();
        BigDecimal valorUnitario = dto.getValorUnitario();

        if (rubrica.getValorReferencia() != null && valor.compareTo(rubrica.getValorReferencia()) < 0) {
            throw new IllegalArgumentException("Erro: Valor menor que o valor de referência da rubrica.");
        }

        if (rubrica.getValorMaximo() != null) {
            if (valorUnitario.compareTo(rubrica.getValorMaximo()) > 0) {
                throw new IllegalArgumentException("Erro: Valor unitário maior que o valor máximo permitido pela rubrica.");
            }

            if (valorEntrada != null && valorEntrada.compareTo(rubrica.getValorMaximo()) > 0) {
                throw new IllegalArgumentException("Erro: Valor de entrada maior que o valor máximo permitido pela rubrica.");
            }
        }
    }



    private int calcularAnoMesFaturamento(LocalDateTime dataInicio) {
        int ano = dataInicio.getYear();
        int mes = dataInicio.getMonthValue(); // Retorna o mês 1-based

        return ano * 100 + mes;
    }

    private Integer calcularCiclo(LocalDateTime dataInicio, TipoPeriodicidadeEnum periodicidade, Long idPontoConsumo) {
        int diaDoMes = dataInicio.getDayOfMonth();

        return switch (periodicidade) {
            case MENSAL -> 1; // Apenas um ciclo por mês
            case A_CADA_FATURAMENTO -> {
                Integer quantidadeDias = pontoConsumoRepository.obterQuantidadeDiasFaturamentoByPontoConsumo(idPontoConsumo);
                int ciclo = 1;
                int basePeriodicidade = quantidadeDias;

                while (diaDoMes > basePeriodicidade) {
                    ciclo++;
                    basePeriodicidade += quantidadeDias;
                }

                yield ciclo;
            }

        };
    }


}

