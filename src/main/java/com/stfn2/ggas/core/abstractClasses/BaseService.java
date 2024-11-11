package com.stfn2.ggas.core.abstractClasses;

import com.stfn2.ggas.config.exceptions.types.ObjectNotFoundException;
import com.stfn2.ggas.config.exceptions.types.ValidationErrorException;
import com.stfn2.ggas.config.exceptions.utils.FieldMessage;
import com.stfn2.ggas.core.abstractClasses.combo.ComboDTO;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.core.utils.Log;
import com.stfn2.ggas.core.utils.QueryHQLExecutor;
import com.stfn2.ggas.core.utils.QuerySQLExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class BaseService<
        Entity extends BaseEntity,
        DTO extends BaseDTO,
        BasicDTO extends BaseDTO,
        Filter extends FilterDTO,
        Repository extends BaseRepository<Entity, Filter>> {

    @Autowired
    protected Repository repository;
    private Log log = new Log(this.getClass());

    private List<FieldMessage> erros;

    @Transactional
    public DTO createOrUpdate(DTO objeto) {
        erros = new ArrayList<>();
        validDto(objeto);

        if (!erros.isEmpty()) {
            throw new ValidationErrorException(erros);
        }

        Entity objetoDB = dtoToEntity(objeto);
        objetoDB.updateVersao();
        var newObj = this.save(objetoDB);
        DTO dto = entityToDTO(newObj);
        return dto;
    }

    public void addErro(String fild, String msg) {
        this.erros.add(new FieldMessage(fild, msg));
    }

    public DTO findById(Long id) {
        Entity entity = repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(id.toString()));
        DTO dto = entityToDTO(entity);
        return dto;
    }

    public Entity getById(Long id) {
        Optional<Entity> objeto = repository.findById(id);
        return objeto.orElse(null);
    }

    public List<BasicDTO> filter(Filter filter) {
        List<BasicDTO> dtos = this.repository.filter(filter).stream()
                .map(this::entityToBasicDTO)
                .collect(Collectors.toList());
        return dtos;
    }

    public List<Entity> filterEntity(Filter filter) {
        List<Entity>  entitys = this.repository.filter(filter);
        return entitys;
    }

    public List<BasicDTO> findAll() {
        List<BasicDTO> dtos = this.repository.findAll().stream()
                .map(this::entityToBasicDTO)
                .collect(Collectors.toList());
        return dtos;
    }

    public Page<BasicDTO> findAll(Filter filter, Pageable pageable) {
        Page<Entity> page = this.repository.filter(filter, pageable);
        Page<BasicDTO> result = page.map(this::entityToBasicDTO);
        return result;
    }

    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }

    public Entity dtoToEntity(DTO dto) {
        Class typeOf = (Class<Entity>) ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
        Entity objetoDB = (Entity) MapperImpl.parseObject(dto, typeOf);
        return objetoDB;
    }

    public BasicDTO entityToBasicDTO(Entity entity) {
        Class typeOf = (Class<BasicDTO>) ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[2];
        BasicDTO objetoDB = (BasicDTO) MapperImpl.parseObject(entity, typeOf);
        return objetoDB;
    }
    
    public DTO entityToDTO(Entity entity) {
        Class typeOf = (Class<DTO>) ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[1];
        DTO objetoDB = (DTO) MapperImpl.parseObject(entity, typeOf);
        return objetoDB;
    }

    public List<ComboDTO> getCombo() {
        List<Entity> list = this.repository.findAll();
        return list.stream().map(m -> {
            ComboDTO dto = new ComboDTO();
            dto.setDescricao(m.getDescricao());
            dto.setId(m.getId());
            return dto;
        }).collect(Collectors.toList());
    }

    @Transactional
    public Entity save(Entity entity) {
        entity = beforeSave(entity);
        erros = new ArrayList<>();
        this.validEntity(entity);

        if (!erros.isEmpty()) {
            throw new ValidationErrorException(erros);
        }
        entity = repository.save(entity);

        this.afterSave(entity);
        return (entity);
    }

    public Entity beforeSave(Entity entity) {
        return entity;
    }

    public Entity afterSave(Entity entity) {
        return entity;
    }

    public void validDto(DTO dto) {

    }
    public void validEntity(Entity entity) {

    }

    public Boolean hasContent(String str) {
        if (str == null) return false;
        return !str.isEmpty();
    }

    public Boolean isNull(Object ob) {
        return ob == null;
    }

    public Boolean isPositive(Number n) {
        if (n == null) return false;
        double d = n.doubleValue();
        return d > 0;
    }

    @Transactional
    public List<DTO> createOrUpdateList(List<DTO> dtos) {
        List<DTO> savedDtos = new ArrayList<>();
        for (DTO dto : dtos) {
                savedDtos.add(createOrUpdate(dto));
        }
        return savedDtos;
    }

    @Autowired
    IRepository<Entity, Filter> iRepository;
    public QueryHQLExecutor createQueryHQL(String queryHQL) {
        return new QueryHQLExecutor(iRepository.createQueryHQL(queryHQL), queryHQL);
    }

    public QuerySQLExecutor createQuerySQL(String querySQL) {
        return new QuerySQLExecutor(iRepository.createQuerySQL(querySQL), querySQL);
    }

}