package se.iths.projektarbete.thymeleaf;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.iths.projektarbete.dto.Message;
import se.iths.projektarbete.dto.User;
import se.iths.projektarbete.service.MessageService;
import se.iths.projektarbete.service.UserService;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class ChatController {

    private final MessageService messageService;
    private final UserService userService;

    @GetMapping("/chat")
    public String getChat(Model model) {
        model.addAttribute("welcome", "General Chat");
        model.addAttribute("chats", messageService.findAll());
        return "chat";
    }

    @PostMapping(value = "/sendMessage")
    public String sendMessage(@ModelAttribute Message message, Principal principal) {
        message.setUsername(principal.getName());
        message.setFeedId(1L);
        messageService.postMessage(message);
        return "redirect:/chat";
    }

    @PostMapping("createAdmin")
    public String createAdmin(@ModelAttribute User user) {
        System.out.println("createAdmin called in ChatController with: " + user.toString());
        userService.createAdmin(user);
        return "redirect:/admin";
    }

    @PostMapping("createUser")
    public String createUser(@ModelAttribute User user) {
        System.out.println("createUser in ChatController called with " + user.toString());
        userService.createUser(user);
        return "chat";
    }

    @GetMapping("/admin")
    public String getAllAdmins(Model model) {
        model.addAttribute("listOfAdmins", userService.getAllUsers());
        return "admin";
    }

}
