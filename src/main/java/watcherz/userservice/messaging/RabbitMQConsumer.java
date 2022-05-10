package watcherz.userservice.messaging;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurationSelector;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import watcherz.userservice.exceptions.CouldNotSaveUserException;
import watcherz.userservice.model.RabbitMQUser;

@Component
@AllArgsConstructor
public class RabbitMQConsumer implements RabbitListenerConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    private final MessageHandler messageHandler;

    @RabbitListener(queues = "${watcherz.rabbitmq.queue}")
    public void recievedMessage(RabbitMQUser user) throws CouldNotSaveUserException {
        logger.info("Recieved Message From RabbitMQ: " + user);
        messageHandler.handleUserCreation(user);
    }
}
