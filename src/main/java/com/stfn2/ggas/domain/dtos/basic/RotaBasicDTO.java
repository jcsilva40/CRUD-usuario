package com.stfn2.ggas.domain.dtos.basic;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RotaBasicDTO extends BaseDTO {
        private Long id;
		private String descricao;
		private String faturamentoGrupoDescricao;
		private String periodicidadeDescricao;
		private Integer quantidadeImoveis;

//    	public RotaBasicDTO (Rota entity) {
//			quantidadeImoveis = entity.getListaImoveis().size();
//		}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}