package com.stfn2.ggas.services;

import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.Empresa;
import com.stfn2.ggas.domain.dtos.EmpresaDTO;
import com.stfn2.ggas.domain.dtos.basic.EmpresaBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.EmpresaFilterDTO;
import com.stfn2.ggas.repositories.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EmpresaService extends BaseService<Empresa, EmpresaDTO, EmpresaBasicDTO, EmpresaFilterDTO, EmpresaRepository> {

   public Empresa obterEmpresaPrincipal() throws NegocioException {
      return repository.obterEmpresaPrincipal();
   }

   public List<Empresa> consultarDadosEmpresas(Map<String, Object> filtro) throws NegocioException {

      List<Empresa> empresas = new ArrayList<>();
      /*Criteria criteria = getCriteria();
      criteria.createAlias("cliente", "cliente");
      criteria.setProjection(Projections.projectionList()
              .add(Projections.distinct(Projections.property("chavePrimaria")), "chavePrimaria")
              .add(Projections.property("cliente.chavePrimaria"), "cliente_chavePrimaria")
              .add(Projections.property("cliente.nome"), "cliente_nome")
              .add(Projections.property("cliente.cnpj"), "cliente_cnpj"));

      filtroConsultaEmpresas(filtro, criteria);

      criteria.addOrder(Order.asc("cliente.nome"));
      criteria.setResultTransformer(new GGASTransformer(getClasseEntidade(), super.getSessionFactory().getAllClassMetadata()));
*/
      return empresas;
   }
}

