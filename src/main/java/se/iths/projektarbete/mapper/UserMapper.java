package se.iths.projektarbete.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import se.iths.projektarbete.dto.User;
import se.iths.projektarbete.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Autowired
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toDto(UserEntity entity);

    UserEntity fromDto(User user);
}
