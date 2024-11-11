package com.stfn2.ggas.core.interfaces;

import com.stfn2.ggas.core.abstractClasses.FilterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RepositoryCustom<Entity, Filter extends FilterDTO> {
	public Page<Entity> filter(Filter filter, Pageable pageable);
	public List<Entity> filter(Filter filter);
}
