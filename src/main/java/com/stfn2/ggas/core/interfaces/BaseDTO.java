package com.stfn2.ggas.core.interfaces;

public class BaseDTO {
    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id < 1) this.id = null;
        else this.id = id;
    }

    public Boolean isNew(){
        return id == null || id < 1;
    }
}
