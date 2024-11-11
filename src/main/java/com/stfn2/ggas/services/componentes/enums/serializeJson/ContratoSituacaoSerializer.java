package com.stfn2.ggas.services.componentes.enums.serializeJson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.stfn2.ggas.domain.enumTypes.ContratoSituacaoEnum;

import java.io.IOException;

public class ContratoSituacaoSerializer extends JsonSerializer<ContratoSituacaoEnum> {

    @Override
    public void serialize(ContratoSituacaoEnum value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", value.getId());
        gen.writeStringField("descricao", value.getDescricao());
        gen.writeBooleanField("inclusaoInicial", value.getInclusaoInicial());
        gen.writeEndObject();
    }
}
