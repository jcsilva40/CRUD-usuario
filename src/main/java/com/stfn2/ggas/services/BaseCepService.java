package com.stfn2.ggas.services;

import com.stfn2.ggas.config.exceptions.types.ObjectNotFoundException;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.Cep;
import com.stfn2.ggas.tools.ToolStr;
import com.stfn2.ggas.webservices.cep.interfaces.CepInsterfaceDTO;
import com.stfn2.ggas.webservices.cep.viacep.ViaCepWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.BaseCep;
import com.stfn2.ggas.domain.dtos.BaseCepDTO;
import com.stfn2.ggas.domain.dtos.filter.BaseCepFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.BaseCepBasicDTO;
import com.stfn2.ggas.repositories.BaseCepRepository;

import java.util.Optional;

@Service
public class BaseCepService extends BaseService<BaseCep, BaseCepDTO, BaseCepBasicDTO, BaseCepFilterDTO, BaseCepRepository> {

    @Autowired
    private ViaCepWebService webService;

    @Autowired
    private CepService cepService;

    public BaseCepDTO findCep(String cep) {
        String cepClean = cep.replaceAll("-", "");
        Optional<BaseCep> obj = repository.findByCep(cepClean);

        if (obj.isEmpty()) {
            try {
                BaseCepDTO newDto;
                Cep cepLegado = cepService.getById(Long.parseLong(cepClean));
                if (cepLegado == null) {
                    CepInsterfaceDTO cepVia = this.webService.findEndereco(cepClean);
                    cepVia.setCep(cepClean);
                    BaseCepDTO newCep = MapperImpl.parseObject(cepVia, BaseCepDTO.class);

                    cepLegado = cepService.getOrCreate(cepVia);

                    newDto = createOrUpdate(newCep);
                } else {
                    newDto = new BaseCepDTO(cepLegado);
                    newDto = createOrUpdate(newDto);
                }
                newDto.setCepId(cepLegado.getId());
                return newDto;
            } catch (RuntimeException e) {
                throw new ObjectNotFoundException("Não foi possível consultar CEP na API!");
            }
        } else {
            BaseCepDTO response = (BaseCepDTO) obj.get().convert();
            response.setCepId(Long.parseLong(cepClean));
            response.setCep(ToolStr.aplicacarMascaraCep(response.getCep()));
            return response;
        }
    }
}


