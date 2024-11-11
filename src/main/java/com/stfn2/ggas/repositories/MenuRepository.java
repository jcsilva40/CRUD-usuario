package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Menu;
import com.stfn2.ggas.domain.dtos.filter.MenuFilterDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends BaseRepository<Menu, MenuFilterDTO> {
       
    @Query("""
            SELECT DISTINCT
                menu
            FROM
                Menu menu                
            WHERE 
                menu.menuPai IS NULL
            ORDER BY
                menu.ordem ASC
            """)
    @Override
    List<Menu> findAll();
}