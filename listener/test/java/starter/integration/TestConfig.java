package starter.integration;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

@Configuration
public class TestConfig {

    @Bean
    public Queue getQueue() {
        return new ActiveMQQueue("queue.test");
    }

    @Bean
    public Topic getTopic() {
        return new ActiveMQTopic("topic.test");
    }

    @Bean
    public ConnectionFactory getConnectionFactory() {
        return new org.apache.activemq.ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
    }

}
