package com.stfn2.ggas.core.utils;

import java.util.HashMap;
import java.util.List;

public interface QueryExecutor {

    List<HashMap<String, Object>> executeToHash();

    void setParameter(String key, Object value);

    void setParameterList(String key, List<?> arrayList);

    String getQueryStr();
}
