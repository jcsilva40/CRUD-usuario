package com.stfn2.ggas.services;

import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.ConstanteSistema;
import com.stfn2.ggas.domain.DocumentoImpressaoLayout;
import com.stfn2.ggas.domain.dtos.DocumentoImpressaoLayoutDTO;
import com.stfn2.ggas.domain.dtos.basic.DocumentoImpressaoLayoutBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.DocumentoImpressaoLayoutFilterDTO;
import com.stfn2.ggas.domain.enumTypes.TipoDocumentoImpressoEnum;
import com.stfn2.ggas.repositories.ConstanteSistemaRepository;
import com.stfn2.ggas.repositories.DocumentoImpressaoLayoutRepository;
import org.springframework.stereotype.Service;

@Service
public class DocumentoImpressaoLayoutService extends BaseService<DocumentoImpressaoLayout, DocumentoImpressaoLayoutDTO, DocumentoImpressaoLayoutBasicDTO, DocumentoImpressaoLayoutFilterDTO, DocumentoImpressaoLayoutRepository> {

   private ConstanteSistemaRepository constanteSistemaRepository;

   DocumentoImpressaoLayoutService(ConstanteSistemaRepository constanteSistemaRepository) {
      this.constanteSistemaRepository = constanteSistemaRepository;
   }

   public String obterDocumentoImpressaoLayoutPorConstante(String constante) throws NegocioException {

      if(constante != null && !constante.isEmpty()) {
         ConstanteSistema constanteSistema = constanteSistemaRepository.obterConstantePorCodigo(constante);
         if(constanteSistema != null) {

            TipoDocumentoImpressoEnum tipoDocumentoImpresso =  TipoDocumentoImpressoEnum.toEnum(Long.valueOf(constanteSistema
                    .getValor()));

            DocumentoImpressaoLayout documentoImpressaoLayout = repository
                    .consultarDocumentoImpressaoLayout(tipoDocumentoImpresso);

            String arquivoRelatorio = null;

            if(documentoImpressaoLayout != null) {
               arquivoRelatorio = documentoImpressaoLayout.getNomeArquivo();
            }

            return arquivoRelatorio;

         }
      }

      return null;
   }

}