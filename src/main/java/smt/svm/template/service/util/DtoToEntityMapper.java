package smt.svm.template.service.util;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import smt.svm.template.dto.UserDTO;
import smt.svm.template.service.mongo.entity.UserEntity;

@Mapper
public interface DtoToEntityMapper {

    DtoToEntityMapper INSTANCE = Mappers.getMapper( DtoToEntityMapper.class );

    UserEntity userDTOToEntity(UserDTO userDTO);
    UserDTO userEntityToDTO(UserEntity userEntity);

}