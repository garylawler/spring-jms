package starter.app.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import starter.app.model.CustomMessage;
import starter.app.model.JmsPropertyConstants;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class JmsAnnotationListener {

    @JmsListener(destination = "topic.test", subscription = "annotatedDurableSub")
    public void listenToTopic(Message message) {
        try {
            CustomMessage customMessage = new CustomMessage(message.getStringProperty(JmsPropertyConstants.THE_MESSAGE));
            Thread.sleep(1000);
            System.out.println("Annotated listener: message received: " + customMessage.getMyMessage());
        } catch (JMSException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @JmsListener(destination = "queue.test", containerFactory = "jmsQueueListenerContainerFactory")
    public void listenToQueue(Message message) {
        try {
            CustomMessage customMessage = new CustomMessage(message.getStringProperty(JmsPropertyConstants.THE_MESSAGE));
            Thread.sleep(1000);
            System.out.println("Annotated queue listener: message received: " + customMessage.getMyMessage());
        } catch (JMSException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
