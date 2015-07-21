package starter.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import starter.config.*;

public class JmsListenerApp {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext springContext = new AnnotationConfigApplicationContext();
        springContext.register(TemplateConfig.class,
                TransactionConfig.class,
                ActiveMQConfig.class,
                AnnotationConfig.class,
                ListenerConfig.class);
        springContext.refresh();
    }
}
