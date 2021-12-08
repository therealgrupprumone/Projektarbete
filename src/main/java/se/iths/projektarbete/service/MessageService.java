package se.iths.projektarbete.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import se.iths.projektarbete.dto.Message;
import se.iths.projektarbete.entity.MessageEntity;
import se.iths.projektarbete.entity.UserEntity;
import se.iths.projektarbete.mapper.MessageMapper;
import se.iths.projektarbete.repo.MessageRepo;
import se.iths.projektarbete.repo.UserRepo;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
        return allMessages;
    }

    @Transactional
    public void createMessage(Message message) {

        UserEntity byUsername = userRepo.findByUsername(message.getUser().getUsername());
        userRepo.save(byUsername);
        messageRepo.save(mapper.fromDto(message));
    }
}
