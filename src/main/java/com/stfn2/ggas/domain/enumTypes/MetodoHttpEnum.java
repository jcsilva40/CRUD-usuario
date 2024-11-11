package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MetodoHttpEnum {

    GET(1L, "GET"),
    POST(2L, "POST"),
    PUT(3L, "PUT"),
    DELETE(4L, "DELETE"),
    PATCH(5L, "PATCH"),
    OPTIONS(6L, "OPTIONS"),
    HEAD(7L, "HEAD");

    private Long id;
    private String method;

    private MetodoHttpEnum(Long id, String method) {
        this.id = id;
        this.method = method;
    }

    @JsonValue
    public Long getId() {
        return id;
    }

    public String getMethod() {
        return method;
    }

    public static MetodoHttpEnum toEnum(Long id) {
        if (id == null) {
            return null;
        }

        for (MetodoHttpEnum httpMethod : MetodoHttpEnum.values()) {
            if (id.equals(httpMethod.getId())) {
                return httpMethod;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }

    public static MetodoHttpEnum fromMethod(String method) {
        if (method == null) {
            return null;
        }

        for (MetodoHttpEnum httpMethod : MetodoHttpEnum.values()) {
            if (method.equalsIgnoreCase(httpMethod.getMethod())) {
                return httpMethod;
            }
        }
        throw new IllegalArgumentException("Método HTTP inválido: " + method);
    }
}
