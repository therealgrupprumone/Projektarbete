package se.iths.projektarbete.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import se.iths.projektarbete.dto.Message;
import se.iths.projektarbete.entity.MessageEntity;
import se.iths.projektarbete.mapper.MessageMapper;
import se.iths.projektarbete.repo.MessageRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageMapper mapper;
    private MessageRepo messageRepo;

    public List<Message> findAll() {
        List<Message> allMessages = new ArrayList<>();
        Iterable<MessageEntity> foundMessages = messageRepo.findAll();
        foundMessages.forEach(message -> {
            allMessages.add(mapper.toDto(message));
        });
        return allMessages;
    }
}
