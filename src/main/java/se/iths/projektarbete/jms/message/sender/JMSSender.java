package se.iths.projektarbete.jms.message.sender;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import se.iths.projektarbete.jms.message.JMSMessage;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class JMSSender {

//    JmsTemplate jmsTemplate;
//
//    public JMSSender(JmsTemplate jmsTemplate) {
//        this.jmsTemplate = jmsTemplate;
//    }
//
//    @Scheduled(fixedRate = 2000) //every two seconds
//    public void sendMessage(){
//        System.out.println("Sending Message");
//        JMSMessage message = new JMSMessage(UUID.randomUUID(),"Hello from JU19_QUEUE!", LocalDateTime.now());
//        jmsTemplate.convertAndSend("MY_QUEUE",message);
//        System.out.println("Message sent!");
//
//    }
}
