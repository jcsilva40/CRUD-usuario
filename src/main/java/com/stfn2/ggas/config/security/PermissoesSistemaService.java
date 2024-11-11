package com.stfn2.ggas.config.security;

import com.stfn2.ggas.config.security.domain.PermissaoEndpoint;
import com.stfn2.ggas.repositories.PermissaoEndpointRepository;
import com.stfn2.ggas.domain.enumTypes.MetodoHttpEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermissoesSistemaService {

    @Autowired
    private PermissaoEndpointRepository permissaoEndpointRepository;

    public Map<String, Map<MetodoHttpEnum, List<String>>> carregarPermissoes() {

        Map<String, Map<MetodoHttpEnum, List<String>>> urlRoleMap = new HashMap<>();

        List<PermissaoEndpoint> permissions = permissaoEndpointRepository.findAll();

        for (PermissaoEndpoint permission : permissions) {
            String url = permission.getUrlEndpoint();
            MetodoHttpEnum metodoHttp = permission.getHttpMethod();
            String role = permission.getPerfilPermissaoUsuario().getDescricao();

            urlRoleMap
                    .computeIfAbsent(url, k -> new HashMap<>())
                    .computeIfAbsent(metodoHttp, k -> new ArrayList<>())
                    .add(role);
        }
        return urlRoleMap;
    }
}
