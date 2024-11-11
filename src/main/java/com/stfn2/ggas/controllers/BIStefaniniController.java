package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.BIStefanini;
import com.stfn2.ggas.domain.dtos.BIPlayDTO;
import com.stfn2.ggas.domain.dtos.BIQueryDTO;
import com.stfn2.ggas.domain.dtos.BIStefaniniDTO;
import com.stfn2.ggas.domain.dtos.DownloadDTO;
import com.stfn2.ggas.domain.dtos.basic.BIStefaniniBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.BIStefaniniFilterDTO;
import com.stfn2.ggas.domain.enumTypes.FormatosBIEnum;
import com.stfn2.ggas.repositories.BIStefaniniRepository;
import com.stfn2.ggas.services.BIStefaniniService;
import com.stfn2.ggas.tools.ToolStr;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.HashMap;


@RestController
@RequestMapping(value = "/bistefaninis")
@Tag(name="BIStefanini", description="EndPoints para gerenciamento de BIStefanini")
public class BIStefaniniController extends BaseController<BIStefanini, BIStefaniniDTO, BIStefaniniBasicDTO, BIStefaniniFilterDTO,
		BIStefaniniRepository,BIStefaniniService> {

	@GetMapping("getBiToPlay/{id}")
	public ResponseEntity<BIPlayDTO> getBiToPlay(@PathVariable("id") Long id) {
		BIStefanini bi = getService().getById(id);
		BIPlayDTO dto = new BIPlayDTO(bi);
		return ResponseEntity.ok(dto);
	}

	@PostMapping("play/{id}")
	public ResponseEntity<DownloadDTO> play(@PathVariable("id") Long id,
						@RequestParam(value = "formato", required = true) Long formato,
						@RequestBody HashMap<String, Object> data) {
		FormatosBIEnum formatoEnum = FormatosBIEnum.toEnum(formato);
		DownloadDTO report;           
                report = getService().executarBI(id, data, formatoEnum);
                return ResponseEntity.ok(report);         
            
	}

	@PostMapping("downloadJasper")
	public ResponseEntity<DownloadDTO> downloadJasper(@RequestBody BIQueryDTO query) {
		DownloadDTO jasper = new DownloadDTO(query.getNomeField(), "jrxml", query.getConteudoJasper().getBytes());
		return ResponseEntity.ok(jasper);
	}

	@GetMapping("exportar/{id}")
	public ResponseEntity<DownloadDTO> exportar(@PathVariable("id") Long id) {
		BIStefanini bi = getService().getById(id);
		BIStefaniniDTO dto = getService().entityToDTO(bi);
		dto.setId(0L);
		dto.getParametros().forEach(p->{
			p.setId(0L);
			p.getValoresFixos().forEach(v-> v.setId(0L));
		});
		zerarIdQuery(dto.getQuery());
		String biJson = ToolStr.toJson(dto);
		return ResponseEntity.ok(new DownloadDTO(bi.getDescricao(), "json", biJson.getBytes()));
	}

	private void zerarIdQuery(BIQueryDTO query) {
		query.setId(0L);
		query.getChildren().forEach(this::zerarIdQuery);
	}

	@PostMapping("/importarBI")
	public ResponseEntity<BIStefaniniDTO> importarBI(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nenhum arquivo selecionado");
		}
		try {
			String conteudo = new String(file.getBytes());

			BIStefaniniDTO dto = ToolStr.jsonToObj(BIStefaniniDTO.class, conteudo);
			BIStefaniniDTO biSave = getService().createOrUpdate(dto);
			return ResponseEntity.ok(biSave);
		} catch (IOException e) {
			throw new RuntimeException("Erro importar BI");
		}
	}
}