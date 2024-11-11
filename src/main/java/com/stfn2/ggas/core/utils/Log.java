package com.stfn2.ggas.core.utils;


import com.stfn2.ggas.core.abstractClasses.BaseEntity;

import java.util.logging.Logger;

public final class Log {

    private static String fragS = "|==== ";
    private static String fragE = " ====|";
    private static Class<?> entityLoger;
    private static Class<?> logClass = Log.class;
    private static StringBuilder str = new StringBuilder();

    public Log(Class<?> entity){
        this.entityLoger = entity;
    }

    public static void infoStatic(Class<?> entity, String msg){
        setlog(entity).info(fragS + msg + fragE);
    }

    public void info(String msg) {
        if(entityLoger != null) logClass = entityLoger;

        setlog(logClass).info(fragS + msg + fragE);
    }

    public static void erroStatic(Class<?> entity, String msg, String erro) {

        setlog(entity).info(fragS + "ERROR -- START" + fragE);
        setlog(entity).info(fragS + "ERRO: " + msg + fragE);
        setlog(entity).info(fragS + "COMP: " + erro + fragE);
        setlog(entity).info(fragS + "ERROR -- END" + fragE);
    }

    public void erro(String msg, String erro) {
        if(entityLoger != null) logClass = entityLoger;

        setlog(logClass).info(fragS + "ERROR -- START" + fragE);
        setlog(logClass).info(fragS + "ERRO: " + msg + fragE);
        setlog(logClass).info(fragS + "COMP: " + erro + fragE);
        setlog(logClass).info(fragS + "ERROR -- END" + fragE);
    }

    public static void updateStatic(Class<?> entityClass, BaseEntity entity) {
        setlog(entityClass).info(fragS + "UPDATE -- START" + fragE);
        setlog(entityClass).info(fragS + "ATUALIZANDO: " + entity.getClass().getSimpleName() + fragE);
        setlog(entityClass).info(fragS + "ID: " + entity.getId() + fragE);
        setlog(entityClass).info(fragS + "UPDATE -- END" + fragE);
    }

    public void update(BaseEntity entity) {
        if(entityLoger != null) logClass = entityLoger;

        setlog(logClass).info(fragS + "UPDATE -- START" + fragE);
        setlog(logClass).info(fragS + "ATUALIZANDO: " + entity.getClass().getSimpleName() + fragE);
        setlog(logClass).info(fragS + "ID: " + entity.getId() + fragE);
        setlog(logClass).info(fragS + "UPDATE -- END" + fragE);
    }

   private static Logger setlog(Class<?> entity){
        return Logger.getLogger(entity.getName());
    }
}
