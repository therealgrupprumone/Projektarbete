package se.iths.projektarbete.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import se.iths.projektarbete.dto.Message;
import se.iths.projektarbete.entity.MessageEntity;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Autowired
    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    Message toDto(MessageEntity message);

    MessageEntity fromDto(Message message);

}
