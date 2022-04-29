package watcherz.userservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import watcherz.userservice.exceptions.*;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler
    public ResponseEntity<String> handleEmailDoesNotExistException(EmailDoesNotExistException e) {
        log.error("Email does not exist", e);
        return new ResponseEntity<>("Email does not exist", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> couldNotSaveUserException(CouldNotSaveUserException e) {
        log.error("Could not save user", e);
        return new ResponseEntity<>("Could not save user", HttpStatus.CONFLICT);
    }
}
