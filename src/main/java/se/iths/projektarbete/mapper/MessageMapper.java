package se.iths.projektarbete.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import se.iths.projektarbete.dto.Message;
import se.iths.projektarbete.entity.MessageEntity;

@Mapper(componentModel = "spring", uses = {FeedMapper.class})
public interface MessageMapper {

    @Autowired
    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    //spara bara username
//    @Mapping(target = "feed", ignore = true)
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "feed.id", target = "feedId")
    Message toDto(MessageEntity message);

    //    @Mapping(target = "feed", ignore = true)
    MessageEntity fromDto(Message message);

}
