package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.OrgaoExpedidor;
import com.stfn2.ggas.domain.dtos.filter.OrgaoExpedidorFilterDTO;

@Repository
public interface OrgaoExpedidorRepository extends BaseRepository<OrgaoExpedidor, OrgaoExpedidorFilterDTO> {

}