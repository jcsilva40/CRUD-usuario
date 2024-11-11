package com.stfn2.ggas.services.componentes.fabricaboleto.construtoresboletos.leiautes;

import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.domain.ArrecadadorContratoConvenio;
import com.stfn2.ggas.domain.DocumentoCobranca;
import com.stfn2.ggas.services.componentes.fabricaboleto.FabricaConstrutorBoleto;
import com.stfn2.ggas.services.componentes.fabricaboleto.codigodebarras.CodigoBarras;
import com.stfn2.ggas.services.componentes.fabricaboleto.codigodebarras.FabricaCodigoBarras;
import com.stfn2.ggas.services.componentes.fabricaboleto.codigodebarras.RepresentacaoNumericaSafra;
import com.stfn2.ggas.services.componentes.fabricaboleto.codigodebarras.dados.DadosPadraoCodigoBarras;
import com.stfn2.ggas.services.componentes.fabricaboleto.construtoresboletos.ConstrutorRelatorioBoleto;

public class ConstrutorRelatorioBoletoBancoSafra extends ConstrutorRelatorioBoleto {

   public static final int TAMANHO_AUTO_CONFERENCIA = 1;

   private static final String CODIGO_BARRA = "codigoBarra";
   private static final String NUMERO_CODIGO = "numeroCodigo";
   private static final String MSG_LOCAL_PAGAMENTO =
           "Pagável em qualquer banco até o vencimento.";

   private CodigoBarras codigoBarras;
   private ArrecadadorContratoConvenio arrecadador;

   /**
    * Instantiates a new construtor relatorio boleto banco safra.
    *
    * @param documentoCobranca the documento cobranca
    * @throws NegocioException the negocio exception
    */
   public ConstrutorRelatorioBoletoBancoSafra(DocumentoCobranca documentoCobranca) throws NegocioException {
      super(documentoCobranca);
      this.documentoCobranca = documentoCobranca;
      this.arrecadador = FabricaConstrutorBoleto.convenioParaBoleto(documentoCobranca);
      this.codigoBarras = this.gerarCodigoBarras();
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

   @Override
   public ConstrutorRelatorioBoleto preencherNumeroCodigoBarras() throws NegocioException {
      mapaParametros().put(CODIGO_BARRA, codigoBarras.toString());
      mapaParametros().put(NUMERO_CODIGO,
              new RepresentacaoNumericaSafra(codigoBarras,
                      this.arrecadador.getContaConvenio().getNumeroDigito()).toString());
      return this;
   }

   private CodigoBarras gerarCodigoBarras() throws NegocioException {
      return FabricaCodigoBarras.novoCodigoBarras(new DadosPadraoCodigoBarras(this.documentoCobranca));
   }
}
