package com.stfn2.ggas.services;

import com.stfn2.ggas.config.exceptions.types.ObjectNotFoundException;
import com.stfn2.ggas.core.abstractClasses.combo.ComboDTO;
import com.stfn2.ggas.domain.RubricaTributo;
import com.stfn2.ggas.domain.RubricaValorRegulamentado;
import com.stfn2.ggas.domain.Tributo;
import com.stfn2.ggas.repositories.RubricaTributoRepository;
import com.stfn2.ggas.repositories.RubricaValorRegulamentadoRepository;
import com.stfn2.ggas.repositories.TributoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.Rubrica;
import com.stfn2.ggas.domain.dtos.RubricaDTO;
import com.stfn2.ggas.domain.dtos.filter.RubricaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.RubricaBasicDTO;
import com.stfn2.ggas.repositories.RubricaRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RubricaService extends BaseService<Rubrica, RubricaDTO, RubricaBasicDTO, RubricaFilterDTO, RubricaRepository> {

    @Autowired
    RubricaRepository rubricaRepository;
    @Autowired
    RubricaValorRegulamentadoRepository rubricaValorRegulamentadoRepository;
    @Autowired
    RubricaTributoRepository rubricaTributoRepository;
    @Autowired
    TributoRepository tributoRepository;

    public List<ComboDTO> findRubricasPorTipo(String tipo) {
        if ("credito".equalsIgnoreCase(tipo)) {
            return rubricaRepository.findRubricasCreditoCombo();
        } else if ("debito".equalsIgnoreCase(tipo)) {
            return rubricaRepository.findRubricasDebitoCombo();
        } else {
            throw new IllegalArgumentException("Tipo inválido. Deve ser 'credito' ou 'debito'.");
        }
    }

    @Override
    public RubricaBasicDTO entityToBasicDTO(Rubrica entity) {
       RubricaBasicDTO basicDTO = super.entityToBasicDTO(entity);
        if(entity.getFinanciamentoTipo() != null){
            basicDTO.setFinanciamentoTipoDescricao(entity.getFinanciamentoTipo().getDescricao());
            basicDTO.setFinanciamentoTipoId(entity.getFinanciamentoTipo().getId());
        }
        return basicDTO;
    }

    @Override
    public Rubrica dtoToEntity(RubricaDTO dto) {
        Rubrica rubrica = getById(dto.getId());

        if (Objects.isNull(rubrica)) {
            rubrica = new Rubrica();
        }
        rubrica = super.dtoToEntity(dto);

        // Criar uma referência final para rubrica
        final Rubrica finalRubrica = rubrica;

        // Configuração das associações de Tributos
        if (dto.getTributosId() != null) {
            List<RubricaTributo> tributos = dto.getTributosId().stream()
                    .map(id -> {
                        Tributo tributo = tributoRepository.findById(id)
                                .orElseThrow(() -> new ObjectNotFoundException("Tributo com ID " + id + " não encontrado."));
                        RubricaTributo rubricaTributo = new RubricaTributo();
                        rubricaTributo.setTributo(tributo);
                        rubricaTributo.setRubrica(finalRubrica);
                        rubricaTributo.setInicioVigencia(dto.getDataInicioVigenciaTributo());
                        rubricaTributo.setFimVigencia(dto.getDataFimVigenciaTributo());
                        return rubricaTributo;
                    })
                    .collect(Collectors.toList());
            rubrica.setTributos(tributos);
        } else {
            rubrica.setTributos(new ArrayList<>());
        }

        // Configuração do RubricaValorRegulamentado
        if (Boolean.TRUE.equals(dto.getRegulamentada())) {
            RubricaValorRegulamentado valorRegulamentado = rubricaValorRegulamentadoRepository
                    .findByRubricaAndHabilitado(rubrica, true)
                    .orElse(new RubricaValorRegulamentado());

            valorRegulamentado.setRubrica(rubrica);
            valorRegulamentado.setInicioVigencia(dto.getDataInicioVigenciaRegulamentada());
            valorRegulamentado.setFimVigencia(dto.getDataFimVigenciaRegulamentada());

            rubricaValorRegulamentadoRepository.save(valorRegulamentado);
        } else {
            // Remover qualquer RubricaValorRegulamentado existente
            rubricaValorRegulamentadoRepository.deleteByRubrica(rubrica);
        }

        rubrica.setUltimaAlteracao(LocalDateTime.now());

        if(rubrica.getItemFaturamento() == null){
            rubrica.setItemFaturamento(false);
        }

        return rubrica;
    }

    //verificar unidade e item contabil indo null
    @Override
    public RubricaDTO entityToDTO(Rubrica entity) {
        RubricaDTO dto = super.entityToDTO(entity);

        if (entity.getFinanciamentoTipo() != null) {
            dto.setFinanciamentoTipoId(entity.getFinanciamentoTipo().getId());
            dto.setFinanciamentoTipoDescricao(entity.getFinanciamentoTipo().getDescricao());
        }

        if (entity.getNomeclaturaComumMercosul() != null) {
            dto.setNomeclaturaComumMercosulId(entity.getNomeclaturaComumMercosul().getId());
        }

        dto.setTributosId(entity.getTributos().stream()
                .map(RubricaTributo::getTributo)
                .map(Tributo::getId)
                .collect(Collectors.toList()));

        if (!entity.getTributos().isEmpty()) {
            RubricaTributo primeiroTributo = entity.getTributos().get(0);
            dto.setDataInicioVigenciaTributo(primeiroTributo.getInicioVigencia());
            dto.setDataFimVigenciaTributo(primeiroTributo.getFimVigencia());
        } else {
            dto.setDataInicioVigenciaTributo(null);
            dto.setDataFimVigenciaTributo(null);
        }

        // Gerenciar RubricaValorRegulamentado
        Optional<RubricaValorRegulamentado> valorRegulamentado =
                rubricaValorRegulamentadoRepository.findByRubricaAndHabilitado(entity, true);

        if (valorRegulamentado.isPresent()) {
            dto.setRegulamentada(true);
            dto.setDataInicioVigenciaRegulamentada(valorRegulamentado.get().getInicioVigencia());
            dto.setDataFimVigenciaRegulamentada(valorRegulamentado.get().getFimVigencia());
        } else {
            dto.setRegulamentada(false);
            dto.setDataInicioVigenciaRegulamentada(null);
            dto.setDataFimVigenciaRegulamentada(null);
        }

        return dto;
    }


    @Override
    @Transactional
    public RubricaDTO createOrUpdate(RubricaDTO dto) {
        Rubrica rubrica = dtoToEntity(dto);
        rubrica.updateVersao();

        rubrica = rubricaRepository.save(rubrica);
        // RubricaValorRegulamentado já é tratado no dtoToEntity

        return entityToDTO(rubrica);
    }
    @Override
    @Transactional
    public void delete(Long id) {
        Rubrica rubrica = getById(id);
        if (rubrica == null) {
            throw new ObjectNotFoundException("Rubrica com ID " + id + " não encontrada.");
        }

        // Remover RubricaValorRegulamentado associada, se existir
        rubricaValorRegulamentadoRepository.deleteByRubrica(rubrica);

        // Remover RubricaTributo associada, se existir
        rubricaTributoRepository.deleteByRubrica(rubrica);

        // Remover a própria Rubrica
        rubricaRepository.delete(rubrica);
    }



}

