package com.stfn2.ggas.core.abstractClasses.combo;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.repositories.EntidadeConteudoRepository;
import com.stfn2.ggas.repositories.UnidadeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComboService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EntidadeConteudoRepository entidadeConteudoRepository;

    @Autowired
    private UnidadeRepository unidadeRepository;

    public List<ComboDTO> findIdAndDescricao(String entityName) {
        return findEntidade(entityName, BaseEntity::getDescricaoField, Optional.empty());
    }

    public List<ComboDTO> findEntidadeAbreviado(String entityName) {
        return findEntidade(entityName, BaseEntity::getAbreviadoField, Optional.empty());
    }

    public List<ComboDTO> findIdAndDescricaoById(String entityName, Long id) {
        return findEntidade(entityName, BaseEntity::getDescricaoField, Optional.of(id));
    }

    public List<ComboDTO> findEntidadeAbreviadoById(String entityName, Long id) {
        return findEntidade(entityName, BaseEntity::getAbreviadoField, Optional.of(id));
    }
    
    public List<ComboDTO> findEntidade(String entityName, Function<BaseEntity, String> fieldGetter, Optional<Long> id) {
        try {
            Class<?> entityClass = Class.forName("com.stfn2.ggas.domain." + entityName);
            // Verifique se a classe estende BaseEntity
            if (!BaseEntity.class.isAssignableFrom(entityClass)) {
                throw new IllegalArgumentException("A entidade " + entityName + " não estende BaseEntity");
            }
            // Crie uma instância da entidade
            BaseEntity entity = (BaseEntity) entityClass.getDeclaredConstructor().newInstance();
            // Obtenha o campo de descrição
            String field = fieldGetter.apply(entity);
            String queryString = "SELECT new com.stfn2.ggas.core.abstractClasses.combo.ComboDTO(e.id, e." + field + ") FROM " + entityName + " e";
            // Adicione a cláusula WHERE se o id for fornecido
            if (id.isPresent()) {
                queryString += " WHERE e.id = " + id.get();
            }
            TypedQuery<ComboDTO> query = entityManager.createQuery(queryString, ComboDTO.class);            

            return query.getResultList();
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            throw new RuntimeException("Erro ao executar a consulta para a entidade " + entityName, e);
        }
    }

    public List<ComboDTO> findEntidadeConteudo(Long idEntidadeClasse) {
        return entidadeConteudoRepository.findAllPorEntidadeClasse(idEntidadeClasse);
    }
    
    public List<ComboDTO> findEntidadeConteudoAbreviado(Long idEntidadeClasse) {
        return entidadeConteudoRepository.findAllPorEntidadeClasseAbreviado(idEntidadeClasse);
    }

    public List<ComboDTO> findUnidadePorEntidadeClasse(Long idUnidadeClasse) {
        return unidadeRepository.findAllPorUnidadeClasse(idUnidadeClasse);
    }

    public List<ComboDTO> findUnidadePorEntidadeClasseAbreviado(Long idUnidadeClasse) {
        return unidadeRepository.findAllPorUnidadeClasseAbreviado(idUnidadeClasse);
    }

    public List<ComboDTO> findIdAndDescricaoEnum(String enumName) {

        List<ComboDTO> enumValues = new ArrayList<>();
        try {
            Class<?> enumClass = Class.forName("com.stfn2.ggas.domain.enumTypes." + enumName);
            
            if (enumClass.isEnum()) {
                for (Object enumConstant : enumClass.getEnumConstants()) {
                    ComboDTO comboDTO = new ComboDTO();
                    for (Field field : enumClass.getDeclaredFields()) {
                        if (!field.isSynthetic()) {
                            field.setAccessible(true);
                            if(field.getName().equals("id")){
                                comboDTO.setId((Long)field.get(enumConstant));
                            }
                            else if(field.getName().equals("descricao")){
                                comboDTO.setDescricao((String)field.get(enumConstant));
                            }                            
                        }
                    }
                    enumValues.add(comboDTO);
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return enumValues;
    }
    
    public List<ComboDTO> findIdAndAbreviadoEnum(String enumName) {

        List<ComboDTO> enumValues = new ArrayList<>();
        try {
            Class<?> enumClass = Class.forName("com.stfn2.ggas.domain.enumTypes." + enumName);
            
            if (enumClass.isEnum()) {
                for (Object enumConstant : enumClass.getEnumConstants()) {
                    ComboDTO comboDTO = new ComboDTO();
                    for (Field field : enumClass.getDeclaredFields()) {
                        if (!field.isSynthetic()) {
                            field.setAccessible(true);
                            if(field.getName().equals("id")){
                                comboDTO.setId((Long)field.get(enumConstant));
                            }
                            else if(field.getName().equals("abreviado")){
                                comboDTO.setDescricao((String)field.get(enumConstant));
                            }                            
                        }
                    }
                    enumValues.add(comboDTO);
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return enumValues;
    }
    
    public List<Map<String, Object>> findAllEnum(String enumName) {

        List<Map<String, Object>> enumValues = new ArrayList<>();
        try {
            Class<?> enumClass = Class.forName("com.stfn2.ggas.domain.enumTypes." + enumName);
            if (enumClass.isEnum()) {
                for (Object enumConstant : enumClass.getEnumConstants()) {
                    Map<String, Object> enumFields = new HashMap<>();
                    for (Field field : enumClass.getDeclaredFields()) {
                        if (!field.isSynthetic() && !field.isEnumConstant()) {
                            field.setAccessible(true);
                            enumFields.put(field.getName(), field.get(enumConstant));
                        }
                    }
                    enumValues.add(enumFields);
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return enumValues;
    }
}
