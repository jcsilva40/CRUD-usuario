package com.stfn2.ggas.config.exceptions.types;

public class TokenInvalidException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public TokenInvalidException(String msg) {
                super(msg);
        }

        public TokenInvalidException(String msg, Throwable cause) {
                super(msg, cause);
        }

}
