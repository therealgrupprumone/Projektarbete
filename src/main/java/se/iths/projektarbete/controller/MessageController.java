package se.iths.projektarbete.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.projektarbete.dto.Message;
import se.iths.projektarbete.exception.EmptyMessageException;
import se.iths.projektarbete.service.MessageService;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/messages")
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.ok(messageService.findAll());
    }

    @PostMapping
    public ResponseEntity<Message> postMessage(@RequestBody Message message, Principal principal) throws EmptyMessageException {
        message.setUsername(principal.getName());
        return ResponseEntity.ok(messageService.postMessage(message));
    }
}