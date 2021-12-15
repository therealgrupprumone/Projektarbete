package se.iths.projektarbete.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import se.iths.projektarbete.dto.Message;
import se.iths.projektarbete.entity.MessageEntity;
import se.iths.projektarbete.entity.UserEntity;
import se.iths.projektarbete.mapper.MessageMapper;
import se.iths.projektarbete.repo.MessageRepo;
import se.iths.projektarbete.repo.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageMapper mapper;
    private MessageRepo messageRepo;
    private UserRepo userRepo;

    public Flux<List<Message>> findAll() {
        List<Message> allMessages = new ArrayList<>();
        Iterable<MessageEntity> foundMessages = messageRepo.findAll();
        foundMessages.forEach(message -> {
            allMessages.add(mapper.toDto(message));
        });
        return Flux
                .fromStream(Stream.generate(() -> allMessages));
    }

    public Message postMessage(Message message) {
        UserEntity byUsername = userRepo.findByUsername(message.getUsername());

        MessageEntity messageEntity = mapper.fromDto(message);
        messageEntity.setUser(byUsername);

        return mapper.toDto(messageRepo.save(messageEntity));

    }
}
