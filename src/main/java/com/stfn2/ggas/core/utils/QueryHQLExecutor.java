package com.stfn2.ggas.core.utils;

import jakarta.persistence.Query;
import org.hibernate.transform.Transformers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryHQLExecutor implements QueryExecutor{

    Query query;

    String queryStr;

    public QueryHQLExecutor(Query query, String queryStr) {
        this.query = query;
        this.queryStr = queryStr;
    }

    public void setParameter(String key, Object value) {
        this.query.setParameter(key, value);
    }

    public List<Map> executeToMap() {
        query.unwrap(org.hibernate.query.Query.class).
                setTupleTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        List<Map> listMap = query.getResultList();
        return listMap;
    }

    public List<HashMap<String, Object>> executeToHash() {
        List<Map> map = executeToMap();

        List<HashMap<String, Object>> response = new ArrayList<>();
        map.forEach(m -> {
            HashMap<String, Object> hash = new HashMap<>();
            response.add(hash);
            for (Object key : m.keySet()) {
                hash.put(key.toString(), m.get(key));
            }
        });
        return response;
    }

    public void setParameterList(String key, List<?> arrayList) {
        //todo BI
    }

    public List<?> list(){
        return query.getResultList();
    }

    @Override
    public String getQueryStr() {
        return this.queryStr;
    }
}
