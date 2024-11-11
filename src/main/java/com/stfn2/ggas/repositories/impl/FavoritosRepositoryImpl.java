package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.FavoritosFilterDTO;
import com.stfn2.ggas.domain.Favoritos;
import jakarta.persistence.EntityManager;

public class FavoritosRepositoryImpl extends IRepository<Favoritos, FavoritosFilterDTO> {

	public FavoritosRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(FavoritosFilterDTO filter, ExecucaoQuery<Favoritos> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}