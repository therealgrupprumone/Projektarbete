package se.iths.projektarbete.jms.message.receiver;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import se.iths.projektarbete.jms.message.JMSMessage;

@Component
public class JMSReceiver {

    @JmsListener(destination = "MY_QUEUE")
    public void listen(@Payload JMSMessage message){
        System.out.println("I got a message!");
        System.out.println(message);
    }





}
