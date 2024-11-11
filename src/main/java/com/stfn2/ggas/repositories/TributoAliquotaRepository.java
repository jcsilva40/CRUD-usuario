package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Tributo;
import com.stfn2.ggas.domain.dtos.filter.TributoAliquotaFilterDTO;
import com.stfn2.ggas.domain.TributoAliquota;
import org.springframework.stereotype.Repository;

@Repository
public interface TributoAliquotaRepository extends BaseRepository<TributoAliquota, TributoAliquotaFilterDTO> {

   TributoAliquota findByTributo(Tributo tributo);
}
