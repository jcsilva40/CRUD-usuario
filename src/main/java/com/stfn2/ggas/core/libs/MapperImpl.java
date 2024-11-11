package com.stfn2.ggas.core.libs;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

import java.util.ArrayList;
import java.util.List;

public class MapperImpl {

    private static final ModelMapper mapper = new ModelMapper();

    static {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        mapper.getConfiguration().setAmbiguityIgnored(true);
        mapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);//.setDeepCopyEnabled(true);
    }

    public static <Origem, Destino> Destino parseObject(Origem origem, Class<Destino> destino) {
        if (origem == null) {
            return null;
        }

        try {
            return mapper.map(origem, destino);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static <Origem, Destino> List<Destino> parseListObject(List<Origem> origens, Class<Destino> destino) {
        if (origens.size() < 1) {
            return new ArrayList<>();
        }

        List<Destino> response = new ArrayList<>();

        for (Origem origem : origens) {
            response.add(mapper.map(origem, destino));
        }

        return response;
    }

}
