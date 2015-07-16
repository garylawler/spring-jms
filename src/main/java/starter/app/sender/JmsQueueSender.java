package starter.app.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import starter.app.model.CustomMessage;
import starter.app.model.JmsPropertyConstants;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.UUID;

@Component
public class JmsQueueSender extends AbstractJmsSender {

    @Autowired
    @Qualifier("jmsQueueTemplate")
    protected JmsTemplate jmsQueueTemplate;

    @Override
    protected JmsTemplate getJmsTemplate() {
        return jmsQueueTemplate;
    }

    @Override
    protected void configureMessage(Message message) {

    }
}
