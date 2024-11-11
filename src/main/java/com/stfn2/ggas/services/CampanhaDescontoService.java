package com.stfn2.ggas.services;

import com.stfn2.ggas.config.security.domain.User;
import com.stfn2.ggas.config.security.domain.UserServices;
import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.core.abstractClasses.combo.ComboDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.CampanhaDesconto;
import com.stfn2.ggas.domain.LogCampanhaDesconto;
import com.stfn2.ggas.domain.SegmentoPai;
import com.stfn2.ggas.domain.dtos.CampanhaDescontoDTO;
import com.stfn2.ggas.domain.dtos.basic.CampanhaDescontoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.CampanhaDescontoFilterDTO;
import com.stfn2.ggas.domain.enumTypes.LogCampanhaDescontoEnum;
import com.stfn2.ggas.domain.enumTypes.ModalidadeMedicaoEnum;
import com.stfn2.ggas.domain.enumTypes.TipoCampanhaDescontoEnum;
import com.stfn2.ggas.domain.enumTypes.TipoDescontoCadeEnum;
import com.stfn2.ggas.repositories.CampanhaDescontoRepository;
import com.stfn2.ggas.tools.ToolDate;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class CampanhaDescontoService extends BaseService<CampanhaDesconto, CampanhaDescontoDTO, CampanhaDescontoBasicDTO, CampanhaDescontoFilterDTO, CampanhaDescontoRepository> {
    
    @Autowired
    private LogCampanhaDescontoService logCampanhaDescontoService;
    
    @Autowired
    private UserServices userService;
    
    @Override
    public Page<CampanhaDescontoBasicDTO> findAll(CampanhaDescontoFilterDTO filter, Pageable pageable) {  
        String descricao = filter.getDescricao();
        /*Long segmentoCampanha = filter.getSegmentoCampanha();
        SegmentoCampanhaEnum segmentoCampanhaEnum = null;
        if(!Objects.isNull(segmentoCampanha)){
            segmentoCampanhaEnum = SegmentoCampanhaEnum.toEnum(segmentoCampanha);
        }*/        
        Long segmentoPaiId = filter.getSegmentoPaiId();        
        Long modalidadeMedicao = filter.getModalidadeMedicao();
        ModalidadeMedicaoEnum modalidadeMedicaoEnum = null;
        if(!Objects.isNull(modalidadeMedicao)){
            modalidadeMedicaoEnum = ModalidadeMedicaoEnum.toEnum(modalidadeMedicao);
        }          
        Boolean habilitado = filter.getHabilitado();
        Page<CampanhaDescontoBasicDTO> result = this.repository.findAll(descricao, segmentoPaiId, modalidadeMedicaoEnum, habilitado, pageable).map(projection -> MapperImpl.parseObject(projection, CampanhaDescontoBasicDTO.class));
        return result;
    }
    
    @Override
    public CampanhaDescontoDTO entityToDTO(CampanhaDesconto campanhaDesconto){
        CampanhaDescontoDTO campanhaDescontoDTO;
        campanhaDescontoDTO = super.entityToDTO(campanhaDesconto);
        campanhaDescontoDTO.setNomeCampanha(campanhaDesconto.getDescricao());
        /*if(!Objects.isNull(campanhaDesconto.getSegmentoCampanha())){
            Long segmentoCampanhaId = campanhaDesconto.getSegmentoCampanha().getId();
            campanhaDescontoDTO.setSegmentoCampanhaId(segmentoCampanhaId);
        }*/
        if(!Objects.isNull(campanhaDesconto.getTipoCampanha())){
            Long tipoCampanhaId = campanhaDesconto.getTipoCampanha().getId();
            campanhaDescontoDTO.setTipoCampanhaId(tipoCampanhaId);
        }
        if(!Objects.isNull(campanhaDesconto.getTipoDesconto())){
            Long tipoDescontoId = campanhaDesconto.getTipoDesconto().getId();
            campanhaDescontoDTO.setTipoDescontoId(tipoDescontoId);
        }
        if(!Objects.isNull(campanhaDesconto.getSegmentoPai())){
            Long segmentoPaiId = campanhaDesconto.getSegmentoPai().getId();            
            campanhaDescontoDTO.setSegmentoPaiId(segmentoPaiId);
        }
        if(!Objects.isNull(campanhaDesconto.getModalidadeMedicao())){
            Long modalidadeMedicaoId = campanhaDesconto.getModalidadeMedicao().getId();
            campanhaDescontoDTO.setModalidadeMedicaoId(modalidadeMedicaoId);
        }
        return campanhaDescontoDTO;
    }
    
    @Override
    public CampanhaDesconto dtoToEntity(CampanhaDescontoDTO campanhaDescontoDTO){
        CampanhaDesconto campanhaDesconto;
        campanhaDesconto = super.dtoToEntity(campanhaDescontoDTO);
        campanhaDesconto.setDescricao(campanhaDescontoDTO.getNomeCampanha());
        /*if(!Objects.isNull(campanhaDescontoDTO.getSegmentoCampanhaId())){
            SegmentoCampanhaEnum segmentoCampanha = SegmentoCampanhaEnum.toEnum(campanhaDescontoDTO.getSegmentoCampanhaId());
            campanhaDesconto.setSegmentoCampanha(segmentoCampanha);
        }*/
        if(!Objects.isNull(campanhaDescontoDTO.getTipoCampanhaId())){
            TipoCampanhaDescontoEnum tipoCampanhaDesconto = TipoCampanhaDescontoEnum.toEnum(campanhaDescontoDTO.getTipoCampanhaId());
            campanhaDesconto.setTipoCampanha(tipoCampanhaDesconto);
        }
        if(!Objects.isNull(campanhaDescontoDTO.getTipoDescontoId())){
            TipoDescontoCadeEnum tipoDescontoCade = TipoDescontoCadeEnum.toEnum(campanhaDescontoDTO.getTipoDescontoId());
            campanhaDesconto.setTipoDesconto(tipoDescontoCade);
        }
        if(!Objects.isNull(campanhaDescontoDTO.getSegmentoPaiId())){
            SegmentoPai segmentoPai = new SegmentoPai();
            segmentoPai.setId(campanhaDescontoDTO.getSegmentoPaiId());
            campanhaDesconto.setSegmentoPai(segmentoPai);
        }
        if(!Objects.isNull(campanhaDescontoDTO.getModalidadeMedicaoId())){
            ModalidadeMedicaoEnum modalidadeMedicao = ModalidadeMedicaoEnum.toEnum(campanhaDescontoDTO.getModalidadeMedicaoId());
            campanhaDesconto.setModalidadeMedicao(modalidadeMedicao);
        }
        return campanhaDesconto;
    }
    
    @Override
    public CampanhaDescontoDTO createOrUpdate(CampanhaDescontoDTO campanhaDescontoDTO) {
        
        CampanhaDesconto campanhaDesconto = dtoToEntity(campanhaDescontoDTO);
        boolean isAlterar = false;
        boolean isDeletar = false;
        if(!Objects.isNull(campanhaDesconto.getId()) && campanhaDesconto.getId() != 0){
            isAlterar = true;
        }
        campanhaDesconto = save(campanhaDesconto);
        LogCampanhaDesconto logCampanhaDesconto = montarLogCampanhaDesconto(campanhaDesconto, isAlterar, isDeletar);
        logCampanhaDescontoService.save(logCampanhaDesconto);
        return entityToDTO(campanhaDesconto);
    }
    
    private LogCampanhaDesconto montarLogCampanhaDesconto(CampanhaDesconto campanhaDesconto, boolean isAlterar, boolean isExcluir){
        LogCampanhaDesconto logCampanhaDesconto = new LogCampanhaDesconto();
        logCampanhaDesconto.setCampanhaDesconto(campanhaDesconto);
        logCampanhaDesconto.setDataExecucao(ToolDate.getDataAtualLocalDateTime());
        if(isExcluir){
            logCampanhaDesconto.setDescricaoAcao(LogCampanhaDescontoEnum.EXCLUIR.getDescricao());
        }
        else if(isAlterar){
            logCampanhaDesconto.setDescricaoAcao(LogCampanhaDescontoEnum.ALTERAR.getDescricao());            
        }
        else{
            logCampanhaDesconto.setDescricaoAcao(LogCampanhaDescontoEnum.INSERIR.getDescricao());
        }
        User user = userService.getAuthenticatedUsername();
        logCampanhaDesconto.setUsuario(user);
        return logCampanhaDesconto;
    }
    
    public List<ComboDTO> findCampanhaDescontoFormatada(){
        return this.repository.findCampanhaDescontoFormatada();
    }
    
    @Override
    public void delete(Long id) {        
        CampanhaDesconto campanhaDesconto = getById(id);
        campanhaDesconto.setHabilitado(false);
        campanhaDesconto = save(campanhaDesconto);
        boolean isAlterar = false;
        boolean isDeletar = true;
        LogCampanhaDesconto logCampanhaDesconto = montarLogCampanhaDesconto(campanhaDesconto, isAlterar, isDeletar);        
        logCampanhaDescontoService.save(logCampanhaDesconto);
    }
    
}