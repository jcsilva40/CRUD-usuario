package com.stfn2.ggas.services;

import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.DocumentoFiscal;
import com.stfn2.ggas.domain.Fatura;
import com.stfn2.ggas.domain.dtos.DocumentoFiscalDTO;
import com.stfn2.ggas.domain.dtos.basic.DocumentoFiscalBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.DocumentoFiscalFilterDTO;
import com.stfn2.ggas.domain.enumTypes.TipoOperacaoEnum;
import com.stfn2.ggas.repositories.DocumentoFiscalRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DocumentoFiscalService extends BaseService<DocumentoFiscal, DocumentoFiscalDTO, DocumentoFiscalBasicDTO, DocumentoFiscalFilterDTO, DocumentoFiscalRepository> {

   public DocumentoFiscalDTO obterDocumentoFiscalporNumeroFatura(Long faturaId) {
      DocumentoFiscalDTO documentoFiscal = new DocumentoFiscalDTO();
      DocumentoFiscalFilterDTO filter = new DocumentoFiscalFilterDTO();

      List<DocumentoFiscalBasicDTO> documentosFiscais=  this.filter(filter);
      documentoFiscal = this.findById(documentosFiscais.get(0).getId());

      return documentoFiscal;
   }

   public DocumentoFiscal obterDocumentoFiscalPorFatura(Fatura fatura, TipoOperacaoEnum tipoOperacao)
           throws NegocioException {
      return repository.obterDocumentoFiscalPorFatura(fatura.getId(), tipoOperacao);
   }

   public DocumentoFiscal obterDocumentoFiscalporIdFatura(Long faturaid){
      return repository.obterDocumentoFiscalporIdFatura(faturaid);
   }

   public Map<Long, List<DocumentoFiscal>> obterDocumentosFiscaisPorFatura(List<Fatura> faturas,
                                                                                 TipoOperacaoEnum tipoOperacao) {
      Map<Long, List<DocumentoFiscal>> documentoFiscalPorFatura =new HashMap<>();

      for(Fatura fatura : faturas){
         try {
            DocumentoFiscal documentoFiscal = this.obterDocumentoFiscalPorFatura(fatura, tipoOperacao);
            documentoFiscalPorFatura.put(fatura.getId(), Arrays.asList(documentoFiscal));
         } catch (NegocioException e) {
            throw new RuntimeException(e);
         }
      }
      return documentoFiscalPorFatura;
   }
}