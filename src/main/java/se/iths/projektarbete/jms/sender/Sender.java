package se.iths.projektarbete.jms.sender;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import se.iths.projektarbete.dto.SendTestDTO;
import se.iths.projektarbete.jms.config.JmsConfig;

@Component
public class Sender {

    JmsTemplate jmsTemplate;

    public Sender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(String username) {

        SendTestDTO sendTestDTO = new SendTestDTO(1L, "New user created: ", username);
        System.out.println("New user created: " + sendTestDTO);
        jmsTemplate.convertAndSend(JmsConfig.EMAIL_QUEUE, sendTestDTO);

    }


}
