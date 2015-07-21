package starter.app.listener;

import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import starter.app.model.CustomMessage;

@Component
@EnableJms
public class JmsAnnotationListener {

    @JmsListener(destination = "topic.test", subscription = "annotatedDurableSub", containerFactory = "jmsListenerContainerFactory")
    public void listen(String data) {
        try {
            CustomMessage customMessage = new CustomMessage(data);
            Thread.sleep(1000);
            System.out.println("annotated listener:" + customMessage.getMyMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
