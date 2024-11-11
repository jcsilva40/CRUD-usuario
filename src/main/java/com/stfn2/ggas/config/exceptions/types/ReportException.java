package com.stfn2.ggas.config.exceptions.types;

public class ReportException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public ReportException(String msg) {
                super(msg);
        }

        public ReportException(String msg, Throwable cause) {
                super(msg, cause);
        }

}
