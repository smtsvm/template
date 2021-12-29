package smt.svm.template.service;

public interface BaseService {
    Object saveEntity(Object object);

    Object findEntity(String cid, Class clazz);

    boolean isExist(String cid, Class clazz);

    void deleteEntity(String cid, Class clazz);
}
