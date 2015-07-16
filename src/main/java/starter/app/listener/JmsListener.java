package starter.app.listener;

import org.springframework.stereotype.Component;
import starter.app.model.JmsPropertyConstants;
import starter.app.model.CustomMessage;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@Component
public class JmsListener implements MessageListener {
    public void onMessage(Message message) {
        try {
            CustomMessage customMessage = new CustomMessage(message.getStringProperty(JmsPropertyConstants.THE_MESSAGE));
            Thread.sleep(1000);
            System.out.println("Message received: " + customMessage.getMyMessage());
        } catch (JMSException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
