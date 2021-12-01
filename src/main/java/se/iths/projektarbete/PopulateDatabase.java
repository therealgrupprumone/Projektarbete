package se.iths.projektarbete;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.iths.projektarbete.entity.UserEntity;
import se.iths.projektarbete.repo.UserRepo;

import java.util.List;

@Configuration
public class PopulateDatabase {

    // CommandLineRunner loadDatabase(MovieRepo movieRepo)
    @Bean
    CommandLineRunner loadDatabase(UserRepo userRepo) {
        return args -> {

            UserEntity userEntity1 = new UserEntity("jannis", "tyskland");
            UserEntity userEntity2 = new UserEntity("joakim", "sverige");
            UserEntity userEntity3 = new UserEntity("albert", "danmark");
            UserEntity userEntity4 = new UserEntity("casper", "ungern");

            userRepo.saveAll(List.of(userEntity1, userEntity2, userEntity3, userEntity4));

        };
    }
}
