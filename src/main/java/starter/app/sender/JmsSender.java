package starter.app.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import starter.app.model.JmsPropertyConstants;
import starter.app.model.CustomMessage;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.UUID;

@Component
public class JmsSender {

    @Autowired
    @Qualifier("jmsTopicTemplate")
    protected JmsTemplate jmsTemplate;

    public void sendMessage(final String message) {
        final CustomMessage customMessage = new CustomMessage(message);

        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                Message message = session.createMessage();
                message.setStringProperty(JmsPropertyConstants.THE_MESSAGE, customMessage.getMyMessage());
                message.setJMSCorrelationID(UUID.randomUUID().toString());
                message.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
                message.setJMSMessageID(UUID.randomUUID().toString());
                System.out.println("Sending message");
                return message;
            }
        });
    }
}
