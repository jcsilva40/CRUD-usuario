package com.stfn2.ggas.services.componentes.debitoAutomatico;

import com.stfn2.ggas.constante.Constantes;
import com.stfn2.ggas.domain.Arrecadador;
import com.stfn2.ggas.domain.ClienteDebitoAutomatico;
import com.stfn2.ggas.domain.Contrato;
import com.stfn2.ggas.domain.DebitoAutomatico;
import com.stfn2.ggas.domain.Fatura;
import com.stfn2.ggas.domain.PontoConsumo;
import com.stfn2.ggas.domain.enumTypes.FormaCobrancaEnum;
import com.stfn2.ggas.services.ClienteDebitoAutomaticoService;
import com.stfn2.ggas.services.ConstanteSistemaService;
import com.stfn2.ggas.services.DebitoAutomaticoService;
import com.stfn2.ggas.services.PontoConsumoService;
import com.stfn2.ggas.tools.ToolDate;
import com.stfn2.ggas.tools.ToolNumber;
import java.math.BigInteger;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DebitoAutomaticoComponent {
    
    @Autowired
    private ConstanteSistemaService constanteSistemaService;
    
    @Autowired
    private ClienteDebitoAutomaticoService clienteDebitoAutomaticoService;
    
    @Autowired
    private PontoConsumoService pontoConsumoService;
    
    @Autowired
    private DebitoAutomaticoService debitoAutomaticoService;
    
    private static final DebitoAutomatico debito = new DebitoAutomatico();

    public void inserirDebitoAutomatico(Fatura fatura) {
                      
        Contrato contrato = fatura.getContratoAtual();

        //ContratoPontoConsumo contratoPc = contrato.getContratoPontoConsumo();

        Arrecadador arrecadador = contrato.getArrecadadorContratoConvenioDebitoAutomatico().getArrecadadorContrato()
                        .getArrecadador();

        Long clienteFaturaId = 0l;
        if(!Objects.isNull(fatura.getCliente())){                            
            clienteFaturaId = fatura.getCliente().getId();
        }

        Long contratoPaiId = 0l;
        if(!Objects.isNull(fatura.getContratoPai())){
            contratoPaiId = fatura.getContratoPai().getId();
        }             

        ClienteDebitoAutomatico clienteDebitoAutomatico = clienteDebitoAutomaticoService.obterClienteDebitoAutomatico(
                        clienteFaturaId, contratoPaiId);

        Long valorTipoConvenioDebitoAutomatico = constanteSistemaService.obterValorLongConstanteSistemaPorCodigo(Constantes.C_TIPO_CONVENIO_DEBITO_AUTOMATICO);
        FormaCobrancaEnum DEBITO_AUTOMATICO = FormaCobrancaEnum.toEnum(valorTipoConvenioDebitoAutomatico);        
        FormaCobrancaEnum formaCobranca = contrato.getFormaCobranca();
        String agencia = contrato.getAgencia();
        String conta = contrato.getContaCorrente();
        // String numeroCliente = gerarNumeroCliente(contrato, contratoPc, fatura);       

        if (formaCobranca.getId().equals(DEBITO_AUTOMATICO.getId())) {
            debito.setAgencia(agencia);
            debito.setConta(conta);
            debito.setArrecadador(arrecadador);
            debito.setDataInclusao(ToolDate.getDataAtualLocalDate());
            debito.setHabilitado(true);
            debito.setClienteDebitoAutomatico(clienteDebitoAutomatico);
            debito.setVencimento(fatura.getDataVencimentoFormatada());
            debito.setValorFatura(fatura.getValorTotal());
            debito.setReferencia(fatura.getAnoMes().toString());
            // debito.setNumeroCliente(numeroCliente);

            debitoAutomaticoService.save(debito);
        }
    }

    public String gerarNumeroCiiente(Fatura fatura) {

        PontoConsumo pontoConsumo = fatura.getPontoConsumo();
        return gerarNumeroCliente(pontoConsumo);		
    }
    
    public String gerarNumeroCliente(Long pontoConsumoId){
        PontoConsumo pontoConsumo = pontoConsumoService.getById(pontoConsumoId);
        return gerarNumeroCliente(pontoConsumo);
    }

    public String gerarNumeroCliente(PontoConsumo pontoConsumo) {

            BigInteger baseCode = new BigInteger("1000000000000");

            String contratoConcatCil = String.valueOf(pontoConsumo.getCil())
                            .concat(pontoConsumo.getComplementoCil());
            String soma = baseCode.add(BigInteger.valueOf(Long.parseLong(contratoConcatCil))).toString();
            Integer digitoVer = ToolNumber.checkSum(soma);
            String numeroCliente = (String) soma.concat(digitoVer.toString());

            return numeroCliente;
    }
}
