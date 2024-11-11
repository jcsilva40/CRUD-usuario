package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.Banco;
import com.stfn2.ggas.domain.EntidadeConteudo;
import com.stfn2.ggas.domain.IndiceFinanceiroValorNominal;
import com.stfn2.ggas.domain.SuperMedicaoDiaria;
import com.stfn2.ggas.domain.dtos.BancoDTO;
import com.stfn2.ggas.domain.dtos.IndiceFinanceiroValorNominalDTO;
import com.stfn2.ggas.domain.dtos.PontoConsumoDTO;
import com.stfn2.ggas.domain.dtos.SuperMedicaoDiariaDTO;
import com.stfn2.ggas.domain.dtos.basic.BancoBasicDTO;
import com.stfn2.ggas.domain.dtos.basic.SuperMedicaoDiariaBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.BancoFilterDTO;
import com.stfn2.ggas.domain.dtos.filter.PontoConsumoFilterDTO;
import com.stfn2.ggas.domain.dtos.filter.SuperMedicaoDiariaFilterDTO;
import com.stfn2.ggas.domain.enumTypes.SuperMedicaoAnormalidadeEnum;
import com.stfn2.ggas.repositories.BancoRepository;
import com.stfn2.ggas.repositories.SuperMedicaoDiariaRepository;
import com.stfn2.ggas.services.componentes.faturamentogruporota.SuperMedicaoDiariaComponent;
import com.stfn2.ggas.services.componentes.faturamentogruporota.dto.PontoConsumoSemMedicaoDTO;
import com.stfn2.ggas.services.componentes.faturamentogruporota.vo.FaturamentoGrupoVO;
import com.stfn2.ggas.tools.ToolDate;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Service
public class SuperMedicaoDiariaService extends BaseService<SuperMedicaoDiaria,  SuperMedicaoDiariaDTO, SuperMedicaoDiariaBasicDTO, SuperMedicaoDiariaFilterDTO, SuperMedicaoDiariaRepository> {

    @Autowired
    private SuperMedicaoDiariaComponent superMedicaoDiariaComponent;
    @Autowired
    private EntidadeConteudoService entidadeConteudoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private SuperMedicaoDiariaRepository superMedicaoDiariaRepository;

    public List<PontoConsumoSemMedicaoDTO> obterPontosConsumoSemSuperMedicaoDiaria() {
        return superMedicaoDiariaComponent.obterPontosConsumoSemSuperMedicaoDiaria();
    }

    public SuperMedicaoDiaria buscaPorPontoDeConsumo(String cil, LocalDateTime dataLeituraInformada) {
        return superMedicaoDiariaRepository.buscaPorPontoDeConsumo(cil, dataLeituraInformada);
    }

    @Override
    public SuperMedicaoDiaria dtoToEntity(SuperMedicaoDiariaDTO dto) {
        var object = new SuperMedicaoDiaria();
        object.setId(dto.getId());
        object.setVersao(dto.getVersao());
        object.setHabilitado(dto.getHabilitado());
        object.setAnoMes(dto.getAnoMes());
        object.setNumeroCiclo(dto.getNumeroCiclo());
        object.setCil(dto.getCil());
        object.setLeituraSemCorrecaoFatorPTZ(dto.getLeituraSemCorrecaoFatorPTZ());
        object.setLeituraComCorrecaoFatorPTZ(dto.getLeituraComCorrecaoFatorPTZ());
        object.setConsumoSemCorrecaoFatorPTZ(dto.getConsumoSemCorrecaoFatorPTZ());
        object.setConsumoComCorrecaoFatorPTZ(dto.getConsumoComCorrecaoFatorPTZ());
        object.setPressao(dto.getPressao());
        object.setTemperatura(dto.getTemperatura());
        object.setFatorPTZ(dto.getFatorPTZ());
        object.setFatorZ(dto.getFatorZ());
        object.setIndicadorIntegrado(dto.getIndicadorIntegrado());
        object.setIndicadorProcessado(dto.getIndicadorProcessado());
        object.setIndicadorConsolidada(dto.getIndicadorConsolidada());
        object.setIndicadorMedidor(dto.getIndicadorMedidor());

        object.setDataRegistroLeitura(LocalDateTime.now());
        object.setDataRealizacaoLeitura(LocalDate.parse(dto.getDataRealizacaoLeitura()).atStartOfDay());

        if (dto.getSupervisorioMedicaoAnormalidadeId() != 0)
        {
            object.setSuperMedicaoAnormalidade(SuperMedicaoAnormalidadeEnum.toEnum(dto.getSupervisorioMedicaoAnormalidadeId()));
        }

        if (dto.getStatusId() != 0)
        {
            object.setStatus(entidadeConteudoService.getById(dto.getStatusId()));
        }

        //Probably should take the id from the JWT here
        object.setUltimoUsuarioAlteracao(usuarioService.getById(dto.getUltimoUsuarioAlteracaoId()));

        return object;
    }
}