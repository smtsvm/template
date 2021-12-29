package smt.svm.template.service.spring;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class MongoRepositoryHolder {

    private static MongoRepositoryHolder INSTANCE;

    private Map<Class, Class> entityRepositoryMap = new HashMap<>();

    @PostConstruct
    private void init() {
        INSTANCE = this;
    }

    public static MongoRepositoryHolder get() {
        return INSTANCE;
    }


    public void addEntityToMap(Class entity, Class repository) {
        entityRepositoryMap.putIfAbsent(entity, repository);
    }

    public Map<Class, Class> getEntityRepositoryMap() {
        return entityRepositoryMap;
    }
}