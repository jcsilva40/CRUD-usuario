package com.stfn2.ggas.services.componentes.fabricaboleto.codigodebarras;

import com.stfn2.ggas.services.componentes.fabricaboleto.construtoresboletos.uteis.DigitoAutoConferencia;

public class RepresentacaoNumericaSafra {

   private static final int FIM_PRIMEIRA_PARTE_PRIMEIRO_CAMPO = 4;
   private static final int INICIO_SEGUNDA_PARTE_PRIMEIRO_CAMPO = 19;
   private static final int FIM_SEGUNDA_PARTE_PRIMEIRO_CAMPO = 24;

   private static final int INICIO_SEGUNDO_CAMPO = 24;
   private static final int FIM_SEGUNDO_CAMPO = 34;

   private static final int INICIO_TERCEIRO_CAMPO = 34;
   private static final int FIM_TERCEIRO_CAMPO = 44;

   private static final int INICIO_QUINTO_CAMPO = 5;
   private static final int FIM_QUINTO_CAMPO = 19;

   private static final char SEPARADOR = '.';
   private static final char ESPACO = ' ';
   private static final int POSICAO_SEPARADOR = 5;
   private static final int POSICAO_AUTO_CONFERENCIA = 4;

   private CodigoBarras codigoBarras;
   private String digito;

   /**
    * Cria uma representação numérica usando como base o {@link CodigoBarras}
    * passado como parâmetro.
    *
    * @param codigoBarras
    *            o {@link CodigoBarras} preenchido
    */
   public RepresentacaoNumericaSafra(CodigoBarras codigoBarras, String digito) {
      this.codigoBarras = codigoBarras;
      this.digito = digito;
   }

   /**
    * Gera o primeiro campo da representação numérica a partir do
    * {@link CodigoBarras} passado no construtor desta instância.
    *
    * @return o primeiro campo
    */
   public String primeiroCampo() {
      StringBuilder construtor = new StringBuilder();
      return construtor.append(this.codigoBarras.toString().substring(0, FIM_PRIMEIRA_PARTE_PRIMEIRO_CAMPO))
              .append(this.codigoBarras.toString().substring(INICIO_SEGUNDA_PARTE_PRIMEIRO_CAMPO,
                      FIM_SEGUNDA_PARTE_PRIMEIRO_CAMPO))
              .append(DigitoAutoConferencia.modulo10(construtor.toString())).insert(POSICAO_SEPARADOR, SEPARADOR)
              .toString();
   }

   /**
    * Gera o segundo campo da representação numérica a partir do
    * {@link CodigoBarras} passado no construtor desta instância.
    *
    * @return o segundo campo
    */
   public String segundoCampo() {
      StringBuilder construtor = new StringBuilder();
      return construtor
              .append(this.codigoBarras.toString().substring(INICIO_SEGUNDO_CAMPO, FIM_SEGUNDO_CAMPO))
              .append(DigitoAutoConferencia.modulo10(construtor.toString()))
              .insert(POSICAO_SEPARADOR, SEPARADOR)
              .toString();
   }

   /**
    * Gera o terceiro campo da representação numérica a partir do
    * {@link CodigoBarras} passado no construtor desta instância.
    *
    * @return o terceiro campo
    */
   public String terceiroCampo() {
      StringBuilder construtor = new StringBuilder();
      return construtor
              .append(this.codigoBarras.toString().substring(INICIO_TERCEIRO_CAMPO, FIM_TERCEIRO_CAMPO))
              .append(DigitoAutoConferencia.modulo10(construtor.toString()))
              .insert(POSICAO_SEPARADOR, SEPARADOR)
              .toString();
   }

   /**
    * Gera o quarto campo da representação numérica a partir do
    * {@link CodigoBarras} passado no construtor desta instância.
    *
    * @return o quarto campo
    */
   public String quartoCampo() {
      return String.valueOf(
              this.codigoBarras
                      .toString()
                      .charAt(POSICAO_AUTO_CONFERENCIA));
   }

   /**
    * Gera o quinto campo da representação numérica a partir do
    * {@link CodigoBarras} passado no construtor desta instância.
    *
    * @return o quinto campo
    */
   public String quintoCampo() {
      return this.codigoBarras.toString().substring(INICIO_QUINTO_CAMPO, FIM_QUINTO_CAMPO);
   }
   /**
    * converte resultado para String
    */
   @Override
   public String toString() {
      return new StringBuilder()
              .append(primeiroCampo())
              .append(ESPACO)
              .append(segundoCampo())
              .append(ESPACO)
              .append(terceiroCampo())
              .append(ESPACO)
              .append(quartoCampo())
              .append(ESPACO)
              .append(quintoCampo())
              .toString();
   }
}
