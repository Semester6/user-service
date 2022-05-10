package watcherz.userservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import watcherz.userservice.exceptions.CouldNotSaveUserException;
import watcherz.userservice.model.RabbitMQUser;
import watcherz.userservice.model.User;
import watcherz.userservice.repository.UserRepository;

@Service @AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void saveUser(RabbitMQUser rabbitMQUser) throws CouldNotSaveUserException {
        User user;
        try{
            user = new User(rabbitMQUser.getUserId(), rabbitMQUser.getEmail());
           userRepository.save(user);
        }
        catch (Exception e){
            throw new CouldNotSaveUserException();
        }
    }

}