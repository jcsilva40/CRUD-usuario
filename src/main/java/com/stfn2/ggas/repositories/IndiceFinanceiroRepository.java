package com.stfn2.ggas.repositories;

import com.stfn2.ggas.domain.dtos.filter.IndiceFinanceiroFilterDTO;
import org.springframework.stereotype.Repository;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.IndiceFinanceiro;

@Repository
public interface IndiceFinanceiroRepository extends BaseRepository<IndiceFinanceiro, IndiceFinanceiroFilterDTO> {

}
