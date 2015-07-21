package starter.app.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Message;

@Component
public class JmsQueueSender extends JmsSenderTemplate {

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
