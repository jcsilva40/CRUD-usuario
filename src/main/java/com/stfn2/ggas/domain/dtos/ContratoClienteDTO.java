package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.ContratoCliente;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContratoClienteDTO extends BaseDTO {
        
        private Long id;

	private Boolean habilitado = true;

	private Integer versao = 0;

	private LocalDateTime ultimaAlteracao = LocalDateTime.now();
 
	private LocalDate relacaoInicio;

	private LocalDate relacaoFim;

	private Boolean indicadorPrincipal;
                
        private ContratoDTO contratoDTO;
/*

	private ClienteDTO cliente;

	private PontoConsumoDTO pontoConsumo;

	private EntidadeConteudoDTO responsabilidade;
  */

    	public ContratoClienteDTO (ContratoCliente entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}