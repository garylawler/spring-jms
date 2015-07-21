package starter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.destination.DestinationResolver;
import org.springframework.jms.support.destination.DynamicDestinationResolver;
import org.springframework.transaction.PlatformTransactionManager;

import javax.jms.*;

@Configuration
@EnableJms
public class AnnotationConfig {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Autowired
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory, PlatformTransactionManager transactionManager, DestinationResolver destinationResolver) {
        DefaultJmsListenerContainerFactory factory =  new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setTransactionManager(transactionManager);
        factory.setDestinationResolver(destinationResolver);
        factory.setSubscriptionDurable(true);
        return factory;
    }

    @Bean(name = "jmsQueueListenerContainerFactory")
    @Autowired
    public DefaultJmsListenerContainerFactory jmsQueueListenerContainerFactory(DefaultJmsListenerContainerFactory factory) {
        factory.setSubscriptionDurable(false);
        return factory;
    }

    @Bean
    public DestinationResolver getDestinationResolver(Topic topic, Queue queue) {
        DynamicDestinationResolver destinationResolver = new DynamicDestinationResolver() {
            @Override
            public Destination resolveDestinationName(Session session, String destinationName, boolean pubSubDomain) throws JMSException {
                return pubSubDomain ? topic : queue;
            }
        };

        return destinationResolver;
    }
}
