package se.iths.projektarbete;

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

import java.util.List;

@Configuration
public class PopulateDatabase {

    // CommandLineRunner loadDatabase(MovieRepo movieRepo)
    @Bean
    CommandLineRunner loadDatabase(UserRepo userRepo, RoleRepo roleRepo, MessageRepo messageRepo, FeedRepo feedRepo) {
        return args -> {

            FeedEntity feed = new FeedEntity(List.of());
            feedRepo.save(feed);

            RoleEntity admin = new RoleEntity("admin");
            RoleEntity user = new RoleEntity("user");
            roleRepo.saveAll(List.of(admin, user));

            UserEntity jannis = new UserEntity("jannis", "tyskland");
            UserEntity joakim = new UserEntity("joakim", "sverige");
            UserEntity albert = new UserEntity("albert", "danmark");
            UserEntity casper = new UserEntity("casper", "ungern");
            userRepo.saveAll(List.of(jannis, joakim, albert, casper));

            MessageEntity message = new MessageEntity("Hello World", jannis, feed);
            messageRepo.save(message);

            jannis.addMessage(message);
            jannis.addRole(admin);
            joakim.addRole(user);
            userRepo.saveAll(List.of(jannis, joakim, albert, casper));
        };
    }
}
