package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.SegmentoPai;
import com.stfn2.ggas.domain.dtos.SegmentoPaiDTO;
import com.stfn2.ggas.domain.dtos.basic.SegmentoPaiBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.SegmentoPaiFilterDTO;
import com.stfn2.ggas.repositories.SegmentoPaiRepository;
import com.stfn2.ggas.services.SegmentoPaiService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/segmentoPai")
@Tag(name="SegmentoPai", description="EndPoints para gerenciamento de SegmentoPai")
public class SegmentoPaiController extends BaseController<SegmentoPai, SegmentoPaiDTO, SegmentoPaiBasicDTO, SegmentoPaiFilterDTO,
		SegmentoPaiRepository,SegmentoPaiService> {
}