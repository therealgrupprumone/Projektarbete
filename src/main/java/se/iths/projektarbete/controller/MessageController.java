package se.iths.projektarbete.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.iths.projektarbete.dto.Message;
import se.iths.projektarbete.service.MessageService;

import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping("messages")
public class MessageController {

    private final MessageService messageService;

//    @GetMapping
//    public ResponseEntity<List<Message>> getAllMessages() {
//        return ResponseEntity.ok(messageService.findAll());
//    }

    @PostMapping
    public ResponseEntity<Message> postMessage(@RequestBody Message message, Principal principal) {
        message.setUsername(principal.getName());
        return ResponseEntity.ok(messageService.postMessage(message));
    }
}