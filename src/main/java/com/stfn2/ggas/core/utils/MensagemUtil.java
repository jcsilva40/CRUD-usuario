package com.stfn2.ggas.core.utils;

import com.stfn2.ggas.config.exceptions.types.GGASException;
import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.tools.ToolStr;

import java.io.StringWriter;
import java.io.Writer;
import java.util.*;

public class MensagemUtil {
   public static final Locale LOCALE = new Locale("pt", "BR");
   public static final String STRING_NOVA_LINHA = "\r\n";

   /**
    * Método responsável por obter mensagem a partir de uma chave passada
    *
    * @param chave - {@link String}
    * @return mensagem - {@link String}
    */
   public static String obterMensagem(String chave) {
      if (existeChave(chave)) {
         return obterMensagem(getResourcePadrao(), chave);
      }
      return chave;
   }

   /**
    * Metodo responsavel por obter mensagem a partir da chave e dos argumentos
    *
    * @param chave      - {@link String}
    * @param argumentos - {@link Object} Array
    * @return mensagem - {@link String}
    */
   public static String obterMensagem(String chave, Object... argumentos) {
      if (existeChave(chave)) {
         return obterMensagem(getResourcePadrao(), chave, argumentos);
      }
      return chave;
   }

   private static ResourceBundle getResourcePadrao() {
      return ResourceBundle.getBundle("mensagens", LOCALE);
   }

   /**
    * Método responsável por obter a mensagem do arquivo de propriedades.
    *
    * @param resourceBundle O Resource Bundle
    * @param chave          A chave
    * @param argumentos     Os argumentos da mensagem
    * @return String A Mensagem
    */
   public static String obterMensagem(ResourceBundle resourceBundle, String chave, Object[] argumentos) {
      if (existeChave(chave)) {
         String mensagem = obterMensagem(resourceBundle, chave);
         for (int i = 0; i < argumentos.length; i++) {
            mensagem = ToolStr.substituirArgumentosDoTexto(mensagem, i, argumentos[i]);
         }
         return mensagem;
      }
      return chave;
   }

   /**
    * Método responsável por obter a mensagem do arquivo de propriedades.
    *
    * @param resourceBundle O Resource Bundle
    * @param chave          A chave
    * @param argumento      Os argumentos da mensagem
    * @return String A Mensagem
    */
   public static String obterMensagem(ResourceBundle resourceBundle, String chave, Object argumento) {
      if (existeChave(chave)) {
         return obterMensagem(resourceBundle, chave, new Object[] { argumento });
      }
      return chave;
   }

   /**
    * Método responsável por obter a mensagem do arquivo de propriedades.
    *
    * @param resourceBundle O Resource Bundle
    * @param chave          A chave
    * @return String A mensagem
    */
   public static String obterMensagem(ResourceBundle resourceBundle, String chave) {
      if (existeChave(chave)) {
         return resourceBundle.getString(chave);
      }
      return chave;
   }

   /**
    * Método reponsável por obter.
    *
    * @param erro the erro
    * @return the stack trace
    */
   public static String getStackTrace(final Throwable erro) {

      final Writer result = new StringWriter();
      Log.infoStatic(MensagemUtil.class, erro.getMessage());
      return result.toString();
   }

   /**
    * Método reponsável por retornar array de bytes das mensagens de erro geradas
    *
    * @param throwable O erro
    * @return Array de bytes da mensagem de erro
    */
   public static byte[] gerarMensagemErroBytes(Throwable throwable) {
      return gerarMensagemErro(throwable).getBytes();
   }

   /**
    * Método reponsável por gerar uma mensagem de erro.
    *
    * @param throwable O erro
    * @return A mensagem de erro
    */
   public static String gerarMensagemErro(Throwable throwable) {

      String chaveErro = null;
      Object[] parametrosErro = null;
      StringBuilder mensagemErro = new StringBuilder();
      Map<String, Object> errosDeNegocio = null;
      Map.Entry<?, ?> erro = null;

      if (throwable instanceof NegocioException) {
         chaveErro = ((NegocioException) throwable).getChaveErro();
         parametrosErro = ((NegocioException) throwable).getParametrosErro();

         errosDeNegocio = ((NegocioException) throwable).getErros();
         if (errosDeNegocio != null && !errosDeNegocio.isEmpty()) {
            for (Iterator<?> it = errosDeNegocio.entrySet().iterator(); it.hasNext();) {
               erro = (Map.Entry<?, ?>) it.next();
               mensagemErro.append(obterMensagemErro((String) erro.getValue(), LOCALE));
               mensagemErro.append(STRING_NOVA_LINHA);
            }
         } else {
            chaveErro = ((NegocioException) throwable).getChaveErro();
            if (chaveErro != null && chaveErro.length() > 0) {
               if (parametrosErro != null && parametrosErro.length > 0) {
                  Object[] parametrosErroLocalizado = new String[parametrosErro.length];
                  for (int i = 0; i < parametrosErro.length; i++) {
                     parametrosErroLocalizado[i] = obterMensagemErro(String.valueOf(parametrosErro[i]), LOCALE);
                  }
                  mensagemErro.append(MensagemUtil.obterMensagem(
                          ResourceBundle.getBundle("mensagens", LOCALE), chaveErro,
                          parametrosErroLocalizado));
                  mensagemErro.append(STRING_NOVA_LINHA);
               } else {
                  mensagemErro.append(chaveErro);
                  mensagemErro.append(STRING_NOVA_LINHA);
               }
            } else {
               if (chaveErro != null) {
                  mensagemErro.append(chaveErro);
                  mensagemErro.append(STRING_NOVA_LINHA);
               } else {
                  mensagemErro.append(STRING_NOVA_LINHA);
                  mensagemErro.append(throwable.getMessage());
                  parametrosErro = throwable.getStackTrace();
                  if (parametrosErro != null) {
                     for (int i = 0; i < parametrosErro.length; i++) {
                        mensagemErro.append(obterMensagemErro(String.valueOf(parametrosErro[i]), LOCALE));
                        mensagemErro.append(STRING_NOVA_LINHA);
                     }
                  }
               }
            }
         }
      } else if (throwable instanceof GGASException) {
         chaveErro = ((GGASException) throwable).getChaveErro();
         if (chaveErro != null) {
            mensagemErro.append(MensagemUtil
                    .obterMensagem(ResourceBundle.getBundle("mensagens", LOCALE), chaveErro));
            mensagemErro.append(STRING_NOVA_LINHA);
            parametrosErro = ((GGASException) throwable).getStackTrace();
            if (parametrosErro != null) {
               for (int i = 0; i < parametrosErro.length; i++) {
                  mensagemErro.append(obterMensagemErro(String.valueOf(parametrosErro[i]), LOCALE));
                  mensagemErro.append(STRING_NOVA_LINHA);
               }
            }
         } else {
            mensagemErro.append(throwable.getMessage());
            mensagemErro.append(STRING_NOVA_LINHA);
            parametrosErro = ((GGASException) throwable).getStackTrace();
            if (parametrosErro != null) {
               for (int i = 0; i < parametrosErro.length; i++) {
                  mensagemErro.append(obterMensagemErro(String.valueOf(parametrosErro[i]), LOCALE));
                  mensagemErro.append(STRING_NOVA_LINHA);
               }
            }
         }

      } else {
         mensagemErro.append("ENTRE EM CONTATO COM O ADMINISTRADOR DO SISTEMA");
         parametrosErro = throwable.getStackTrace();
         if (parametrosErro != null) {
            for (int i = 0; i < parametrosErro.length; i++) {
               mensagemErro.append(obterMensagemErro(String.valueOf(parametrosErro[i]), LOCALE));
               mensagemErro.append(STRING_NOVA_LINHA);
            }
         }
      }

      // impressão de log do erro
      mensagemErro.append(STRING_NOVA_LINHA);
      mensagemErro.append(STRING_NOVA_LINHA);
      mensagemErro.append(STRING_NOVA_LINHA);
      mensagemErro.append(new StringWriter().toString());

      return mensagemErro.toString();
   }

   /**
    * Obter mensagem erro.
    *
    * @param chave  the chave
    * @param locale the locale
    * @return the string
    */
   public static String obterMensagemErro(String chave, Locale locale) {

      String mensagem = null;
      String chaveAux = null;
      StringBuilder stringBuilder = new StringBuilder();
      String[] arrChaves = chave.split(", ");
      int countTokens = arrChaves.length;

      if (countTokens > 0) {
         for (int i = 0; i < arrChaves.length; i++) {
            chaveAux = arrChaves[i];
            try {
               if (locale != null) {
                  mensagem = MensagemUtil.obterMensagem(
                          ResourceBundle.getBundle("mensagens", locale), chaveAux);
               } else {
                  mensagem = chaveAux;
               }
            } catch (MissingResourceException e) {
               Log.erroStatic(MensagemUtil.class, e.getMessage(), e.getCause().getMessage());
               mensagem = chaveAux;
            }
            stringBuilder.append(mensagem);
            if (countTokens > 1) {
               stringBuilder.append(", ");
            }
            countTokens--;
         }
      } else {
         if (locale != null) {
            try {
               mensagem = MensagemUtil
                       .obterMensagem(ResourceBundle.getBundle("mensagens", locale), chave);
            } catch (MissingResourceException e) {
               Log.erroStatic(MensagemUtil.class, e.getMessage(), e.getCause().getMessage());
               mensagem = chave;
            }
            stringBuilder.append(mensagem);
         } else {
            stringBuilder.append(chave);
         }
      }

      return stringBuilder.toString();
   }

   /**
    * Método responsável por validar se a chave existe no arquivo de propriedades.
    *
    * @param chave A chave
    * @return true caso exista ou false caso contrário
    */
   public static boolean existeChave(String chave) {

      boolean retorno = true;

      try {
         ResourceBundle.getBundle("mensagens", LOCALE).getString(chave);
      } catch (MissingResourceException e) {
         //LOG.error("Falha ao obter valor da chave", e);
         Log.erroStatic(MensagemUtil.class, "Falha ao obter valor da chave: ", chave);
         retorno = false;
      }
      return retorno;
   }

   /**
    * Montar lista erros.
    *
    * @param msgErros the msg erros
    * @return the list
    */
   public static List<String> montarListaErros(String... msgErros) {

      List<String> listaErros = new ArrayList<>();

      for (String erro : msgErros) {
         if (erro != null) {
            listaErros.add(erro);
         }
      }

      return listaErros;

   }
}
