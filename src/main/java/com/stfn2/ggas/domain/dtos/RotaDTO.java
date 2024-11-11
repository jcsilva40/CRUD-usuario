package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Rota;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RotaDTO extends BaseDTO {
    private Long id;
	private String descricao;
	private Long periodicidadeId;
	private Long faturamentoGrupoId;
	private Long pontoConsumoId;
	private Long imovelId;
	private List<ImovelDTO> imoveis;
	private List <Long> listaImoveisARemanejar = new ArrayList<>();
	private boolean trocaImovel;
	private Long novaRotaId;

	public RotaDTO (Rota entity) {


	}

	@Override
	public Long getId() {
		return this.id;
	}
}
