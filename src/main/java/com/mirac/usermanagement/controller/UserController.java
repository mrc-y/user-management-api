package com.mirac.usermanagement.controller;

import com.mirac.usermanagement.model.User;
import com.mirac.usermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller class for handling user operations.
 *
 * @author miracy
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "user", consumes = "application/json", produces = "application/json")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUpUser(@Valid @RequestBody User user) {
        log.info("UserController.signUpUser() has been called.");

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userService.signUpUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@Valid @RequestBody User user) {
        log.info("UserController.loginUser() has been called.");

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userService.loginUser(user), HttpStatus.OK);
    }

    @PostMapping("/logout/{username}")
    public ResponseEntity<Boolean> logoutUser(@PathVariable String username) {
        log.info("UserController.logoutUser() has been called.");

        if (ObjectUtils.isEmpty(username)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userService.logoutUser(username), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> retrieveUser(@PathVariable String id) {
        log.info("UserController.retrieveUser() has been called.");
        User user = userService.retrieveUser(id);
        return new ResponseEntity<>(user, user == null ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        log.info("UserController.getAllUsers() has been called.");
        List<User> userList = userService.getAllUsers();
        return new ResponseEntity<>(userList, userList.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<User> deleteUser(@PathVariable String username) {
        log.info("UserController.deleteUser() has been called.");
        userService.deleteUser(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
