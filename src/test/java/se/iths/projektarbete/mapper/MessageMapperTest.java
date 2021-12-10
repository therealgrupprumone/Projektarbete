package se.iths.projektarbete.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import se.iths.projektarbete.dto.Message;
import se.iths.projektarbete.entity.FeedEntity;
import se.iths.projektarbete.entity.MessageEntity;
import se.iths.projektarbete.entity.UserEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageMapperTest {

    private final MessageMapper messageMapper = Mappers.getMapper(MessageMapper.class);


    @Test
    void mappingMessageEntityToMessageDtoSavesUsername() {
        var messageEntity = new MessageEntity("Hello", new UserEntity("Albert", "password"), new FeedEntity());

        var messageDto = messageMapper.toDto(messageEntity);

        assertEquals("Albert", messageDto.getUsername());
    }

    @Test
    void mappingMessageEntityToMessageDtoSavesChatmessage() {
        var messageEntity = new MessageEntity("Hello", new UserEntity("Albert", "password"), new FeedEntity());

        var messageDto = messageMapper.toDto(messageEntity);

        assertEquals("Hello", messageDto.getChatMessage());
    }

    @Test
    void mappingMessageDtoToMessageEntitySavesUsername() {
        var messageDto = new Message();
        messageDto.setUsername("batman");
        messageDto.setChatMessage("im batman");
        messageDto.setFeedId(1L);

        var createdAt = LocalDateTime.now();
        messageDto.setCreatedAt(createdAt);

        var messageEntity = messageMapper.fromDto(messageDto);

        assertEquals("batman", messageEntity.getUser().getUsername());
        assertEquals("im batman", messageEntity.getChatMessage());
        assertEquals(1L, messageEntity.getFeed().getId());
        assertEquals(createdAt, messageEntity.getCreatedAt());

    }


}
