package se.iths.projektarbete;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PopulateDatabase {

    // CommandLineRunner loadDatabase(MovieRepo movieRepo)
    @Bean
    CommandLineRunner loadDatabase() { //TODO
        return args -> {
            // TODO Insert database data
        };
    }
}
