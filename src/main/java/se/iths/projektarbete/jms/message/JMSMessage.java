package se.iths.projektarbete.jms.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
public class JMSMessage implements Serializable {
    private String jmsMessage;

    @Override
    public String toString() {
        return "JMSMessage{" +
                "jmsMessage='" + jmsMessage + '\'' +
                '}';
    }
}
