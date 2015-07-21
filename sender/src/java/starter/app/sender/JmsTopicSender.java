package starter.app.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class JmsTopicSender extends JmsSenderTemplate {

    @Autowired
    @Qualifier("jmsTopicTemplate")
    protected JmsTemplate jmsTopicTemplate;

    @Override
    protected JmsTemplate getJmsTemplate() {
        return jmsTopicTemplate;
    }

    @Override
    protected void configureMessage(Message message) throws JMSException {
        message.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
    }
}
