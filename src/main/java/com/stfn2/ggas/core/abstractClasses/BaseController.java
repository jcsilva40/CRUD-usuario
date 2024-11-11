package com.stfn2.ggas.core.abstractClasses;

import com.stfn2.ggas.core.abstractClasses.combo.ComboDTO;
import com.stfn2.ggas.core.event.ObjectCreateEvent;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.core.models.Response;
import com.stfn2.ggas.core.utils.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseController<Entity extends BaseEntity, DTO extends BaseDTO, BasicDTO extends BaseDTO, Filter extends FilterDTO,
        Repository extends BaseRepository<Entity, Filter>,  Service extends BaseService<Entity, DTO, BasicDTO, Filter, Repository >> {

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	@Getter
	protected Service service;

	@PostMapping(
			consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}
	)
	@Operation(
			summary = "Cria um objeto",
			responses = {
					@ApiResponse(description = "Sucess", responseCode = "200", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	public ResponseEntity<Response<DTO>> create(HttpServletResponse resp, HttpServletRequest req,
																							@Valid @RequestBody DTO objDto, BindingResult result) {
		Response<DTO> response = new Response<>();
		DTO objCreate = service.createOrUpdate(objDto);
		response.setData(objCreate);
		publisher.publishEvent(new ObjectCreateEvent(this, resp, objCreate.getId().toString()));
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping(value = "/{id}",
			consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}
	)
	@Operation(
			summary = "Atualiza um objeto",
			responses = {
					@ApiResponse(description = "Sucess", responseCode = "200", content = @Content),
					@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	public ResponseEntity<Response<DTO>> update(@Valid @RequestBody DTO objDto, @PathVariable Long id) {
		Response<DTO> response = new Response<>();
		response.setData(service.createOrUpdate(objDto));
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/filter", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(
			summary = "Busca todos de acordo com o filtro",
			responses = {
					@ApiResponse(description = "Sucess", responseCode = "200", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	public ResponseEntity<Response<List<BasicDTO>>> filter(Filter filter) {
		Response<List<BasicDTO>> response = new Response<>();
		response.setData(service.filter(filter));
		return ResponseEntity.ok(response);
	}

	@GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(
			summary = "Busca todos os objetos",
			responses = {
					@ApiResponse(description = "Sucess", responseCode = "200", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	public ResponseEntity<Response<List<BasicDTO>>> findAll() {
		Response<List<BasicDTO>> response = new Response<>();
		response.setData(service.findAll());
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/page", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(
			summary = "Busca todos paginado de acordo com o filtro",
			responses = {
					@ApiResponse(description = "Sucess", responseCode = "200", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)

	public ResponseEntity<Response<Page<BasicDTO>>> findAllPage(HttpServletRequest req, Filter filter, Pageable pageable) {
		Response<Page<BasicDTO>> response = new Response<>();
		response.setData(service.findAll(filter, pageable));
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(
			summary = "Busca objeto por ID",
			responses = {
					@ApiResponse(description = "Sucess", responseCode = "200", content = @Content),
					@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	public ResponseEntity<Response<DTO>> findById(@PathVariable Long id) {
		Response<DTO> response = new Response<>();
		DTO obj = service.findById(id);
		if (obj == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(obj);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	@Operation(
			summary = "Deleta um objeto",
			responses = {
					@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	public ResponseEntity<String> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/combo", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(
			summary = "Devolve um objeto com descrição e id para montagem de um ComboBox",
			responses = {
					@ApiResponse(description = "Sucess", responseCode = "200", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	public ResponseEntity<Response<List<ComboDTO>>> getCombo() {
		Response<List<ComboDTO>> response = new Response<>();
		response.setData(service.getCombo());
		return ResponseEntity.ok(response);
	}

	@PostMapping(
			value = "/saveList",
			consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}
	)
	@Operation(
			summary = "Cria ou atualiza uma lista de objetos",
			responses = {
					@ApiResponse(description = "Sucesso", responseCode = "200", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	public ResponseEntity<Response<List<DTO>>> saveList(
			@Valid @RequestBody List<DTO> dtos,	BindingResult result) {
		Response<List<DTO>> response = new Response<>();
		List<DTO> savedDtos = service.createOrUpdateList(dtos);
		response.setData(savedDtos);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
