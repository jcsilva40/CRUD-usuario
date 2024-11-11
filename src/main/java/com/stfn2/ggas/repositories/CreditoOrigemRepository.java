package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.CreditoOrigem;
import com.stfn2.ggas.domain.dtos.filter.CreditoOrigemFilterDTO;

@Repository
public interface CreditoOrigemRepository extends BaseRepository<CreditoOrigem, CreditoOrigemFilterDTO> {

}