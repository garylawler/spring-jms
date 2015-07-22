package starter.app.sender;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import starter.app.model.CustomMessage;
import starter.app.model.JmsPropertyConstants;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.UUID;

public abstract class JmsSenderTemplate {

    public final void sendSimpleMessage(final String message) {
        final CustomMessage customMessage = new CustomMessage(message);

        getJmsTemplate().send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                Message message = session.createMessage();
                message.setStringProperty(JmsPropertyConstants.THE_MESSAGE, customMessage.getMyMessage());
                message.setJMSCorrelationID(UUID.randomUUID().toString());
                message.setJMSMessageID(UUID.randomUUID().toString());
                configureMessage(message);
                System.out.println("Sending message:" + customMessage.getMyMessage());
                return message;
            }
        });
    }
//
//    public final void sendComplexMessage(final String message) {
//        final CustomMessage customMessage = new CustomMessage(message);
//
//        getJmsTemplate().convertAndSend((Object) message, new MessagePostProcessor() {
//            @Override
//            public Message postProcessMessage(Message message) throws JMSException {
//                message.setStringProperty(JmsPropertyConstants.THE_MESSAGE, customMessage.getMyMessage());
//                message.setJMSCorrelationID(UUID.randomUUID().toString());
//                message.setJMSMessageID(UUID.randomUUID().toString());
//                configureMessage(message);
//                System.out.println("Sending message:" + customMessage.getMyMessage());
//                return message;
//            }
//        });
//    }

    abstract protected JmsTemplate getJmsTemplate();

    abstract protected void configureMessage(Message message) throws JMSException;
}
