package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Banco;
import com.stfn2.ggas.domain.dtos.filter.BancoFilterDTO;

@Repository
public interface BancoRepository extends BaseRepository<Banco, BancoFilterDTO> {

}