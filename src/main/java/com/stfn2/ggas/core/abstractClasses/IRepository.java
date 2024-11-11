package com.stfn2.ggas.core.abstractClasses;

import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.core.interfaces.RepositoryCustom;
import com.stfn2.ggas.core.utils.Log;
import com.stfn2.ggas.tools.ToolDate;
import com.stfn2.ggas.tools.ToolNumber;
import com.stfn2.ggas.tools.ToolStr;
import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public abstract class IRepository<Entity extends BaseEntity, Filter extends FilterDTO> implements RepositoryCustom<Entity, Filter> {

   @Getter
   private final EntityManager em;
   private final String field;
   protected Class<Entity> typeOf;
   protected CriteriaBuilder cb = null;
   protected Filter filter;

   private Log log = new Log(this.getClass());

   public Query createQueryHQL(String queryHQL) {
      return this.em.createQuery(queryHQL);
   }

   public Query createQuerySQL(String querySQL) {
      return this.em.createNativeQuery(querySQL);
   }

   @Autowired
   public IRepository(EntityManager em, String field) {
      this.em = em;
      this.field = field;
   }

   @Override
   public Page<Entity> filter(Filter filter, Pageable pageable) {

      try {
         cb = em.getCriteriaBuilder();
         CriteriaQuery<Entity> cq = cb.createQuery(getTClass());
         Root<Entity> iRoot = cq.from(getTClass());

         CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
         Root<Entity> countRoot = countQuery.from(getTClass());

         ExecucaoQuery<Entity> excute = new ExecucaoQuery(cq, iRoot);
         ExecucaoQuery<Entity> excuteCount = new ExecucaoQuery(cq, countRoot);

         filterDefault(filter, excute);
         filterDefault(filter, excuteCount);

         filters(filter, excute);
         filters(filter, excuteCount);

         List<Predicate> predicates = excute.getPredicates();

         List<Predicate> countPredicates = excuteCount.getPredicates();

         if (!predicates.isEmpty()) {
            Predicate[] predicate = new Predicate[predicates.size()];
            predicates.toArray(predicate);

            cq.select(iRoot).where(predicate);

            countQuery
                    .select(cb.count(countRoot))
                    .where(cb.and(countPredicates.toArray(new Predicate[0])));
         } else {
            countQuery.select(cb.count(countRoot));
         }
         if (!getOrders(iRoot).isEmpty()) {
            cq.orderBy(getOrders(iRoot));
         }

         TypedQuery<Entity> query = em.createQuery(cq);
         query.setFirstResult((int) pageable.getOffset());
         query.setMaxResults(pageable.getPageSize());

         List<Entity> resultList = query.getResultList();

         TypedQuery<Long> countTypedQuery = em.createQuery(countQuery);
         long total = countTypedQuery.getSingleResult();

         Page<Entity> pagina = new PageImpl<>(resultList, pageable, total);

         return pagina;
      } catch (Exception e) {
         log.erro("ERRO_SISTEMA: ", e.toString());
         throw new RuntimeException("Erro");
      }

   }

   @Override
   public List<Entity> filter(Filter filter) {
      var cq = queryFactory(filter);

      TypedQuery<Entity> query = em.createQuery(cq);
      List<Entity> result = query.getResultList();

      if(result.isEmpty()){
         result = new ArrayList<>();
      }

      return result;
   }

   private CriteriaQuery<Entity> queryFactory(Filter filter) {
      cb = em.getCriteriaBuilder();
      CriteriaQuery<Entity> cq = cb.createQuery(getTClass());
      Root<Entity> iRoot = cq.from(getTClass());

      ExecucaoQuery<Entity> excute = new ExecucaoQuery(cq, iRoot);

      filterDefault(filter, excute);

      filters(filter, excute);

      List<Predicate> predicates = excute.getPredicates();

      if (!predicates.isEmpty()) {
         Predicate[] predicate = new Predicate[predicates.size()];
         predicates.toArray(predicate);

         cq.select(iRoot).where(predicate);
      }
      if (!getOrders(iRoot).isEmpty()) {
         cq.orderBy(getOrders(iRoot));
      }

      return cq;
   }

   private CriteriaQuery<Long> queryFactoryCount(Filter filter) {
      cb = em.getCriteriaBuilder();
      CriteriaQuery<Long> cq = cb.createQuery(Long.class);
      Root<Entity> iRoot = cq.from(getTClass());

      ExecucaoQuery<Entity> excute = new ExecucaoQuery(cq, iRoot);
      filterDefault(filter, excute);
      filters(filter, excute);

      List<Predicate> predicates = excute.getPredicates();

      if (!predicates.isEmpty()) {
         cq.select(cb.count(iRoot))
                 .where(cb.and(predicates.toArray(new Predicate[0])));
      } else {
         cq.select(cb.count(iRoot));
      }

      return cq;
   }


   @SuppressWarnings("unchecked")
   public Class<Entity> getTClass() {
      this.typeOf = (Class<Entity>) ((ParameterizedType) getClass()
              .getGenericSuperclass())
              .getActualTypeArguments()[0];
      return this.typeOf;
   }

   private List<Order> getOrders(Root<Entity> iRoot) {
      List<Order> orders = new ArrayList<>();
      orders.add(cb.asc(iRoot.get(field)));
      return orders;
   }

   protected abstract void filters(Filter filter, ExecucaoQuery<Entity> execute);
   //protected abstract void filtersProject(Filter filter, ExecucaoQuery<?> execute);

   private void filterDefault(Filter filter, ExecucaoQuery<Entity> execute) {
      addFilterPositive(execute, "id", filter.getId());
      addFilterLike(execute, "descricao", filter.getDescricao());
      addFilter(execute, "habilitado", filter.getHabilitado());
   }

   public void addFilter(ExecucaoQuery<Entity> execute, String key, Object value) {
      if (value == null) return;

      if (value instanceof String valorStr) {
         if (!ToolStr.hasData(valorStr)) return;
         execute.getPredicates().add(cb.equal((execute.getIRoot().get(key)), valorStr.toUpperCase()));
      } else {
         execute.getPredicates().add(cb.equal((execute.getIRoot().get(key)), value));
      }
   }

   public void addFilterLike(ExecucaoQuery<Entity> execute, String key, String value) {
      if (!ToolStr.hasData(value)) return;
      execute.getPredicates().add(cb.like(cb.upper(execute.getIRoot().get(key)), "%" + value.toUpperCase() + "%"));
   }

   public void addFilterPositive(ExecucaoQuery<Entity> execute, String key, Number value) {
      if (!ToolNumber.isPositive(value)) return;
      execute.getPredicates().add(cb.equal((execute.getIRoot().get(key)), value));
   }

   public void addFilterPositiveFk(ExecucaoQuery<Entity> execute, String key, Number value) {
      if (!ToolNumber.isPositive(value)) return;
      execute.getPredicates().add(cb.equal((execute.getIRoot().get(key).get("id")), value));
   }

   public void addFilterDay(ExecucaoQuery<Entity> execute, String key, Integer day) {        
        if(!ToolDate.isDiaValido(day)) return;
        Expression<Integer> dayExpression = cb.function("TO_NUMBER", Integer.class, cb.function("TO_CHAR", String.class, execute.getIRoot().get(key), cb.literal("DD")));
        Predicate dayPredicate = cb.equal(dayExpression, day);
        execute.getPredicates().add(dayPredicate);
    }

    public void addFilterMonth(ExecucaoQuery<Entity> execute, String key, Integer month) { 
        if(!ToolDate.isMesValido(month)) return;
        Expression<Integer> monthExpression = cb.function("TO_NUMBER", Integer.class, cb.function("TO_CHAR", String.class, execute.getIRoot().get(key), cb.literal("MM")));
        Predicate monthPredicate = cb.equal(monthExpression, month);
        execute.getPredicates().add(monthPredicate);
    }

    public void addFilterYear(ExecucaoQuery<Entity> execute, String key, Integer year) {        
        if(!ToolDate.isAnoValido(year)) return;
        Expression<Integer> yearExpression = cb.function("TO_NUMBER", Integer.class, cb.function("TO_CHAR", String.class, execute.getIRoot().get(key), cb.literal("YYYY")));
        Predicate yearPredicate = cb.equal(yearExpression, year);
        execute.getPredicates().add(yearPredicate);
    }

    public void addFilterMonthAndYear(ExecucaoQuery<Entity> execute, String key, Integer year, Integer month) {
        if(!ToolDate.isMesValido(month)) return;
        if(!ToolDate.isAnoValido(year)) return;
        addFilterYear(execute, key, year);
        addFilterMonth(execute, key, month);
    }
   
   public void addFilterSubAttribute(ExecucaoQuery<Entity> execute, String key, String subKey, Object value) {
      if (value instanceof String valorStr) {
         if (!ToolStr.hasData(valorStr)) return;
         execute.getPredicates().add(cb.equal((execute.getIRoot().get(key).get(subKey)), valorStr.toUpperCase()));
      } else if (value instanceof Number number) {
         if (!ToolNumber.isPositive(number)) return;
         execute.getPredicates().add(cb.equal((execute.getIRoot().get(key).get(subKey)), value));
      }
   }

   public void addFilterSubAttribute2(ExecucaoQuery<Entity> execute, String key, String subKey, String subKey2, Object value) {
      if (value instanceof String valorStr) {
         if (!ToolStr.hasData(valorStr)) return;
         execute.getPredicates().add(cb.equal((execute.getIRoot().get(key).get(subKey)).get(subKey2), valorStr.toUpperCase()));
      } else if (value instanceof Number number) {
         if (!ToolNumber.isPositive(number)) return;
         execute.getPredicates().add(cb.equal((execute.getIRoot().get(key).get(subKey)).get(subKey2), value));
      }
   }

   public void addFilterSubAttribute3(ExecucaoQuery<Entity> execute, String key, String subKey, String subKey2, String subKey3, Object value) {
      if (value instanceof String valorStr) {
         if (!ToolStr.hasData(valorStr)) return;
         execute.getPredicates().add(cb.equal((execute.getIRoot().get(key).get(subKey)).get(subKey2).get(subKey3), valorStr.toUpperCase()));
      } else if (value instanceof Number number) {
         if (!ToolNumber.isPositive(number)) return;
         execute.getPredicates().add(cb.equal((execute.getIRoot().get(key).get(subKey)).get(subKey2).get(subKey3), value));
      }
   }
}