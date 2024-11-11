package com.stfn2.ggas.config.exceptions.types;

import java.util.Map;
import java.util.ResourceBundle;

public class NegocioException extends GGASException {

   /**
    *
    */
   private static final long serialVersionUID = 5136787218792613264L;
   private Map<String, Object> erros;

   /**
    * Construtor padrão.
    */
   public NegocioException() {

      super();
   }

   /**
    * Instantiates a new negocio exception.
    *
    * @param message
    *            the message
    * @param cause
    *            the cause
    */
   public NegocioException(String message, Throwable cause) {

      super(message, cause);
   }

   /**
    * Instantiates a new negocio exception.
    *
    * @param message
    *            the message
    */
   public NegocioException(String message) {

      super(message);
   }

   /**
    * Instantiates a new negocio exception.
    *
    * @param cause
    *            the cause
    */
   public NegocioException(Throwable cause) {

      super(cause);
   }

   /**
    * Instantiates a new negocio exception.
    *
    * @param erros
    *            the erros
    */
   public NegocioException(Map<String, Object> erros) {

      super();
      this.erros = erros;
   }

   /**
    * Instantiates a new negocio exception.
    *
    * @param rb
    *            the rb
    * @param chave
    *            the chave
    * @param param
    *            the param
    */
   public NegocioException(ResourceBundle rb, String chave, Object[] param) {

      super(rb, chave, param);
   }

   /**
    * Instantiates a new negocio exception.
    *
    * @param rb
    *            the rb
    * @param chave
    *            the chave
    */
   public NegocioException(ResourceBundle rb, String chave) {

      super(rb, chave);
   }

   /**
    * Instantiates a new negocio exception.
    *
    * @param rb
    *            the rb
    * @param chave
    *            the chave
    * @param rootCause
    *            the root cause
    */
   public NegocioException(ResourceBundle rb, String chave, Exception rootCause) {

      super(rb, chave, rootCause);
   }

   /**
    * Instantiates a new negocio exception.
    *
    * @param rb
    *            the rb
    * @param chave
    *            the chave
    * @param param
    *            the param
    */
   public NegocioException(ResourceBundle rb, String chave, Object param) {

      super(rb, chave, param);
   }

   /**
    * Instantiates a new negocio exception.
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
   public NegocioException(ResourceBundle rb, String chave, Object[] param, Exception rootCause) {

      super(rb, chave, param, rootCause);
   }

   /**
    * Instantiates a new negocio exception.
    *
    * @param chave
    *            the chave
    * @param rootCause
    *            the root cause
    */
   public NegocioException(String chave, Exception rootCause) {

      super(chave, rootCause);
   }

   /**
    * Instantiates a new negocio exception.
    *
    * @param chave
    *            the chave
    * @param param
    *            the param
    */
   public NegocioException(String chave, Object param) {

      super(chave, param);
   }


   /**
    * Instantiates a new negocio exception.
    *
    * @param chave
    *            the chave
    * @param key
    *            the key
    */
   public NegocioException(String chave, boolean key) {

      super(chave, chave);
   }

   /**
    * Instantiates a new negocio exception.
    *
    * @param chave
    *            the chave
    * @param param
    *            the param
    */
   public NegocioException(String chave, Object[] param) {

      super(chave, param);
   }

   /**
    * @return Retorna o atributo erros.
    */
   public Map<String, Object> getErros() {

      return erros;
   }

   /**
    * @param erros
    *            O valor a ser atribuído ao
    *            atributo erros.
    */
   public void setErros(Map<String, Object> erros) {

      this.erros = erros;
   }
}
