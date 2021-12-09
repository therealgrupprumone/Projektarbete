package se.iths.projektarbete;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.iths.projektarbete.entity.FeedEntity;
import se.iths.projektarbete.entity.MessageEntity;
import se.iths.projektarbete.entity.RoleEntity;
import se.iths.projektarbete.entity.UserEntity;
import se.iths.projektarbete.repo.FeedRepo;
import se.iths.projektarbete.repo.MessageRepo;
import se.iths.projektarbete.repo.RoleRepo;
import se.iths.projektarbete.repo.UserRepo;
import se.iths.projektarbete.service.FeedService;
import se.iths.projektarbete.service.MessageService;
import se.iths.projektarbete.service.RoleService;
import se.iths.projektarbete.service.UserService;

@Configuration
@Slf4j
public class PopulateDatabase {

    @Value("${current.profile:default profile}")
    private String profile;

    // CommandLineRunner loadDatabase(MovieRepo movieRepo)
    @Bean
    CommandLineRunner loadDatabase(
            UserRepo userRepo,
            RoleRepo roleRepo,
            MessageRepo messageRepo,
            FeedRepo feedRepo,
            FeedService feedService,
            UserService userService,
            RoleService roleService,
            MessageService messageService
    ) {
        return args -> {
            FeedEntity feedEntity = new FeedEntity();
            feedRepo.save(feedEntity);

            RoleEntity roleAdmin = new RoleEntity("ROLE_ADMIN");
            RoleEntity roleUser = new RoleEntity("ROLE_USER");
            roleRepo.save(roleAdmin);
            roleRepo.save(roleUser);

            UserEntity jannis = new UserEntity("jannis", "tyskland");
            UserEntity joakim = new UserEntity("joakim", "sverige");
            UserEntity albert = new UserEntity("albert", "danmark");
            UserEntity casper = new UserEntity("casper", "ungern");

            userService.createUserEntity(jannis);
            userService.createUserEntity(joakim);
            userService.createUserEntity(albert);
            userService.createUserEntity(casper);

            MessageEntity message1 = new MessageEntity("Hello from jannis", jannis, feedEntity);
            MessageEntity message2 = new MessageEntity("Hello from joakim", joakim, feedEntity);

            messageService.createMessage(message1);
            messageService.createMessage(message2);

            log.info("Running with profile: " + profile);

        };
    }
}
