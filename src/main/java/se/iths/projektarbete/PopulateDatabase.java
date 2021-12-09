package se.iths.projektarbete;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.iths.projektarbete.entity.MessageEntity;
import se.iths.projektarbete.entity.RoleEntity;
import se.iths.projektarbete.entity.UserEntity;
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
    CommandLineRunner loadDatabase(
            UserRepo userRepo,
            RoleRepo roleRepo,
            MessageRepo messageRepo
    ) {
        return args -> {

            RoleEntity roleAdmin = new RoleEntity("ROLE_ADMIN");
            RoleEntity roleUser = new RoleEntity("ROLE_USER");
            roleRepo.save(roleAdmin);
            roleRepo.save(roleUser);

            UserEntity jannis = new UserEntity("jannis", "tyskland");
            UserEntity joakim = new UserEntity("joakim", "sverige");
            UserEntity albert = new UserEntity("albert", "danmark");
            UserEntity casper = new UserEntity("casper", "ungern");

            userRepo.saveAll(List.of(jannis, joakim, albert, casper));

            MessageEntity message1 = new MessageEntity("Hello from jannis", jannis);
            MessageEntity message2 = new MessageEntity("Hello from joakim", joakim);
            messageRepo.saveAll(List.of(message1, message2));

        };
    }
}
