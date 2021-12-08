package se.iths.projektarbete.jms.sender;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import se.iths.projektarbete.jms.config.JmsConfig;
import se.iths.projektarbete.jms.model.MessageObject;


import java.time.LocalDateTime;

import java.util.UUID;

@Component
public class Sender {
    JmsTemplate jmsTemplate;


    public Sender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {

        System.out.println("Sending list with active users...");
        MessageObject messageObject = new MessageObject(UUID.randomUUID(), "active users: ", LocalDateTime.now());
        jmsTemplate.convertAndSend(JmsConfig.CHAT_APP_QUEUE, messageObject);
        System.out.println("List sent!");

    }



}
