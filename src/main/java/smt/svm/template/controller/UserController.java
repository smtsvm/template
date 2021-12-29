package smt.svm.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smt.svm.template.dto.UserDTO;
import smt.svm.template.service.BaseService;

@RestController
@CrossOrigin()
@RequestMapping(value = "api/v1.0/user")
public class UserController {

    @Autowired
    private BaseService baseService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserDTO> uploadDocument(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok((UserDTO) baseService.saveEntity(userDTO));
    }

}
