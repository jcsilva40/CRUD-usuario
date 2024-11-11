package com.stfn2.ggas.domain.dtos.basic;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.CampanhaDescontoVincular;
import com.stfn2.ggas.domain.enumTypes.StatusCampanhaEnum;
import com.stfn2.ggas.domain.projection.CampanhaDescontoVincularBasicProjection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampanhaDescontoVincularBasicDTO extends BaseDTO {
        
        private Long id;
	
        private String campanhaSelecionada;
        
        private String predioSelecionado;
        
        private String contratoSelecionado;
        
        private String solicitante;
        
        private LocalDateTime dataSolicitacao;
        
        private String aprovador;
        
        private LocalDateTime dataAprovacao;
        
        private Short periodoFaturado;
        
        private Boolean habilitado;
        
        private StatusCampanhaEnum statusCampanha;

    	public CampanhaDescontoVincularBasicDTO (CampanhaDescontoVincular entity) {super(); 	}
        
        public CampanhaDescontoVincularBasicDTO(CampanhaDescontoVincularBasicProjection projection) {
            this.id = projection.getId();
            this.habilitado = projection.getHabilitado();
            setDescricaoCampanhaSelecionada(projection.getDescricaoCampanha(), projection.getInicioAdesao(), projection.getEncerramentoAdesao(), projection.getSegmentoPaiDescricao());
            this.contratoSelecionado = "";
		this.predioSelecionado = "";
		if(!Objects.isNull(projection.getContratoId())){
			setDescricaoContratoSelecionado(projection.getCil(), projection.getClienteNome(), projection.getCnpjOrCpf(), projection.getClienteNomeFantasia());
		}
		if(!Objects.isNull(projection.getImovelNome()) && !projection.getImovelNome().isBlank()){
			setDescricaoPredioSelecionado(projection.getImovelNome(), projection.getImovelNip());
		}		
		this.solicitante = projection.getSolicitante();
		this.dataSolicitacao = projection.getDataSolicitacao();
		this.aprovador = projection.getAprovador();
		this.dataAprovacao = projection.getDataAprovacao();
		this.statusCampanha = projection.getStatusCampanha();
		this.periodoFaturado = projection.getPeriodoFaturado();        
        }
        
        public final void setDescricaoCampanhaSelecionada(String descricaoCampanha, LocalDate inicioAdesao, LocalDate encerramentoAdesao, String segmento) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String strInicioAdesao = inicioAdesao.format(formatter);
		String strEncerramentoAdesao = encerramentoAdesao.format(formatter);
		this.campanhaSelecionada = descricaoCampanha + " [INÍCIO: " + strInicioAdesao + " FIM: " 
				+ strEncerramentoAdesao + "] [" + segmento + "]";
	}
	
	public final void setDescricaoContratoSelecionado(String cil, String nomeCliente, String cnpjOrCpf, String nomeFantasia) {
		this.contratoSelecionado = cil + " - " + nomeCliente + " - " + cnpjOrCpf + " - " + nomeFantasia ;
	}	
	
	public final void setDescricaoPredioSelecionado(String nomePredio, Integer NIP) {
		this.predioSelecionado = nomePredio + " [NIP=" + String.valueOf(NIP) + "]";
	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}