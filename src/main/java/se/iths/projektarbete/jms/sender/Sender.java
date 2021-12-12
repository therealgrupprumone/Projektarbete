package se.iths.projektarbete.jms.sender;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import se.iths.projektarbete.dto.EmailNotification;
import se.iths.projektarbete.jms.config.JmsConfig;

@Component
public class Sender {

    JmsTemplate jmsTemplate;

    public Sender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(EmailNotification emailNotification) {
        jmsTemplate.convertAndSend(JmsConfig.EMAIL_QUEUE, emailNotification);
    }


}
