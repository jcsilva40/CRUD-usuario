package com.stfn2.ggas.core.abstractClasses.entidade;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;
import org.springframework.stereotype.Service;

@Service
public class EntidadeService {
    
    @PersistenceContext
    private EntityManager entityManager;

    public String findDescricaoEnitdade(String entityName, Long id) {

        return getValueEntidade(entityName, BaseEntity::getDescricaoField, id);
    }
    
    public String findAbreviadoEnitdade(String entityName, Long id) {

        return getValueEntidade(entityName, BaseEntity::getAbreviadoField, id);
    }

    private String getValueEntidade(String entityName, Function<BaseEntity, String> fieldGetter, Long id) {
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
            String queryString = "SELECT e." + field + " FROM " + entityName + " e WHERE e.id = :id";
            TypedQuery<String> query = entityManager.createQuery(queryString, String.class);
            query.setParameter("id", id);

            return query.getSingleResult();
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            throw new RuntimeException("Erro ao executar a consulta para a entidade " + entityName, e);
        }
    }
}
