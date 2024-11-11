package com.stfn2.ggas.config.exceptions.types;

public class DuplicateEmailException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public DuplicateEmailException(String email) {
                super("O e-mail: " + email + ", já encontra-se cadastrado no nosso sistema!");
        }
}