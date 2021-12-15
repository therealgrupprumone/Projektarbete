package se.iths.projektarbete.thymeleaf;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iths.projektarbete.dto.Message;
import se.iths.projektarbete.dto.User;
import se.iths.projektarbete.service.MessageService;
import se.iths.projektarbete.service.UserService;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class ChatController {

    MessageService messageService;
    UserService userService;

    @GetMapping("/chat")
    String getChat(Model model) {
        model.addAttribute("welcome", "General Chat");
        model.addAttribute("chats", messageService.findAll());
        return "chat";
    }

    @PostMapping(value = "/sendMessage")
    String sendMessage(@ModelAttribute Message message, Principal principal) {
        message.setUsername(principal.getName());
        message.setFeedId(1L);
        messageService.postMessage(message);
        return "redirect:/chat";
    }

    @PostMapping("createAdmin")
    String createAdmin (@ModelAttribute User user){
        userService.createAdmin(user);
        return "redirect:/tempAdmin";
    }

    @GetMapping("/tempAdmin")
    String getAllAdmins (Model model){
        model.addAttribute("listOfAdmins", userService.getAllAdmins());
        return "tempAdmin";
    }


}
