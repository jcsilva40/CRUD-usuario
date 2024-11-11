package com.stfn2.ggas.services;

import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.*;
import com.stfn2.ggas.domain.dtos.ImovelDTO;
import com.stfn2.ggas.domain.dtos.RotaDTO;
import com.stfn2.ggas.domain.dtos.basic.RotaBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.RotaFilterDTO;
import com.stfn2.ggas.repositories.RotaRepository;
import com.stfn2.ggas.services.componentes.faturamentogruporota.vo.RotaFaturamentoGrupoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RotaService extends BaseService<Rota, RotaDTO, RotaBasicDTO, RotaFilterDTO, RotaRepository> {

   @Autowired
   protected LeituraTipoService serviceLeituraTipo;

   @Autowired
   protected EmpresaService serviceEmpresa;

   @Autowired
   protected LeituristaService serviceLeiturista;

   @Autowired
   protected PeriodicidadeService servicePeriodicidade;

   @Autowired
   protected FaturamentoGrupoService serviceFaturamentogrupo;

   @Autowired
   protected PontoConsumoService servicePontoConsumo;

   @Autowired
   protected ImovelService serviceImovel;

   private List<PontoConsumo> listaPontoConsumo;

   @Override
   public Page<RotaBasicDTO> findAll(RotaFilterDTO filter, Pageable pageable) {
      String faturamentoGrupo = filter.getFaturamentoGrupoDescricao();
      String periodicidade = filter.getPeriodicidadeDescricao();
      String descricao = filter.getDescricao();
      Integer quantidadeImoveis = filter.getQuantidadeImoveis();
      Boolean habilitado = filter.getHabilitado();

      Page<RotaBasicDTO> result = this.repository.findAll(descricao, faturamentoGrupo, periodicidade, quantidadeImoveis, habilitado, pageable).map(projection -> MapperImpl.parseObject(projection, RotaBasicDTO.class));
      return result;
   }

   public List<RotaFaturamentoGrupoVO> listaRotaFaturamentoGrupo(Long faturamentoGrupoId) {
      return repository.listaRotaFaturamentoGrupo(faturamentoGrupoId);
   }

   public RotaDTO createOrUpdate(RotaDTO dto) {

      LeituraTipo leituraTipo = serviceLeituraTipo.getById(1L);
      Empresa empresa = serviceEmpresa.getById(1L);
      Leiturista leiturista = serviceLeiturista.getById(2L);
      Periodicidade periodicidade = servicePeriodicidade.getById(dto.getPeriodicidadeId());
      FaturamentoGrupo faturamentoGrupo = serviceFaturamentogrupo.getById(dto.getFaturamentoGrupoId());

      Rota rota = getById(dto.getId());
      if (rota == null) {
         rota = new Rota();
         rota.setDescricao(dto.getDescricao());
         rota.setLeituraTipo(leituraTipo);
         rota.setEmpresa(empresa);
         rota.setLeiturista(leiturista);
         rota.setPeriodicidade(periodicidade);
         rota.setFaturamentoGrupo(faturamentoGrupo);
         if (dto.getImovelId() != 0) {
            Imovel imovel = serviceImovel.getById(dto.getImovelId());
            listaPontoConsumo = servicePontoConsumo.obterListaPontoConsumoPorImovel(dto.getImovelId());
            imovel.setRota(rota);
         }

         super.save(rota);

      } else {
         if (!dto.getListaImoveisARemanejar().isEmpty()) {

            for (Long i : dto.getListaImoveisARemanejar()) {
               Imovel imovelTroca = serviceImovel.getById(i);
               List<PontoConsumo> listaPontoConsumoTroca = servicePontoConsumo.obterListaPontoConsumoPorImovel(imovelTroca.getId());
               Rota novaRota = getById(dto.getNovaRotaId());
               imovelTroca.setRota(novaRota);
               for (PontoConsumo p : listaPontoConsumoTroca) {
                  p.setRota(novaRota);
               }
            }
         }
         rota.setDescricao(dto.getDescricao());
         rota.setLeituraTipo(leituraTipo);
         rota.setEmpresa(empresa);
         rota.setLeiturista(leiturista);
         rota.setPeriodicidade(periodicidade);
         rota.setFaturamentoGrupo(faturamentoGrupo);
         if (dto.getImovelId() != null) {
            if(dto.getImovelId() != 0){
               Imovel imovel = serviceImovel.getById(dto.getImovelId());
               listaPontoConsumo = servicePontoConsumo.obterListaPontoConsumoPorImovel(dto.getImovelId());
               imovel.setRota(rota);
            }
         }
         super.save(rota);
      }

      return entityToDTO(rota);
   }

   @Override
   public RotaDTO entityToDTO(Rota entity) {

      RotaDTO dto = super.entityToDTO(entity);

      dto.setPeriodicidadeId(entity.getPeriodicidade().getId());
      dto.setFaturamentoGrupoId(entity.getFaturamentoGrupo().getId());

      dto.setImoveis(entity.getListaImoveis().stream()
              .map(ImovelDTO::new)
              .collect(Collectors.toList()));

      return dto;
   }

   @Override
   public Rota afterSave(Rota entity) {

      if (listaPontoConsumo != null) {
         for (PontoConsumo p : listaPontoConsumo) {
            p.setRota(entity);
         }
      }
      return entity;
   }

   @Override
   public void delete(Long id) {

      Rota rota = getById(id);
      if (!rota.getListaImoveis().isEmpty()) {

         for (Imovel i : rota.getListaImoveis()) {

            listaPontoConsumo = servicePontoConsumo.obterListaPontoConsumoPorImovel(i.getId());
            for (PontoConsumo p : listaPontoConsumo) {
               p.setRota(null);
            }
            i.setRota(null);
         }

      }
      super.delete(id);
   }

   public List<Rota> listarRotasPorGrupoFaturamento(Long chavePrimariaGrupoFaturamento) throws NegocioException {

      List<Rota> rotas = new ArrayList<>();
      rotas = repository.obterRotasPeloFaturamentoGrupo(chavePrimariaGrupoFaturamento);

      return rotas;
   }
}