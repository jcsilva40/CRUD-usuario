package com.stfn2.ggas.services;

import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.constante.Constantes;
import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.DocumentoCobranca;
import com.stfn2.ggas.domain.DocumentoTipo;
import com.stfn2.ggas.domain.Fatura;
import com.stfn2.ggas.domain.dtos.DocumentoCobrancaDTO;
import com.stfn2.ggas.domain.dtos.basic.DocumentoCobrancaBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.DocumentoCobrancaFilterDTO;
import com.stfn2.ggas.repositories.DocumentoCobrancaRepository;
import com.stfn2.ggas.tools.ToolDate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class DocumentoCobrancaService extends BaseService<DocumentoCobranca, DocumentoCobrancaDTO, DocumentoCobrancaBasicDTO, DocumentoCobrancaFilterDTO, DocumentoCobrancaRepository> {

   private ArrecadadorService arrecadadorService;
   private ConstanteSistemaService constanteSistemaService;

   DocumentoCobrancaService(ArrecadadorService arrecadadorService, ConstanteSistemaService constanteSistemaService){
      this.arrecadadorService = arrecadadorService;
      this.constanteSistemaService = constanteSistemaService;
   }

   public List<DocumentoCobranca> consultarDocumentoCobrancaPelaFatura(Long faturaId, DocumentoTipo tipoDocumento)
           throws NegocioException {

      DocumentoTipo tipoDocumentoTemp = tipoDocumento;

      if(tipoDocumento == null) {
         String parametroTpDoc = constanteSistemaService.obterValorConstanteSistemaPorCodigo(Constantes.C_TIPO_DOCUMENTO_FATURA);
         tipoDocumentoTemp = arrecadadorService.obterTipoDocumento(Long.valueOf(parametroTpDoc));
      }

      List<DocumentoCobranca> documentoCobrancas = repository.consultarDocumentoCobrancaPelaFatura(faturaId,
              tipoDocumentoTemp.getId());

      return documentoCobrancas;
   }

   public DocumentoCobranca obterDocumentoCobrancaRecente(Iterator<DocumentoCobranca> iterator) {
      DocumentoCobranca documentoCobranca = null;
      if (iterator.hasNext()) {
         documentoCobranca = iterator.next();
         while (iterator.hasNext()) {
            DocumentoCobranca documentoCobrancaAuxiliar = iterator.next();
            if (ToolDate.compararDatas(documentoCobranca.getDataEmissao(),
                    documentoCobrancaAuxiliar.getDataEmissao()) < 0) {
               documentoCobranca = documentoCobrancaAuxiliar;
            }
         }
      }
      return documentoCobranca;
   }

   public List<Fatura> consultarDadosFaturasPorDocumentoCobranca(Long faturaId, Long documentoCobrancaId) {

      List<Fatura> dadosFaturaPorDocumentoCobrancaVos = new ArrayList<>();

      dadosFaturaPorDocumentoCobrancaVos =
              repository.consultarDadosFaturasPorDocumentoCobranca(faturaId, documentoCobrancaId);

      return dadosFaturaPorDocumentoCobrancaVos;
   }

   public List<DocumentoCobranca> consultarDadosDocumentoCobrancaPelaFatura(Long faturaId, DocumentoTipo documentoTipo) {

      List<DocumentoCobranca> documentoCobrancas = new ArrayList<>();

      DocumentoTipo tipoDocumento = documentoTipo;

      if(tipoDocumento == null) {
         String parametroTpDoc = constanteSistemaService.obterValorConstanteSistemaPorCodigo(Constantes.C_TIPO_DOCUMENTO_FATURA);
         tipoDocumento = arrecadadorService.obterTipoDocumento(Long.valueOf(parametroTpDoc));
      }

      documentoCobrancas = repository.consultarDadosDocumentoCobrancaPelaFatura(faturaId, tipoDocumento.getId());

      return documentoCobrancas;
   }

   public DocumentoCobranca obtemDadosParaConsultarFaturas(Fatura fatura) {
      List<DocumentoCobranca> listaDocumentoCobranca = consultarDadosDocumentoCobrancaPelaFatura(fatura.getId(), null);

      Iterator<DocumentoCobranca> iterator = listaDocumentoCobranca.iterator();

      return obterDocumentoCobrancaRecente(iterator);
   }
}