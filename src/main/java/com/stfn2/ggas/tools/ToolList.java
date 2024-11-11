package com.stfn2.ggas.tools;

import java.util.List;

public class ToolList {
   public static Boolean notNullOrEmpyt(List<?> lista){
      return lista != null && lista.size() > 0;
   }
}
