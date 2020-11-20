package com.pjbruer.springbootrealworldblog.controller;

import com.pjbruer.springbootrealworldblog.exception.UserInfoNotFoundException;
import com.pjbruer.springbootrealworldblog.model.User;
import com.pjbruer.springbootrealworldblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController()
@RequestMapping("api")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {this.userRepository = userRepository; }

    @GetMapping("/user/{username}")
    public ResponseEntity<User> findUserByUsername(@PathVariable String username) {
        try {
            return new ResponseEntity<>(userRepository.findByUsername(username), HttpStatus.OK);

        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request");
        }
    }

    private void handleFindUserException(Throwable throwable) {
        if (throwable instanceof UserInfoNotFoundException) {
            //logger.warn("User not found in controller method findUserByUsername " + throwable.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User not found");
        }
    }
}

