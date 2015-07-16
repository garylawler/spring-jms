package starter.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.listener.MessageListenerContainer;
import starter.config.ActiveMQConfig;
import starter.config.TemplateConfig;
import starter.config.ListenerConfig;

public class JmsListenerApp {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext springContext = new AnnotationConfigApplicationContext();
        springContext.register(TemplateConfig.class, ActiveMQConfig.class, ListenerConfig.class);
        springContext.refresh();
    }
}
