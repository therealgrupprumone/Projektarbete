package se.iths.projektarbete.jms.receiver;


import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import se.iths.projektarbete.jms.config.JmsConfig;
import se.iths.projektarbete.jms.model.MessageObject;

@Component
public class Receiver {

    @JmsListener(destination = JmsConfig.CHAT_APP_QUEUE)
    public void listen(@Payload MessageObject messageObject) {
        System.out.println("I got a list with active user");
        System.out.println(messageObject);
    }

}
