package smt.svm.template.dto;

import lombok.Data;

@Data
public class UserDTO extends BaseDTO{
    private String id;
    private String username;
    private String email;
}
