package watcherz.userservice.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${watcherz.rabbitmq.queue}")
    String queueName;

    @Value("${spring.rabbitmq.username}")
    String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    //create MessageListenerContainer using default connection factory
//    @Bean
//    MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory ) {
//        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
//        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
//        simpleMessageListenerContainer.setQueues(queue());
//        simpleMessageListenerContainer.setMessageListener(new RabbitMQConsumer());
//        return simpleMessageListenerContainer;
//
//    }

    //create custom connection factory
    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setUsername(password);
        return cachingConnectionFactory;
    }

}
