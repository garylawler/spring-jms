package starter.app.listener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import starter.app.model.JmsPropertyConstants;

import javax.jms.Message;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class JmsListenerTests {

    private JmsListener jmsListener;
    private @Mock Message message;

    @Before
    public void onSetUp() throws Exception {
        jmsListener = new JmsListener();
        when(message.getStringProperty(isA(String.class))).thenReturn("message 2");
    }

    @Test
    public void onMessage() throws Exception {
        jmsListener.onMessage(message);
        verify(message, times(1)).getStringProperty(JmsPropertyConstants.THE_MESSAGE);
    }
}