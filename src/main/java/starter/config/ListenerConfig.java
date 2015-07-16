package starter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import starter.app.listener.JmsListener;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

@Configuration
public class ListenerConfig {

    @Bean
    @Autowired
    public MessageListenerContainer getMessageListenerContainer(Queue destination, ConnectionFactory connectionFactory) throws Exception {
        DefaultMessageListenerContainer listenerContainer = new DefaultMessageListenerContainer();
        listenerContainer.setConnectionFactory(connectionFactory);
        listenerContainer.setDestination(destination);
        listenerContainer.setMessageListener(new JmsListener());
        return listenerContainer;
    }
}
