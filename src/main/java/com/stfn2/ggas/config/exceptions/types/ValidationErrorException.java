package com.stfn2.ggas.config.exceptions.types;

import com.stfn2.ggas.config.exceptions.utils.FieldMessage;

import java.util.List;

public class ValidationErrorException extends RuntimeException{

    private List<FieldMessage> erros;

    public ValidationErrorException(List<FieldMessage> erros){
        this.erros = erros;
    }


    public List<FieldMessage> getErros() {
        return erros;
    }
}
