package smt.svm.template.service.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import smt.svm.template.service.mongo.entity.UserEntity;

public interface UserRepository extends MongoRepository<UserEntity, String> {
}
