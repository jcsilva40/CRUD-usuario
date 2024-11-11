package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Funcionario;
import com.stfn2.ggas.domain.dtos.filter.FuncionarioFilterDTO;

@Repository
public interface FuncionarioRepository extends BaseRepository<Funcionario, FuncionarioFilterDTO> {

}
