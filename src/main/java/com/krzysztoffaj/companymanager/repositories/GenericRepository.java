//package com.krzysztoffaj.companymanager.repositories;
//
//import com.krzysztoffaj.companymanager.entities.EntityId;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.lang.reflect.ParameterizedType;
//import java.util.List;
//
//public abstract class GenericRepository<T extends EntityId> {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @SuppressWarnings({"unchecked"})
//    public T get(int id) {
//        return (T) entityManager.find(getGenericInstance().getClass(), id);
//    }
//
//    @SuppressWarnings({"unchecked"})
//    public List<T> getAll() {
//        return entityManager.createQuery("SELECT t FROM " + getInstanceSimpleName() + " t").getResultList();
//    }
//
//    public void add(T entity) {
//        entityManager.persist(entity);
//    }
//
//    public void update(T entity) {
//        entityManager.merge(entity);
//    }
//
//    public void delete(T entity) {
//        entityManager.remove(entityManager.contains(entity)
//                                     ? entity
//                                     : entityManager.merge(entity));
//    }
//
//    @SuppressWarnings({"deprecation", "unchecked"})
//    private T getGenericInstance() {
//        T instance = null;
//        try {
//            instance = (T) ((Class) ((ParameterizedType) this.getClass().
//                    getGenericSuperclass()).getActualTypeArguments()[0])
//                    .newInstance();
//        } catch (InstantiationException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
//        return instance;
//    }
//
//    private String getInstanceSimpleName() {
//        return getGenericInstance().getClass().getSimpleName();
//    }
//}
