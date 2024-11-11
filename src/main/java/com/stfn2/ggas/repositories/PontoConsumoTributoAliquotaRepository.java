package com.stfn2.ggas.repositories;

import com.stfn2.ggas.domain.PontoConsumo;
import org.springframework.stereotype.Repository;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.PontoConsumoTributoAliquota;
import com.stfn2.ggas.domain.dtos.filter.PontoConsumoTributoAliquotaFilterDTO;

import java.util.List;

@Repository
public interface PontoConsumoTributoAliquotaRepository extends BaseRepository<PontoConsumoTributoAliquota, PontoConsumoTributoAliquotaFilterDTO> {

   List<PontoConsumoTributoAliquota> findByPontoConsumo(PontoConsumo pontoConsumo);
}
