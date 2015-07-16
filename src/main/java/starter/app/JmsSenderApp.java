package starter.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import starter.app.sender.JmsSender;
import starter.config.ActiveMQConfig;
import starter.config.TemplateConfig;

public class JmsSenderApp {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext springContext = new AnnotationConfigApplicationContext();
        springContext.register(TemplateConfig.class, ActiveMQConfig.class);
        springContext.refresh();

        JmsSender jmsSender = springContext.getBean(JmsSender.class);

        while(true) {
            jmsSender.sendMessage("this is the message");
            Thread.sleep(10000);
        }
    }
}
