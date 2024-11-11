package com.stfn2.ggas.core.utils;

import com.stfn2.ggas.core.models.DadosAuditoria;

public class DadosAuditoriaUtil {

   private static ThreadLocal<DadosAuditoria> dadosAuditoria = new ThreadLocal<DadosAuditoria>();
   public static DadosAuditoria getDadosAuditoria() {
      return dadosAuditoria.get();
   }

   public static void setDadosAuditoria(DadosAuditoria dados) {
      dadosAuditoria.set(dados);
   }
}
