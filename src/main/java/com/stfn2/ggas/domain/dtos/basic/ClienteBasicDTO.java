package com.stfn2.ggas.domain.dtos.basic;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Cliente;
import com.stfn2.ggas.tools.ToolStr;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteBasicDTO extends BaseDTO {
   private Long id;
   private String nome;
	 private String cnpj;
	 private String cpf;
	 private String cpfCnpj;

   public ClienteBasicDTO(Cliente entity) {
		 super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
	 public String getCpfCnpj(){
		 if(this.cnpj != null){
			 return ToolStr.aplicarMascaraCNPJ(this.cnpj);
		 }
		 return ToolStr.aplicarMascaraCPF(this.cpf);
	 }
}