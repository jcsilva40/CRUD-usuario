package com.stfn2.ggas.core.utils;

import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.query.TupleTransformer;

public class QuerySQLExecutor implements QueryExecutor{

    Query query;

    String queryStr;

    public QuerySQLExecutor(Query query, String queryStr) {
        this.query = query;
        this.queryStr = queryStr;
    }

    @Override
    public void setParameter(String key, Object value) {
        this.query.setParameter(key, value);
    }

    public List<HashMap<String, Object>> executeToMap() {
        query.unwrap(org.hibernate.query.Query.class).
                setTupleTransformer(new TupleTransformer<HashMap<String, Object>>() {
                    @Override
                    public HashMap<String, Object> transformTuple(Object[] tuple, String[] aliases) {
                        HashMap<String, Object> result = new HashMap<>();
                        for (int i = 0; i < tuple.length; i++) {
                            String alias = aliases[i];
                            if (alias != null) {
                                Object value = tuple[i];
                                if (value instanceof java.sql.Timestamp timestamp) {
                                    // Converter java.sql.Timestamp para LocalDate
                                    value = timestamp.toLocalDateTime().toLocalDate();
                                }
                                result.put(alias, value);
                            }
                        }
                        return result;
                    }
        });

        List<HashMap<String, Object>> listMap = query.getResultList();
        return listMap;
    }

    @Override
    public List<HashMap<String, Object>> executeToHash() {
        List<HashMap<String, Object>> map = executeToMap();       
        return map;
    }

    @Override
    public void setParameterList(String key, List<?> arrayList) {
        //todo BI
    }

    @Override
    public String getQueryStr() {
        return this.queryStr;
    }
}
