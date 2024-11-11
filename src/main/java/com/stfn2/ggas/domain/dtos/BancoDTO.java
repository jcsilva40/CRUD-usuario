package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Banco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BancoDTO extends BaseDTO {
        private Long id;

                //Banco
		private Long bancoId;

		private String bancoNome;

		private String codigoBanco;

		private String bancoAbreviado;

                //Agencia
		private Long agenciaId;

		private String agenciaNome;

		private String agenciaCodigo;

                //Conta
		private Long contaId;
                
		private String contaDescricao;

		private String numeroConta;

		private String digitoVerificador;

    	public BancoDTO (Banco entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.bancoId;
    	}
}