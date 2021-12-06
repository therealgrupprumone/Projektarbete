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

import java.util.List;

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

            userRepo.saveAll(List.of(jannis, joakim, albert, casper));

            MessageEntity message1 = new MessageEntity("Hello from jannis", jannis, feedEntity);
            MessageEntity message2 = new MessageEntity("Hello from joakim", joakim, feedEntity);
            messageRepo.saveAll(List.of(message1, message2));


//            log.info("Running with profile: " + profile);
//

//
//            RoleEntity admin = new RoleEntity("admin");
//            RoleEntity user = new RoleEntity("user");
//
//            UserEntity jannis = new UserEntity("jannis", "tyskland");
//            UserEntity joakim = new UserEntity("joakim", "sverige");
//            UserEntity albert = new UserEntity("albert", "danmark");
//            UserEntity casper = new UserEntity("casper", "ungern");
//            userRepo.saveAll(List.of(jannis, joakim, albert, casper));
//

//
//            jannis.addMessage(message1);
//            jannis.addRole(admin);
//            joakim.addRole(user);
//            joakim.addMessage(message2);
//            userRepo.saveAll(List.of(jannis, joakim, albert, casper));
//
//
//            Feed feed = new Feed();
//            feedService.createFeed(feed);
//
//            User dtoJannis = new User("Jannis", "tyskland");
//            User dtoJoakim = new User("Joakim", "sverige");
//            User dtoAlbert = new User("Albert", "danmark");
//            User dtoCasper = new User("Casper", "ungern");
//
//            userService.createUser(dtoJannis);
//            userService.createUser(dtoJoakim);
//            userService.createUser(dtoAlbert);
//            userService.createUser(dtoCasper);
//
//            Role adminRole = new Role("ROLE_ADMIN");
//            Role userRole = new Role("ROLE_USER");
//
//            roleService.createRole(adminRole);
//            roleService.createRole(userRole);
//
//            dtoJannis.addRole(adminRole);
//            dtoJoakim.addRole(userRole);
//            dtoAlbert.addRole(userRole);
//            dtoCasper.addRole(userRole);
//
//            Message messageJannis = new Message("Hej, här är ett meddelande!", dtoJannis, feed);
//            Message messageAlbert = new Message("Hej från Albert!", dtoAlbert, feed);
//            Message messageCasper = new Message("Hej från Casper", dtoCasper, feed);
//
//            dtoJannis.addMessage(messageJannis);
//            dtoAlbert.addMessage(messageAlbert);
//            dtoCasper.addMessage(messageCasper);
//
//            messageService.createMessage(messageJannis);
//            messageService.createMessage(messageAlbert);
//            messageService.createMessage(messageCasper);
        };
    }
}
