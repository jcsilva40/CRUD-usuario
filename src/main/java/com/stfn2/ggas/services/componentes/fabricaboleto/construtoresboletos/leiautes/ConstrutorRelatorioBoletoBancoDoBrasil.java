package com.stfn2.ggas.services.componentes.fabricaboleto.construtoresboletos.leiautes;

import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.domain.DocumentoCobranca;
import com.stfn2.ggas.services.componentes.fabricaboleto.construtoresboletos.ConstrutorRelatorioBoleto;

public class ConstrutorRelatorioBoletoBancoDoBrasil extends ConstrutorRelatorioBoleto {

   private static final String MSG_LOCAL_PAGAMENTO =
           "Pagável em qualquer banco até o vencimento. Após, atualize o boleto no site bb.com.br.";

   /**
    * Instantiates a new construtor relatorio boleto banco do brasil.
    *
    * @param documentoCobranca the documento cobranca
    * @throws NegocioException the negocio exception
    */
   public ConstrutorRelatorioBoletoBancoDoBrasil(DocumentoCobranca documentoCobranca) throws NegocioException {
      super(documentoCobranca);
   }

   @Override
   public ConstrutorRelatorioBoleto preencherNossoNumero() {
      mapaParametros().put(NOSSO_NUMERO, String.valueOf(this.documentoCobranca.getNossoNumero()));
      return this;
   }

   @Override
   public ConstrutorRelatorioBoleto preencherLocalDePagamento() {
      mapaParametros().put(LOCAL_PAGAMENTO, MSG_LOCAL_PAGAMENTO);
      return this;
   }
}
