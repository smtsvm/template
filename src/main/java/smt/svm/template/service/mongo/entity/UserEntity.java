package smt.svm.template.service.mongo.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class UserEntity extends BaseEntity{

    @Id
    private String id;
    private String username;
    private String email;
}
