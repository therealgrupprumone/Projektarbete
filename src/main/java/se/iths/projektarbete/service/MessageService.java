package se.iths.projektarbete.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import se.iths.projektarbete.dto.Message;
import se.iths.projektarbete.entity.MessageEntity;
import se.iths.projektarbete.entity.UserEntity;
import se.iths.projektarbete.exception.EmptyMessageException;
import se.iths.projektarbete.mapper.MessageMapper;
import se.iths.projektarbete.repo.MessageRepo;
import se.iths.projektarbete.repo.UserRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageMapper mapper;
    private MessageRepo messageRepo;
    private UserRepo userRepo;

    public List<Message> findAll() {
        List<Message> allMessages = new ArrayList<>();
        Iterable<MessageEntity> foundMessages = messageRepo.findAll();
        foundMessages.forEach(message -> {
            allMessages.add(mapper.toDto(message));
        });
        //Reversing list so that the most recent message is on top of the list in /chat
        Collections.reverse(allMessages);
        return allMessages;
    }

    public Message postMessage(Message message) throws EmptyMessageException {
        if (message.getChatMessage().equals(""))
            throw new EmptyMessageException("Message can not be empty");

        UserEntity byUsername = userRepo.findByUsername(message.getUsername());

        MessageEntity messageEntity = mapper.fromDto(message);
        messageEntity.setUser(byUsername);

        return mapper.toDto(messageRepo.save(messageEntity));

    }
}
