package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.ConsumoHistorico;
import com.stfn2.ggas.domain.MedicaoHistorico;
import com.stfn2.ggas.domain.dtos.MedicaoHistoricoDTO;
import com.stfn2.ggas.domain.dtos.basic.MedicaoHistoricoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.MedicaoHistoricoFilterDTO;
import com.stfn2.ggas.repositories.MedicaoHistoricoRepository;
import com.stfn2.ggas.repositories.MedidorInstalacaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicaoHistoricoService extends BaseService<MedicaoHistorico, MedicaoHistoricoDTO, MedicaoHistoricoBasicDTO, MedicaoHistoricoFilterDTO, MedicaoHistoricoRepository> {

   @Autowired
   private ConsumoHistoricoService consumoHistoricoService;

   @Autowired
   private SuperMedicaoDiariaService superMedicaoDiariaService;

   @Autowired
   private PontoConsumoService pontoConsumoService;

   @Autowired
   private MedidorInstalacaoRepository medidorInstalacaoRepository;

   public MedicaoHistoricoBasicDTO obterUltimaLeitura(MedicaoHistoricoFilterDTO filtro){
      MedicaoHistorico medicaoHistorico = new MedicaoHistorico();
      medicaoHistorico = this.repository.obterUltimaMedicao(filtro.getPontoConsumoId(),
              filtro.getMedidorInstalacaoId());
      if(medicaoHistorico != null){
         medicaoHistorico = this.getById(medicaoHistorico.getId());
      }

      return this.entityToBasicDTO(medicaoHistorico);
   }

   @Override
   public MedicaoHistoricoBasicDTO entityToBasicDTO(MedicaoHistorico entity)
   {
      var dto = new MedicaoHistoricoBasicDTO();

      dto.setId(entity.getId());
      dto.setCiclo(entity.getCiclo());
      dto.setAnoMes(entity.getAnoMes());
      dto.setConsumoMedidor(entity.getConsumoMedidor());
      dto.setConsumoCorretor(entity.getConsumoCorretor());
      dto.setLeituraCorretor(entity.getLeituraCorretor());
      dto.setLeituraFaturada(entity.getLeituraFaturada());
      dto.setDataLeituraFaturada(entity.getDataLeituraFaturada());
      dto.setDataLeituraInformada(entity.getDataLeituraInformada());

      dto.setMedidorInstalacaoId(entity.getMedidorInstalacao().getId());
      dto.setPontoConsumoId(entity.getPontoConsumo().getId());
      dto.setPontoConsumoDescricao(entity.getPontoConsumo().getDescricao());
      dto.setFatorPTZ(entity.getFatorPTZ());

      return dto;
   }

   @Override
   public MedicaoHistoricoDTO entityToDTO(MedicaoHistorico entity)
   {
      var dto = new MedicaoHistoricoDTO();

      dto.setId(entity.getId());
      dto.setCiclo(entity.getCiclo());
      dto.setAnoMes(entity.getAnoMes());
      dto.setConsumoMedidor(entity.getConsumoMedidor());
      dto.setConsumoCorretor(entity.getConsumoCorretor());
      dto.setLeituraCorretor(entity.getLeituraCorretor());
      dto.setLeituraFaturada(entity.getLeituraFaturada());
      dto.setDataLeituraFaturada(entity.getDataLeituraFaturada());
      dto.setDataLeituraInformada(entity.getDataLeituraInformada());

      dto.setPontoConsumoId(entity.getPontoConsumo().getId());
      dto.setPontoConsumoDescricao(entity.getPontoConsumo().getDescricao());
      dto.setMedidorInstalacaoId(entity.getMedidorInstalacao().getId());

      dto.setFatorPTZ(entity.getFatorPTZ());

      return dto;
   }

   @Override
   public MedicaoHistorico dtoToEntity(MedicaoHistoricoDTO dto) {

      var object = getById(dto.getId());
      if (object == null)
      {
         object = new MedicaoHistorico();
      }

      object.setId(dto.getId());
      object.setCiclo(dto.getCiclo());
      object.setAnoMes(dto.getAnoMes());
      object.setConsumoMedidor(dto.getConsumoMedidor());
      object.setConsumoCorretor(dto.getConsumoCorretor());
      object.setLeituraCorretor(dto.getLeituraCorretor());
      object.setLeituraFaturada(dto.getLeituraFaturada());
      object.setDataLeituraFaturada(dto.getDataLeituraFaturada());
      object.setDataLeituraInformada(dto.getDataLeituraInformada());
      object.setFatorPTZ(dto.getFatorPTZ());

      object.setPontoConsumo(pontoConsumoService.getById(dto.getPontoConsumoId()));
      object.setMedidorInstalacao(medidorInstalacaoRepository.getById(dto.getMedidorInstalacaoId()));

      return object;
   }

   @Override
   public MedicaoHistorico beforeSave(MedicaoHistorico entity) {

      var superMedicaoDiaria = superMedicaoDiariaService.buscaPorPontoDeConsumo(entity.getPontoConsumo().getCil(), entity.getDataLeituraFaturada().atStartOfDay());
      superMedicaoDiaria.setLeituraSemCorrecaoFatorPTZ(entity.getLeituraCorretor());
      superMedicaoDiaria.setLeituraComCorrecaoFatorPTZ(entity.getLeituraFaturada());
      superMedicaoDiaria.setConsumoSemCorrecaoFatorPTZ(entity.getConsumoMedidor());
      superMedicaoDiaria.setConsumoComCorrecaoFatorPTZ(entity.getConsumoCorretor());
      superMedicaoDiaria.setFatorPTZ(entity.getFatorPTZ());
      superMedicaoDiariaService.save(superMedicaoDiaria);

      var consumoHistorico = consumoHistoricoService.buscaPorPontoConsumoEdataLeituraHistorico(entity.getPontoConsumo().getId(), entity.getDataLeituraInformada());
      consumoHistorico.setConsumo(entity.getConsumoCorretor());
      consumoHistorico.setConsumoMedido(entity.getConsumoMedidor());
      consumoHistorico.setConsumoApurado(entity.getConsumoMedidor());
      consumoHistorico.setFatorPTZ(entity.getFatorPTZ());
      consumoHistoricoService.save(consumoHistorico);

      return super.afterSave(entity);
   }
}