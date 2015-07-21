package starter.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import starter.config.ActiveMQConfig;
import starter.config.ListenerConfig;
import starter.config.TemplateConfig;

public class JmsListenerApp {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext springContext = new AnnotationConfigApplicationContext();
        springContext.register(TemplateConfig.class, ActiveMQConfig.class, ListenerConfig.class);
        springContext.refresh();
    }
}
