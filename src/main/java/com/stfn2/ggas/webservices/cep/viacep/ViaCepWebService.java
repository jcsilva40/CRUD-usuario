package com.stfn2.ggas.webservices.cep.viacep;

import com.stfn2.ggas.webservices.cep.viacep.model.EnderecoViaCepDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "https://viacep.com.br")
public interface ViaCepWebService {
    @GetMapping("/ws/{cep}/json/")
    EnderecoViaCepDTO findEndereco(@PathVariable("cep") String cep);
}