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
        var userEntity = new UserEntity("username", "password", "email@email.se");
        var messageEntity = new MessageEntity();

        messageEntity.setUser(userEntity);
        messageEntity.setChatMessage("This must not be null");

        var messageDto = messageMapper.toDto(messageEntity);

        assertEquals("username", messageDto.getUsername());
    }

    @Test
    void mappingMessageEntityToMessageDtoSavesChatmessage() {
        var messageEntity = new MessageEntity();
        messageEntity.setChatMessage("This is a message");

        var messageDto = messageMapper.toDto(messageEntity);

        assertEquals("This is a message", messageDto.getChatMessage());
    }

    @Test
    void mappingMessageEntityToMessageDtoSavesFeedId() {
        var feedEntity = new FeedEntity();
        feedEntity.setId(5L);
        var messageEntity = new MessageEntity();
        messageEntity.setChatMessage("This is a message");
        messageEntity.setFeed(feedEntity);

        var messageDto = messageMapper.toDto(messageEntity);

        assertEquals(5L, messageDto.getFeedId());
    }

    @Test
    void mappingMessageEntityToMessageDtoSavesCreatedAt() {

        var messageEntity = new MessageEntity();
        var createdAt = "2021-12-24 13:37";

        messageEntity.setChatMessage("This must not be null");
        messageEntity.setCreatedAt(createdAt);

        var messageDto = messageMapper.toDto(messageEntity);

        assertEquals(createdAt, messageDto.getCreatedAt());
    }

    @Test
    void mappingMessageDtoToMessageEntitySavesUsername() {
        var messageDto = new Message();
        messageDto.setUsername("batman");
        messageDto.setChatMessage("im batman"); // This is non-null so it needs to be set

        var messageEntity = messageMapper.fromDto(messageDto);

        assertEquals("batman", messageEntity.getUser().getUsername());
    }

    @Test
    void mappingMessageDtoToMessageEntitySavesChatmessage() {
        var messageDto = new Message();
        messageDto.setUsername("robin"); // This is non-null so it needs to be set
        messageDto.setChatMessage("im robin");

        var messageEntity = messageMapper.fromDto(messageDto);

        assertEquals("im robin", messageEntity.getChatMessage());
    }

    @Test
    void mappingMessageDtoToMessageEntitySavesFeedId() {
        var messageDto = new Message();
        messageDto.setUsername("robin"); // This is non-null so it needs to be set
        messageDto.setChatMessage("im robin"); // This is non-null so it needs to be set
        messageDto.setFeedId(5L);

        var messageEntity = messageMapper.fromDto(messageDto);

        assertEquals(5L, messageEntity.getFeed().getId());
    }


}
