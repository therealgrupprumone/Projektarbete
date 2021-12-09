package se.iths.projektarbete.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import se.iths.projektarbete.dto.Role;
import se.iths.projektarbete.entity.RoleEntity;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Autowired
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    @Mapping(target = "users", ignore = true)
    Role toDto(RoleEntity entity);

    @Mapping(target = "users", ignore = true)
    RoleEntity fromDto(Role role);

}
