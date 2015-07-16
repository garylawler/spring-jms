package starter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import starter.app.listener.JmsListener;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;

@Configuration
public class ListenerConfig {

    private DefaultMessageListenerContainer createDefaultMessageListenerContainer(Destination destination, ConnectionFactory connectionFactory) {
        DefaultMessageListenerContainer listenerContainer = new DefaultMessageListenerContainer();
        listenerContainer.setConnectionFactory(connectionFactory);
        listenerContainer.setDestination(destination);
        return listenerContainer;
    }

    @Bean
    @Autowired
    public MessageListenerContainer getTopicMessageListenerContainer(Topic destination, ConnectionFactory connectionFactory) throws Exception {
        DefaultMessageListenerContainer listenerContainer = createDefaultMessageListenerContainer(destination, connectionFactory);
        listenerContainer.setMessageListener(new JmsListener());
        listenerContainer.setSubscriptionDurable(true);
        listenerContainer.setDurableSubscriptionName("durableSub"); // defaults to class name if not set
        return listenerContainer;
    }

    @Bean
    @Autowired
    public MessageListenerContainer getQueueMessageListenerContainer(Queue destination, ConnectionFactory connectionFactory) throws Exception {
        DefaultMessageListenerContainer listenerContainer = createDefaultMessageListenerContainer(destination, connectionFactory);
        listenerContainer.setMessageListener(new JmsListener());
        return listenerContainer;
    }

    @Bean
    @Autowired
    public JmsTransactionManager getJmsTransactionManager(ConnectionFactory connectionFactory) {
        return new JmsTransactionManager(connectionFactory);
    }
}
