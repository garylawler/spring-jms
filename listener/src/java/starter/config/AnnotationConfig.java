package starter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.destination.DynamicDestinationResolver;
import org.springframework.transaction.PlatformTransactionManager;

import javax.jms.*;

@Configuration
@EnableJms
public class AnnotationConfig {

    @Bean(name = "jmsListenerContainerFactory")
    @Autowired
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory, PlatformTransactionManager transactionManager, Queue queue, Topic topic) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setTransactionManager(transactionManager);
        factory.setSubscriptionDurable(true);

        DynamicDestinationResolver destinationResolver = new DynamicDestinationResolver() {
            @Override
            public Destination resolveDestinationName(Session session, String destinationName, boolean pubSubDomain) throws JMSException {
                return pubSubDomain ? topic : queue;
            }
        };

        factory.setDestinationResolver(destinationResolver);

        return factory;
    }
}
