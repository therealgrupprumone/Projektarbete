package se.iths.projektarbete.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import se.iths.projektarbete.entity.MessageEntity;
import se.iths.projektarbete.repo.MessageRepo;

@Service
@AllArgsConstructor
public class MessageService {

    private MessageRepo messageRepo;

    public Iterable<MessageEntity> findAll() {
        return messageRepo.findAll();
    }
}
