package com.stfn2.ggas.core.interfaces;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.abstractClasses.FilterDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface BaseRepository<Entidade extends BaseEntity, Filter extends FilterDTO> extends JpaRepository<Entidade, Long>,
        RepositoryCustom<Entidade, Filter>, PagingAndSortingRepository<Entidade, Long> {
}
