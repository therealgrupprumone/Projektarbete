package se.iths.projektarbete;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import se.iths.projektarbete.dto.Message;
import se.iths.projektarbete.dto.User;
import se.iths.projektarbete.entity.FeedEntity;
import se.iths.projektarbete.entity.RoleEntity;
import se.iths.projektarbete.repo.FeedRepo;
import se.iths.projektarbete.repo.RoleRepo;
import se.iths.projektarbete.service.MessageService;
import se.iths.projektarbete.service.UserService;

@Configuration
@Slf4j
@Profile("!test")
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

            User user = new User("boalbert", "hejhej", "boalbert@test.se");
            userService.createUser(user);

            User userAdmin = new User("jannis", "hejhej", "jannis@test.se");
            userService.createAdmin(userAdmin);

            Message firstMessage = new Message(1L, "First message", "boalbert", "2021-12-24 13:37", 1L);
            Message secondMessage = new Message(2L, "Second message", "boalbert", "2021-12-24 13:37", 1L);
            Message thirdMessage = new Message(3L, "Third message, and this one is a bit longer.", "boalbert", "2021-12-24 13:37", 1L);
            Message fourthMessage = new Message(4L, "Fourth message, and this one is even longer. Morbi non feugiat nisi. Nullam semper egestas tellus sed lacinia. Suspendisse varius libero non lorem interdum, ut porta ex volutpat.", "jannis", "2021-12-24 13:37", 1L);

            messageService.postMessage(firstMessage);
            messageService.postMessage(secondMessage);
            messageService.postMessage(thirdMessage);
            messageService.postMessage(fourthMessage);

            log.info("Running with profile: " + profile);

        };
    }
}
