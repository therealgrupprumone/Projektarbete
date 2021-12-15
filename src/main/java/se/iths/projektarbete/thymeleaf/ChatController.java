package se.iths.projektarbete.thymeleaf;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import se.iths.projektarbete.dto.Message;
import se.iths.projektarbete.repo.UserRepo;
import se.iths.projektarbete.service.MessageService;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class ChatController {

    MessageService messageService;
    UserRepo userRepo;

    @GetMapping(value = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    String getChat(Model model) {
        var variable = new ReactiveDataDriverContextVariable(this.messageService.findAll(), 1);
        model.addAttribute("welcome", "General Chat");
        model.addAttribute("chats", messageService.findAll());
        model.addAttribute("updatedChat", variable);
        return "chat";
    }

    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    String sendMessage(@ModelAttribute Message message, Principal principal) {
        message.setUsername(principal.getName());
        message.setFeedId(1L);
        messageService.postMessage(message);
        return "redirect:/chat";
    }

}
