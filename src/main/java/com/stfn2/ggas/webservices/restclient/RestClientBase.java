package com.stfn2.ggas.webservices.restclient;

import com.stfn2.ggas.domain.Cep;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

public class RestClientBase {

   private final RestClient restClient;

   public RestClientBase() {
      this.restClient = RestClient.builder()
              .baseUrl("")
              .build();
   }

   public List<Cep> findAll(){
      return restClient.get()
              .uri("")
              .retrieve()
              .body(new ParameterizedTypeReference<List<Cep>>(){});
   }
}
