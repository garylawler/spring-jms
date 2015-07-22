package starter.app.listener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import starter.app.model.JmsPropertyConstants;

import javax.jms.JMSException;
import javax.jms.Message;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class JmsListenerAnnotationTests {

    private JmsAnnotationListener jmsAnnotationListener;
    private @Mock Message message;

    @Before
    public void onSetUp() throws Exception {
        jmsAnnotationListener = new JmsAnnotationListener();
        when(message.getStringProperty(isA(String.class))).thenReturn("message 1");
    }

    @Test
    public void onTopicMessage() throws Exception {
        jmsAnnotationListener.listenToQueue(message);
        verify(message, times(1)).getStringProperty(JmsPropertyConstants.THE_MESSAGE);
    }

    @Test
    public void onQueueMessage() throws Exception {
        jmsAnnotationListener.listenToTopic(message);
        verify(message, times(1)).getStringProperty(JmsPropertyConstants.THE_MESSAGE);
    }

    @Test(expected = RuntimeException.class)
    public void onTopicMessageException() throws Exception {
        when(message.getStringProperty(isA(String.class))).thenThrow(new JMSException("dummy"));
        jmsAnnotationListener.listenToQueue(message);
        verify(message, times(1)).getStringProperty(JmsPropertyConstants.THE_MESSAGE);
    }

    @Test(expected = RuntimeException.class)
    public void onQueueMessageException() throws Exception {
        when(message.getStringProperty(isA(String.class))).thenThrow(new JMSException("dummy"));
        jmsAnnotationListener.listenToTopic(message);
        verify(message, times(1)).getStringProperty(JmsPropertyConstants.THE_MESSAGE);
    }
}