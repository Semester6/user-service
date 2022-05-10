package watcherz.userservice.messaging;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import watcherz.userservice.exceptions.CouldNotSaveUserException;
import watcherz.userservice.model.RabbitMQUser;
import watcherz.userservice.service.UserService;

@Service @AllArgsConstructor
public class MessageHandler {

    private final UserService userService;

    public void handleUserCreation(RabbitMQUser user) throws CouldNotSaveUserException {
            userService.saveUser(user);
    }
}
