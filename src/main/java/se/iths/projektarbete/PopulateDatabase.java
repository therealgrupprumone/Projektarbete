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

import java.util.List;

@Configuration
@Slf4j
public class PopulateDatabase {

    @Value("${current.profile:default profile}")
    private String profile;

    // CommandLineRunner loadDatabase(MovieRepo movieRepo)
    @Bean
    CommandLineRunner loadDatabase(UserRepo userRepo, RoleRepo roleRepo, MessageRepo messageRepo, FeedRepo feedRepo) {
        return args -> {


            log.info("Running with profile: " + profile);

            FeedEntity feed = new FeedEntity(List.of());
            feedRepo.save(feed);    /** VARFÖR BEHÖVER MAN SPARA DENNA **/

            RoleEntity admin = new RoleEntity("admin");
            RoleEntity user = new RoleEntity("user");
//            roleRepo.saveAll(List.of(admin, user));
            /** VARFÖR BEHÖVER MAN INTE SPARA DENNA **/

            UserEntity jannis = new UserEntity("jannis", "tyskland");
            UserEntity joakim = new UserEntity("joakim", "sverige");
            UserEntity albert = new UserEntity("albert", "danmark");
            UserEntity casper = new UserEntity("casper", "ungern");
            userRepo.saveAll(List.of(jannis, joakim, albert, casper));  /** VARFÖR BEHÖVER MAN SPARA DENNA **/

            MessageEntity message1 = new MessageEntity("Hello from jannis", jannis, feed);
            MessageEntity message2 = new MessageEntity("Hello from joakim", joakim, feed);
//            messageRepo.saveAll(List.of(message1, message2));
            /** VARFÖR BEHÖVER MAN INTE SPARA DENNA **/

            jannis.addMessage(message1);
            jannis.addRole(admin);
            joakim.addRole(user);
            joakim.addMessage(message2);
            userRepo.saveAll(List.of(jannis, joakim, albert, casper));
        };
    }
}
