package se.iths.projektarbete;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.iths.projektarbete.entity.RoleEntity;
import se.iths.projektarbete.entity.UserEntity;
import se.iths.projektarbete.repo.RoleRepo;
import se.iths.projektarbete.repo.UserRepo;

import java.util.List;

@Configuration
public class PopulateDatabase {

    // CommandLineRunner loadDatabase(MovieRepo movieRepo)
    @Bean
    CommandLineRunner loadDatabase(UserRepo userRepo, RoleRepo roleRepo) {
        return args -> {

//            FeedEntity feedEntity = new FeedEntity();

            RoleEntity roleEntity1 = new RoleEntity("admin");
            RoleEntity roleEntity2 = new RoleEntity("user");


            roleRepo.saveAll(List.of(roleEntity1, roleEntity2));


            UserEntity userEntity1 = new UserEntity("jannis", "tyskland");
            UserEntity userEntity2 = new UserEntity("joakim", "sverige");
            UserEntity userEntity3 = new UserEntity("albert", "danmark");
            UserEntity userEntity4 = new UserEntity("casper", "ungern");


//            MessageEntity messageEntity = new MessageEntity("Hello World", userEntity1, feedEntity);

            userEntity1.addRole(roleEntity1);
            userEntity2.addRole(roleEntity2);

            userRepo.saveAll(List.of(userEntity1, userEntity2, userEntity3, userEntity4));

        };
    }
}
