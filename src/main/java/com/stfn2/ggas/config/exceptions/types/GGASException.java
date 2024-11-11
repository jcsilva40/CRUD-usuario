package com.stfn2.ggas.config.exceptions.types;

import com.stfn2.ggas.core.utils.MensagemUtil;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ResourceBundle;

public class GGASException extends Exception {

   /**
    *
    */
   private static final long serialVersionUID = 5206198827235629205L;

   private Exception rootCause;

   private String chaveErro;

   private Object[] parametrosErro;

   /**
    * serialVersionUID
    */


   /**
    * Construtor padrão.
    */
   public GGASException() {

      super();
   }

   /**
    * Instantiates a new GGAS exception.
    *
    * @param message
    *            the message
    */
   public GGASException(String message) {

      super(message);
   }

   /**
    * Instantiates a new GGAS exception.
    *
    * @param cause
    *            the cause
    */
   public GGASException(Throwable cause) {

      super(cause);
   }

   /**
    * Instantiates a new GGAS exception.
    *
    * @param message
    *            the message
    * @param cause
    *            the cause
    */
   public GGASException(String message, Throwable cause) {

      super(message, cause);
   }

   /**
    * Instantiates a new GGAS exception.
    *
    * @param chave
    *            the chave
    * @param param
    *            the param
    */
   public GGASException(String chave, Object param) {

      this.chaveErro = chave;
      this.parametrosErro = new Object[] {param};
   }

   /**
    * Instantiates a new GGAS exception.
    *
    * @param chave
    *            the chave
    * @param param
    *            the param
    */
   public GGASException(String chave, Object[] param) {

      this.chaveErro = chave;
      if (param != null) {
         this.parametrosErro = param.clone();
      }
   }

   /**
    * Instantiates a new GGAS exception.
    *
    * @param rb
    *            the rb
    * @param chave
    *            the chave
    * @param param
    *            the param
    */
   public GGASException(ResourceBundle rb, String chave, Object param) {

      this(rb, chave, new Object[] {param});
   }

   /**
    * Instantiates a new GGAS exception.
    *
    * @param rb
    *            the rb
    * @param chave
    *            the chave
    * @param param
    *            the param
    * @param rootCause
    *            the root cause
    */
   public GGASException(ResourceBundle rb, String chave, Object[] param, Exception rootCause) {

      this(rb, chave, param);
      this.rootCause = rootCause;
      this.chaveErro = chave;
      if(param != null) {
         this.parametrosErro = param.clone();
      }
   }

   /**
    * Instantiates a new GGAS exception.
    *
    * @param rb
    *            the rb
    * @param chave
    *            the chave
    * @param rootCause
    *            the root cause
    */
   public GGASException(ResourceBundle rb, String chave, Exception rootCause) {

      this(rb, chave);
      this.rootCause = rootCause;
      this.chaveErro = chave;
   }

   /**
    * Instantiates a new GGAS exception.
    *
    * @param rb
    *            the rb
    * @param chave
    *            the chave
    * @param param
    *            the param
    */
   public GGASException(ResourceBundle rb, String chave, Object[] param) {

      super(MensagemUtil.obterMensagem(rb, chave, param));
      this.chaveErro = chave;
      if (param != null) {
         this.parametrosErro = param.clone();
      }
   }

   /**
    * Instantiates a new GGAS exception.
    *
    * @param rb
    *            the rb
    * @param chave
    *            the chave
    */
   public GGASException(ResourceBundle rb, String chave) {

      super(MensagemUtil.obterMensagem(rb, chave));
      this.chaveErro = chave;
   }

   /*
    * (non-Javadoc)
    * @see java.lang.Throwable#printStackTrace(java.io.PrintWriter)
    */
   @Override
   public void printStackTrace(PrintWriter s) {

      super.printStackTrace(s);
      if(getRootCause() != null) {
         getRootCause().printStackTrace(s);
      }
   }

   /*
    * (non-Javadoc)
    * @see java.lang.Throwable#printStackTrace(java.io.PrintStream)
    */
   @Override
   public void printStackTrace(PrintStream s) {

      super.printStackTrace(s);
      if(getRootCause() != null) {
         getRootCause().printStackTrace(s);
      }
   }

   /*
    * (non-Javadoc)
    * @see java.lang.Throwable#printStackTrace()
    */
   @Override
   public void printStackTrace() {

      super.printStackTrace();
      if(getRootCause() != null) {
         getRootCause().printStackTrace();
      }
   }

   /**
    * @return Retorna o atributo rootCause.
    */
   public Exception getRootCause() {

      return rootCause;
   }

   /**
    * @param rootCause
    *            O valor a ser atribuído ao
    *            atributo rootCause.
    */
   public void setRootCause(Exception rootCause) {

      this.rootCause = rootCause;
   }

   /**
    * @return Retorna o atributo chaveErro.
    */
   public String getChaveErro() {

      return chaveErro;
   }

   /**
    * @param chaveErro
    *            O valor a ser atribuído ao
    *            atributo chaveErro.
    */
   public void setChaveErro(String chaveErro) {

      this.chaveErro = chaveErro;
   }

   /**
    * @return Retorna o atributo parametrosErro.
    */
   public Object[] getParametrosErro() {
      Object[] retorno = null;
      if(parametrosErro != null) {
         retorno = parametrosErro.clone();
      }
      return retorno;
   }

   /**
    * @param parametrosErro
    *            O valor a ser atribuído ao atributo parametrosErro.
    */
   public void setParametrosErro(Object[] parametrosErro) {
      if (parametrosErro != null) {
         this.parametrosErro = parametrosErro.clone();
      } else {
         this.parametrosErro = null;
      }
   }

}
