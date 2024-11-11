package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.MenuFilterDTO;
import com.stfn2.ggas.domain.Menu;
import jakarta.persistence.EntityManager;

public class MenuRepositoryImpl extends IRepository<Menu, MenuFilterDTO> {

	public MenuRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(MenuFilterDTO filter, ExecucaoQuery<Menu> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}