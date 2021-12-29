package smt.svm.template.service.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
import smt.svm.template.service.BaseService;
import smt.svm.template.service.spring.MongoRepositoryHolder;

@Service("mongoBaseService")
@ConditionalOnProperty(name = {"mongo.enabled"}, havingValue = "true", matchIfMissing = true)
public class MongoServiceImpl implements BaseService {


    @Autowired
    private MongoRepositoryHolder coreRepositoryHolder;
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public Object saveEntity(Object object) {
        return getMongoRepository(object.getClass()).save(object);
    }

    @Override
    public Object findEntity(String cid, Class clazz) {
        return getMongoRepository(clazz).findById(cid).orElse(null);
    }

    @Override
    public boolean isExist(String cid, Class clazz){
        return getMongoRepository(clazz).existsById(cid);
    }

    @Override
    public void deleteEntity(String cid, Class clazz) {
        getMongoRepository(clazz).deleteById(cid);
    }

    public MongoRepository getMongoRepository(Class clazz) {
        Class clazzRepo = coreRepositoryHolder.getEntityRepositoryMap().get(clazz);
        return (MongoRepository) applicationContext.getBean(clazzRepo);
    }

}

