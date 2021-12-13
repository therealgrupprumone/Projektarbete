package se.iths.projektarbete.thymeleaf;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iths.projektarbete.dto.Message;
import se.iths.projektarbete.service.MessageService;

@Controller
@AllArgsConstructor
public class ChatController {

    MessageService messageService;

    @GetMapping("/chat")
    String getChat(Model model) {
        model.addAttribute("welcome", "General Chat");
        model.addAttribute("chats", messageService.findAll());
        return "chat";
    }

    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    String sendMessage(@ModelAttribute Message message) {
        message.setUsername("boalbert");
        message.setFeedId(1L);
        messageService.postMessage(message);
        return "redirect:/chat";
    }

}
