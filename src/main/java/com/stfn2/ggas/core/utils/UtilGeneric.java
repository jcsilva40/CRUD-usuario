package com.stfn2.ggas.core.utils;

import java.lang.reflect.ParameterizedType;

public class UtilGeneric<T> {
	public T newInstance;
	public Class<T> typeOf;


	@SuppressWarnings({ "unchecked", "deprecation" })
	public void initParameter() throws Exception, ClassNotFoundException, InstantiationException {
		ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
		String parameterClassName = pt.getActualTypeArguments()[0].toString().split("\\s")[1];
		newInstance = (T) Class.forName(parameterClassName).newInstance();
	}

	@SuppressWarnings("unchecked")
	public Class<T> getTClass() {
		this.typeOf = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return this.typeOf;
	}
}

interface Factory<T> {
	T factory();
}