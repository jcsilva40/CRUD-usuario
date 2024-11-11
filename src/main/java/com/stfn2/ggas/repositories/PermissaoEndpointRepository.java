package com.stfn2.ggas.repositories;

import com.stfn2.ggas.config.security.domain.PermissaoEndpoint;
import com.stfn2.ggas.config.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoEndpointRepository extends JpaRepository<PermissaoEndpoint, Long> {


}
