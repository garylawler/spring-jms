package starter.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import starter.app.sender.JmsQueueSender;
import starter.app.sender.JmsTopicSender;
import starter.config.ActiveMQConfig;
import starter.config.TemplateConfig;

public class JmsSenderApp {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext springContext = new AnnotationConfigApplicationContext();
        springContext.register(TemplateConfig.class, ActiveMQConfig.class);
        springContext.refresh();

        JmsTopicSender jmsTopicSender = springContext.getBean(JmsTopicSender.class);
        JmsQueueSender jmsQueueSender = springContext.getBean(JmsQueueSender.class);

        while(true) {
            jmsTopicSender.sendMessage("this is a topic message");
            Thread.sleep(1000);
            jmsQueueSender.sendMessage("this is a queue message");
            Thread.sleep(1000);
        }
    }
}
