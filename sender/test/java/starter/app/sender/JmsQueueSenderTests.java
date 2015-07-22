package starter.app.sender;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class JmsQueueSenderTests {

    private JmsTopicSender jmsTopicSender;
    private JmsQueueSender jmsQueueSender;
    private @Mock JmsTemplate jmsTemplate;

    @Before
    public void onSetUp() throws Exception {
        jmsTopicSender = new JmsTopicSender();
        jmsQueueSender = new JmsQueueSender();

        jmsTopicSender.jmsTopicTemplate = jmsTemplate;
        jmsQueueSender.jmsQueueTemplate = jmsTemplate;
    }

    @Test
    public void sendToTopic() throws Exception {
        jmsTopicSender.sendSimpleMessage("message");
        verify(jmsTemplate, times(1)).send(isA(MessageCreator.class));
    }

    @Test
    public void sendToQueue() throws Exception {
        jmsQueueSender.sendSimpleMessage("message");
        verify(jmsTemplate, times(1)).send(isA(MessageCreator.class));
    }
}