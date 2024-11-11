package com.stfn2.ggas.core.interfaces;

import java.util.HashMap;
import java.util.Map;

public interface ValidarDados {

	default Map<String, Object> validarDados() {
		Map<String, Object> validacao = new HashMap<>();
		return validacao;
	}
}
