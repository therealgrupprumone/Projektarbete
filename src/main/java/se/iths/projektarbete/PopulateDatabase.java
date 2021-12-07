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
import se.iths.projektarbete.repo.RoleRepo;
import se.iths.projektarbete.service.UserService;

@Configuration
@Slf4j
public class PopulateDatabase {

    @Value("${current.profile:default profile}")
    private String profile;

    // CommandLineRunner loadDatabase(MovieRepo movieRepo)
    @Bean
    CommandLineRunner loadDatabase(RoleRepo roleRepo, FeedRepo feedRepo, UserService userService) {
        return args -> {

            var feed = new FeedEntity();
            feedRepo.save(feed);

            var roleUser = new RoleEntity("ROLE_USER");
            var roleAdmin = new RoleEntity("ROLE_ADMIN");

            roleRepo.save(roleUser);
            roleRepo.save(roleAdmin);

            var userJannis = new UserEntity("Jannis", "tyskland");
            var userAlbert = new UserEntity("Albert", "sverige");

            var msgJannis = new MessageEntity("First Hello!", userJannis, feed);
            userJannis.addMessage(msgJannis);

            var msgAlbert = new MessageEntity("Hello again!", userAlbert, feed);
            userAlbert.addMessage(msgAlbert);

            // Krypterar lösenord, assignar roll och sparar entitet via userRepo i databasen
            userService.createUser(userAlbert, "ROLE_ADMIN");
            userService.createUser(userJannis, "ROLE_USER");

            log.info("Running with profile: " + profile);
        };
    }
}
