package se.iths.projektarbete;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.iths.projektarbete.entity.RoleEntity;
import se.iths.projektarbete.entity.UserEntity;
import se.iths.projektarbete.repo.FeedRepo;
import se.iths.projektarbete.repo.MessageRepo;
import se.iths.projektarbete.repo.RoleRepo;
import se.iths.projektarbete.repo.UserRepo;
import se.iths.projektarbete.service.UserService;

@Configuration
@Slf4j
public class PopulateDatabase {

    @Value("${current.profile:default profile}")
    private String profile;

    // CommandLineRunner loadDatabase(MovieRepo movieRepo)
    @Bean
    CommandLineRunner loadDatabase(UserRepo userRepo, RoleRepo roleRepo, MessageRepo messageRepo, FeedRepo feedRepo, UserService userService) {
        return args -> {

            RoleEntity roleEntity = new RoleEntity("ROLE_USER");
            roleRepo.save(roleEntity);

            UserEntity userEntity = new UserEntity("Jannis", "tyskland");
            userService.createUser(userEntity);
//
//            log.info("Running with profile: " + profile);
//
//            FeedEntity feed = new FeedEntity(List.of());
//            feedRepo.save(feed);
//
////            RoleEntity admin = new RoleEntity("ROLE_ADMIN");
//            RoleEntity roleUser = new RoleEntity("ROLE_USER");
//
//            roleRepo.save(roleUser);
//
//            UserEntity jannis = new UserEntity("jannis", "tyskland");
//            UserEntity joakim = new UserEntity("joakim", "sverige");
//            UserEntity albert = new UserEntity("albert", "danmark");
//            UserEntity casper = new UserEntity("casper", "ungern");
////            userRepo.saveAll(List.of(jannis, joakim, albert, casper));
//
////            jannis.addRole(admin);
//            jannis.addRole(roleUser);
//            joakim.addRole(roleUser);
//            albert.addRole(roleUser);
//            casper.addRole(roleUser);
//
//
//            MessageEntity message1 = new MessageEntity("Hello from jannis", jannis, feed);
//            MessageEntity message2 = new MessageEntity("Hello from joakim", joakim, feed);
//
//            jannis.addMessage(message1);
//
//            joakim.addMessage(message2);
//
//            userService.createUser(jannis);
//            userService.createUser(joakim);
//            userService.createUser(albert);
//            userService.createUser(casper);
        };
    }
}
