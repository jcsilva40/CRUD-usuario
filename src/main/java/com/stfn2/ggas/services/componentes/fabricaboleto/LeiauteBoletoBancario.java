package com.stfn2.ggas.services.componentes.fabricaboleto;

import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.domain.DocumentoCobranca;
import com.stfn2.ggas.services.componentes.fabricaboleto.construtoresboletos.ConstrutorRelatorioBoleto;
import com.stfn2.ggas.services.componentes.fabricaboleto.construtoresboletos.leiautes.*;

public enum LeiauteBoletoBancario{
   BB {
      @Override
      ConstrutorRelatorioBoleto construtorBoleto(DocumentoCobranca documentoCobranca) throws NegocioException {
         return new ConstrutorRelatorioBoletoBancoDoBrasil(documentoCobranca);
      }
   },
   SANTANDER {
      @Override
      ConstrutorRelatorioBoleto construtorBoleto(DocumentoCobranca documentoCobranca) throws NegocioException {
         return new ConstrutorRelatorioBoletoSantander(documentoCobranca);
      }
   },
   CAIXA {
      @Override
      ConstrutorRelatorioBoleto construtorBoleto(DocumentoCobranca documentoCobranca) throws NegocioException {
         return new ConstrutorRelatorioBoletoCaixa(documentoCobranca);
      }
   },
   ITAU {
      @Override
      ConstrutorRelatorioBoleto construtorBoleto(DocumentoCobranca documentoCobranca) throws NegocioException {
         return new ConstrutorRelatorioBoletoItau(documentoCobranca);
      }
   },
   BN {
      @Override
      ConstrutorRelatorioBoleto construtorBoleto(DocumentoCobranca documentoCobranca) throws NegocioException {
         return new ConstrutorRelatorioBoletoSantander(documentoCobranca);
      }
   },
   BRADESCO {
      @Override
      ConstrutorRelatorioBoleto construtorBoleto(DocumentoCobranca documentoCobranca) throws NegocioException {
         return new ConstrutorRelatorioBoletoSantander(documentoCobranca);
      }
   },
   DAYCOVAL {
      @Override
      ConstrutorRelatorioBoleto construtorBoleto(DocumentoCobranca documentoCobranca) throws NegocioException {
         return new ConstrutorRelatorioBoletoDaycoval(documentoCobranca);
      }
   },
   SAFRA {
      @Override
      ConstrutorRelatorioBoleto construtorBoleto(DocumentoCobranca documentoCobranca) throws NegocioException {
         return new ConstrutorRelatorioBoletoBancoSafra(documentoCobranca);
      }
   };

   /**
    * Cria um {@link ConstrutorRelatorioBoleto} cujo banco está associado ao
    * tipo da enumeração.
    *
    * @param documentoCobranca o {@link DocumentoCobranca}
    * @return o {@link ConstrutorRelatorioBoleto} do banco selecionado
    * @throws NegocioException
    */
   abstract ConstrutorRelatorioBoleto construtorBoleto(DocumentoCobranca documentoCobranca) throws NegocioException;
}
