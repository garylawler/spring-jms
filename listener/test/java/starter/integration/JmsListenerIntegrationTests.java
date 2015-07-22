package starter.integration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import starter.app.listener.JmsListener;
import starter.app.model.JmsPropertyConstants;
import starter.config.*;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, AnnotationConfig.class, ListenerConfig.class, TransactionConfig.class, TemplateConfig.class})
public class JmsListenerIntegrationTests {

    @Autowired
    @Qualifier("jmsQueueTemplate")
    private JmsTemplate jmsQueueTemplate;

    @Autowired
    @Qualifier("jmsTopicTemplate")
    private JmsTemplate jmsTopicTemplate;

    @Autowired
    private JmsListener jmsListener; // would make assertions on this if we had the hooks to do so

    private MessageCreator messageCreator;

    @Before
    public void onSetUp() {
        jmsQueueTemplate.setReceiveTimeout(5000);

        messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message message = session.createMessage();
                message.setStringProperty(JmsPropertyConstants.THE_MESSAGE, "test");
                return message;
            }
        };
    }

    @Test
    public void listenOnQueue() throws Exception {
        jmsQueueTemplate.send(messageCreator);
    }

    @Test
    public void listenOnToTopic() throws Exception {
        jmsTopicTemplate.send(messageCreator);
    }
}
