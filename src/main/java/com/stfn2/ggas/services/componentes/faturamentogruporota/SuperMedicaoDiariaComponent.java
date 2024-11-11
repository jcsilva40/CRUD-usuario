package com.stfn2.ggas.services.componentes.faturamentogruporota;

import com.stfn2.ggas.domain.FaturamentoGrupo;
import com.stfn2.ggas.domain.PontoConsumo;
import com.stfn2.ggas.domain.Rota;
import com.stfn2.ggas.repositories.SuperMedicaoDiariaRepository;
import com.stfn2.ggas.services.FaturamentoGrupoService;
import com.stfn2.ggas.services.PontoConsumoService;
import com.stfn2.ggas.services.RotaService;
import com.stfn2.ggas.services.SuperMedicaoDiariaService;
import com.stfn2.ggas.services.componentes.faturamentogruporota.dto.PontoConsumoSemMedicaoDTO;
import com.stfn2.ggas.services.componentes.faturamentogruporota.vo.FaturamentoGrupoVO;
import com.stfn2.ggas.services.componentes.faturamentogruporota.vo.PontoConsumoRotaVO;
import com.stfn2.ggas.services.componentes.faturamentogruporota.vo.RotaFaturamentoGrupoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SuperMedicaoDiariaComponent {

    @Autowired
    private FaturamentoGrupoService faturamentoGrupoService;

    @Autowired
    private RotaService rotaService;

    @Autowired
    private PontoConsumoService pontoConsumoService;

    @Autowired
    private SuperMedicaoDiariaRepository superMedicaoDiariaRepository;

    public List<PontoConsumoSemMedicaoDTO> obterPontosConsumoSemSuperMedicaoDiaria(){

        var listaPontoConsumoSemMedicao = new ArrayList<PontoConsumoSemMedicaoDTO>();

        var listaFaturamentoGrupo = faturamentoGrupoService.listaFaturamentoGrupo();
        for (FaturamentoGrupoVO faturamentoGrupoVO : listaFaturamentoGrupo) {
            var listaRota = rotaService.listaRotaFaturamentoGrupo(faturamentoGrupoVO.getId());
            for (RotaFaturamentoGrupoVO rotaFaturamentoGrupoVO : listaRota){
                //Pega lista de ponto de consumo da rota
                var listaPontosConsumo = pontoConsumoService.listaPontoConsumoRota(rotaFaturamentoGrupoVO.getId());

                //Pega apenas a lista de cil desta lista de pontos de consumo da rota
                List<String> listaCils = new ArrayList<>(listaPontosConsumo.stream()
                        .map(PontoConsumoRotaVO::getCil)
                        .toList());

                //Pega a lista de cil presentes na super medicao diaria pela rota (pelo cil), anoMes, ciclo
                var listaCilsPontoConsumoPresente = superMedicaoDiariaRepository.listaIdsPontoConsumoSemSuperMedicaoDiaria(faturamentoGrupoVO.getAnoMes(), faturamentoGrupoVO.getCiclo(), listaCils);

                //Deixa apenas o que não existe na super medicao diaria
                listaCils.removeAll(listaCilsPontoConsumoPresente);

                List<PontoConsumoRotaVO> listaPontoConsumoSemSuperMedicaoDiaria = listaPontosConsumo.stream()
                        .filter(t -> listaCils.contains(t.getCil()))
                        .toList();

                montarRetornoPontoConsumoSemSuperMedicaoDiaria(faturamentoGrupoVO, rotaFaturamentoGrupoVO, listaPontoConsumoSemSuperMedicaoDiaria, listaPontoConsumoSemMedicao);

                rotaFaturamentoGrupoVO.setPontosDeConsumo(listaPontosConsumo);
            }
            faturamentoGrupoVO.setRotas(listaRota);
        }
        return listaPontoConsumoSemMedicao;
    }

    private void montarRetornoPontoConsumoSemSuperMedicaoDiaria(FaturamentoGrupoVO faturamentoGrupoVO, RotaFaturamentoGrupoVO rotaFaturamentoGrupoVO, List<PontoConsumoRotaVO> pontoConsumoRotaVOList, List<PontoConsumoSemMedicaoDTO> pontoConsumoSemMedicaoDTOList){
        if (pontoConsumoRotaVOList.isEmpty()){
            return;
        }
        for (PontoConsumoRotaVO pontoConsumoRotaVO : pontoConsumoRotaVOList) {
            PontoConsumoSemMedicaoDTO pontoConsumoSemMedicaoDTO = new PontoConsumoSemMedicaoDTO();
            pontoConsumoSemMedicaoDTO.setFaturamentoDescricao(faturamentoGrupoVO.getDescricao());
            pontoConsumoSemMedicaoDTO.setAnoMes(faturamentoGrupoVO.getAnoMes());
            pontoConsumoSemMedicaoDTO.setCiclo(faturamentoGrupoVO.getCiclo());
            pontoConsumoSemMedicaoDTO.setRotaId(rotaFaturamentoGrupoVO.getId());
            pontoConsumoSemMedicaoDTO.setRotaDescricao(rotaFaturamentoGrupoVO.getDescricao());
            pontoConsumoSemMedicaoDTO.setCil(pontoConsumoRotaVO.getCil());
            pontoConsumoSemMedicaoDTO.setPontoConsumoDescricao(pontoConsumoRotaVO.getDescricao());

            pontoConsumoSemMedicaoDTOList.add(pontoConsumoSemMedicaoDTO);
        }
    }
}
