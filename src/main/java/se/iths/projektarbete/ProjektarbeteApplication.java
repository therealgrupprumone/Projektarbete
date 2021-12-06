package se.iths.projektarbete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class ProjektarbeteApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjektarbeteApplication.class, args);
    }
}
