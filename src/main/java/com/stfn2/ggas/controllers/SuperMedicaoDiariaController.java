package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.core.models.Response;
import com.stfn2.ggas.domain.Banco;
import com.stfn2.ggas.domain.SuperMedicaoDiaria;
import com.stfn2.ggas.domain.dtos.BancoDTO;
import com.stfn2.ggas.domain.dtos.SuperMedicaoDiariaDTO;
import com.stfn2.ggas.domain.dtos.basic.BancoBasicDTO;
import com.stfn2.ggas.domain.dtos.basic.SuperMedicaoDiariaBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.BancoFilterDTO;
import com.stfn2.ggas.domain.dtos.filter.SuperMedicaoDiariaFilterDTO;
import com.stfn2.ggas.repositories.BancoRepository;
import com.stfn2.ggas.repositories.SuperMedicaoDiariaRepository;
import com.stfn2.ggas.services.BancoService;
import com.stfn2.ggas.services.SuperMedicaoDiariaService;
import com.stfn2.ggas.services.componentes.faturamentogruporota.dto.PontoConsumoSemMedicaoDTO;
import com.stfn2.ggas.services.componentes.faturamentogruporota.vo.FaturamentoGrupoVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/superMedicaoDiaria")
@Tag(name="SuperMedicaoDiaria", description="EndPoints para gerenciamento de Supervisorio Medicao Diaria")
public class SuperMedicaoDiariaController extends BaseController<SuperMedicaoDiaria, SuperMedicaoDiariaDTO, SuperMedicaoDiariaBasicDTO, SuperMedicaoDiariaFilterDTO,
		SuperMedicaoDiariaRepository, SuperMedicaoDiariaService> {

	@GetMapping(value = "/listaPontoConsumoSemMedicaoDiaria")
	public ResponseEntity<?> listaPontoConsumoSemMedicaoDiaria(){
		Response<List<PontoConsumoSemMedicaoDTO>> response = new Response<>();
		var result = service.obterPontosConsumoSemSuperMedicaoDiaria();

		response.setData(result);
		return ResponseEntity.ok(response);
	}
}