package starter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

@Configuration
@ComponentScan(basePackages = {"starter.app"})
public class TemplateConfig {

    @Bean(name = "jmsQueueTemplate")
    @Autowired
    public JmsTemplate getJmsQueueTemplate(Queue destination, ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setDefaultDestination(destination);

        return jmsTemplate;
    }

    @Bean(name = "jmsTopicTemplate")
    @Autowired
    public JmsTemplate getJmsTopicTemplate(Topic destination, ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setDefaultDestination(destination);
        jmsTemplate.setPubSubDomain(true);

        return jmsTemplate;
    }

}
