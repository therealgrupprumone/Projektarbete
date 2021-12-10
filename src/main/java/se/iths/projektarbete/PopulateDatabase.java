package se.iths.projektarbete;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.iths.projektarbete.dto.Message;
import se.iths.projektarbete.dto.User;
import se.iths.projektarbete.entity.FeedEntity;
import se.iths.projektarbete.entity.RoleEntity;
import se.iths.projektarbete.repo.FeedRepo;
import se.iths.projektarbete.repo.RoleRepo;
import se.iths.projektarbete.service.MessageService;
import se.iths.projektarbete.service.UserService;

import java.time.LocalDateTime;

@Configuration
@Slf4j
public class PopulateDatabase {

    @Value("${current.profile:default profile}")
    private String profile;

    @Bean
    CommandLineRunner loadDatabase(
            RoleRepo roleRepo,
            FeedRepo feedRepo,
            UserService userService,
            MessageService messageService
    ) {
        return args -> {
            FeedEntity feedEntity = new FeedEntity();
            feedRepo.save(feedEntity);

            RoleEntity roleAdmin = new RoleEntity("ROLE_ADMIN");
            RoleEntity roleUser = new RoleEntity("ROLE_USER");
            roleRepo.save(roleAdmin);
            roleRepo.save(roleUser);

            User user = new User("boalbert", "hej");
            userService.createUser(user);

            Message firstMessage = new Message(1L, "First message", "boalbert", LocalDateTime.now(), 1L);
            Message secondMessage = new Message(2L, "Second message", "boalbert", LocalDateTime.now(), 1L);
            Message thirdMessage = new Message(3L, "Third message", "boalbert", LocalDateTime.now(), 1L);

            messageService.postMessage(firstMessage);
            messageService.postMessage(secondMessage);
            messageService.postMessage(thirdMessage);

            log.info("Running with profile: " + profile);

        };
    }
}
