package com.stfn2.ggas.services.componentes.bi;

import com.stfn2.ggas.core.utils.QueryExecutor;
import com.stfn2.ggas.core.utils.QueryHQLExecutor;
import com.stfn2.ggas.core.utils.QuerySQLExecutor;
import com.stfn2.ggas.domain.BIQuery;
import com.stfn2.ggas.domain.BIStefanini;
import com.stfn2.ggas.domain.ParametroBI;
import com.stfn2.ggas.domain.enumTypes.TipoEntradaBIEnum;
import com.stfn2.ggas.domain.enumTypes.TipoPrimitivoParametroBIEnum;
import com.stfn2.ggas.domain.enumTypes.TipoQueryBIEnum;
import com.stfn2.ggas.services.BIStefaniniService;
import com.stfn2.ggas.tools.ToolDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class PlayBIComponent {

    private BIStefaniniService service;

    public PlayBIComponent(BIStefaniniService service) {
        this.service = service;
    }

    public List<HashMap<String, Object>> executarBI(BIStefanini bi, HashMap<String, Object> parametros) {
        //this.valid.validarCamposBI(bi, parametros);
        //this.valid.checkErrosAndThrowException();

        List<HashMap<String, Object>> data = buildData(parametros, bi, bi.getQuery(), null);
        return data;
    }

    private List<HashMap<String, Object>> buildData(HashMap<String, Object> parametros, BIStefanini bi, BIQuery query, HashMap<String, Object> parametrosFromFild) {
        List<HashMap<String, Object>> data;
        if (query.getTipoQuery() == TipoQueryBIEnum.HQL) {
            QueryHQLExecutor queryHql = service.createQueryHQL(query.getQuery());
            popularDadosQueryParametros(query.getQuery(), queryHql, parametros, bi);
            if (parametrosFromFild != null) {
                putParametros(queryHql, parametrosFromFild);
            }
            data = queryHql.executeToHash();
        } else {
            QuerySQLExecutor querySql = service.createQuerySQL(query.getQuery());
            popularDadosQueryParametros(query.getQuery(), querySql, parametros, bi);
            if (parametrosFromFild != null) {
                putParametros(querySql, parametrosFromFild);
            }
            data = querySql.executeToHash();
        }

        if (query.getChildren() != null) {
            data.forEach(item -> query.getChildren().forEach(q -> item.put(q.getNomeField(), buildData(parametros, bi, q, item))));
        }
        return data;
    }

    private void popularDadosQueryParametros(String queryStr, QueryExecutor query, HashMap<String, Object> param, BIStefanini bi) {
        for (ParametroBI p : bi.getParametros()) {
            String pKey = ":" + p.getKey();
            if (!queryStr.contains(pKey)) {
                continue;
            }
            try {
                Object object = param.get(p.getKey());
                if (TipoEntradaBIEnum.MULTIPLAS_ENTIDADE.equals(p.getTipoEntrada())) {
                    if (Objects.isNull(object) || ((List<?>) object).isEmpty()) {
                        query.setParameter(p.getKey(), new ArrayList(Arrays.asList(0)));
                    } else {                        
                        List<?> lista = (List<?>) object;                        
                        query.setParameter(p.getKey(), lista);
                    }
                } else {
                    if (object == null) {
                        query.setParameter(p.getKey(), null);
                    } else if (TipoEntradaBIEnum.PRIMITIVO.equals(p.getTipoEntrada())) {
                        String value = object.toString();
                        TipoPrimitivoParametroBIEnum tipo = p.getTipoPrimitivo();
                        switch (tipo) {
                            case BIG_DECIMAL -> query.setParameter(p.getKey(), new BigDecimal(value));
                            case LONG -> query.setParameter(p.getKey(), Long.valueOf(value));
                            case SHORT -> query.setParameter(p.getKey(), Short.valueOf(value));
                            case STRING -> query.setParameter(p.getKey(), value);
                            case DOUBLE -> query.setParameter(p.getKey(), Double.valueOf(value));
                            case INTEGER -> query.setParameter(p.getKey(), Integer.valueOf(value));
                            case DATE -> query.setParameter(p.getKey(), ToolDate.formateToQuery(value));
                            default -> {
                            }
                        }
                    } else if (TipoEntradaBIEnum.ANO_MES.equals(p.getTipoEntrada())) {
                        String value = object.toString();
                        query.setParameter(p.getKey(), Integer.valueOf(value));
                    } else {
                        String value = object.toString();
                        query.setParameter(p.getKey(), Long.valueOf(value));
                    }
                }
            } catch (NumberFormatException en) {
                System.out.println(en.getMessage());
            }
        }
    }

    private void putParametros(QueryExecutor query, HashMap<String, Object> parametrosFromFild) {
        for (String key : parametrosFromFild.keySet()) {
            String pKey = ":" + key;
            if (query.getQueryStr().contains(pKey)) {
                Object value = parametrosFromFild.get(key);
                if (value instanceof BigDecimal) {
                    query.setParameter(key, (BigDecimal) value);
                } else if (value instanceof Long) {
                    query.setParameter(key, (Long) value);
                } else if (value instanceof Short) {
                    query.setParameter(key, (Short) value);
                } else if (value instanceof String) {
                    query.setParameter(key, (String) value);
                } else if (value instanceof Double) {
                    query.setParameter(key, (Double) value);
                } else if (value instanceof Integer) {
                    query.setParameter(key, (Integer) value);
                } else if (value instanceof Date) {
                    query.setParameter(key, value);
                } else if (value instanceof LocalDate){
                    query.setParameter(key, value);
                }
            }
        }
    }


}
