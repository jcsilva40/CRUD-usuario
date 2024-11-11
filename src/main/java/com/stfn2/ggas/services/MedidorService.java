package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.CertificadoMedidor;
import com.stfn2.ggas.domain.Medidor;
import com.stfn2.ggas.domain.MedidorMarca;
import com.stfn2.ggas.domain.MedidorModelo;
import com.stfn2.ggas.domain.dtos.MedidorDTO;
import com.stfn2.ggas.domain.dtos.basic.MedicaoHistoricoBasicDTO;
import com.stfn2.ggas.domain.dtos.basic.MedidorBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.MedicaoHistoricoFilterDTO;
import com.stfn2.ggas.domain.dtos.filter.MedidorFilterDTO;
import com.stfn2.ggas.domain.enumTypes.MedidorSituacaoEnum;
import com.stfn2.ggas.repositories.MedidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedidorService extends BaseService<Medidor, MedidorDTO, MedidorBasicDTO, MedidorFilterDTO, MedidorRepository> {

   @Autowired
   private MedicaoHistoricoService medicaoHistoricoService;

   @Autowired
   private MedidorMarcaService medidorMarcaService;

   @Autowired
   private MedidorModeloService medidorModeloService;

   @Autowired
   private CertificadoMedidorService certificadoMedidorService;


   @Override
   public Medidor dtoToEntity(MedidorDTO dto) {

      Medidor medidor = getById(dto.getId());
      if(medidor == null){
         medidor = new Medidor();
      }
      MedidorMarca marca = medidorMarcaService.getById(dto.getMedidorMarcaId());
      MedidorModelo modelo = medidorModeloService.getById(dto.getMedidorModeloId());
      if(dto.getMedidorCertificadoId() != null){
         CertificadoMedidor certificado = certificadoMedidorService.getById(dto.getMedidorCertificadoId());
         if(certificado != null){
            medidor.setCertificadoMedidor(certificado);
         }
      }
      medidor.setMedidorMarca(marca);
      medidor.setMedidorModelo(modelo);
      medidor.setDescricao(dto.getDescricao());
      medidor.setDataAquisicao(dto.getDataAquisicao());
      medidor.setPressaoAtual(dto.getPressaoMedicao());

       return medidor;
   }

   @Override
   public MedidorDTO entityToDTO(Medidor entity) {

      MedidorDTO dto = super.entityToDTO(entity);
      dto.setId(entity.getId());
      dto.setMedidorMarcaId(entity.getMedidorMarca().getId());
      dto.setMedidorModeloId(entity.getMedidorModelo().getId());
      if(entity.getCertificadoMedidor() != null){
         dto.setMedidorCertificadoId(entity.getCertificadoMedidor().getId());
      }
      dto.setPressaoMedicao(entity.getPressaoAtual());

      return dto;
   }

   public MedicaoHistoricoBasicDTO obterUltimaLeitura(MedicaoHistoricoFilterDTO filter) {
      MedicaoHistoricoBasicDTO ultimamedicao = new MedicaoHistoricoBasicDTO();
      ultimamedicao = medicaoHistoricoService.obterUltimaLeitura(filter);
      return ultimamedicao;
   }

   public List<MedidorDTO> obterListaMedidoresDisponiveis(){
      return repository.obterListaMedidoresDisponiveis(MedidorSituacaoEnum.INSTALADO);
   }
}