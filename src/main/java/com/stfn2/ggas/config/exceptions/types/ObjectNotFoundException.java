package com.stfn2.ggas.config.exceptions.types;

public class ObjectNotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public ObjectNotFoundException(String id) {
                super("Objeto com id: " + id + ", não foi encontrado");
        }

        public ObjectNotFoundException(String id, Throwable cause) {
                super("Objeto com id: " + id + ", não foi encontrado", cause);
        }

        @Override
        public String toString() {
                return "ObjectNotFoundException [getMessage()=" + getMessage() + "]";
        }

}