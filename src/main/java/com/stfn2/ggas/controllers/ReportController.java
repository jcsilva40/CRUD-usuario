package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.models.Response;
import com.stfn2.ggas.domain.dtos.DownloadDTO;
import com.stfn2.ggas.services.componentes.relatorio.RelatorioComponent;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/relatorios")
public class ReportController {

   @Autowired
   private RelatorioComponent relatorioService;

   @GetMapping("/exemplo")
   public ResponseEntity<byte[]> relExemplo(HttpServletRequest request) throws Exception {

      byte[] relatorioBytes = relatorioService.executePdf(null, null, null);

      return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatorioBytes);
   }

   @PostMapping("obterplanilhadelista")
   public ResponseEntity<Response<DownloadDTO>>downloadPlanilhaPorLista (@RequestBody List<HashMap<String, Object>> lista) {
      Response<DownloadDTO> response = new Response<>();
      DownloadDTO planilhaXlSx = RelatorioComponent.gerarRelatorioXML(lista);
      response.setData(planilhaXlSx);
      return ResponseEntity.ok(response);
   }
}
