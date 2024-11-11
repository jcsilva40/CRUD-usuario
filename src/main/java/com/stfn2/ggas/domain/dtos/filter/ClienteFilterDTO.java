package com.stfn2.ggas.domain.dtos.filter;


import com.stfn2.ggas.core.abstractClasses.FilterDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteFilterDTO extends FilterDTO {
    
    private String nome;
}
