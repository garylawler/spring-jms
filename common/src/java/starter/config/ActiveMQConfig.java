package starter.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

@Configuration
public class ActiveMQConfig {

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
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL("tcp://127.0.0.1:61616");
        connectionFactory.setClientID("testApp");

        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setTargetConnectionFactory(connectionFactory);

        return cachingConnectionFactory;
    }
}
