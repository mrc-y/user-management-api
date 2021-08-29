package com.mirac.usermanagement.service.impl;

import com.mirac.usermanagement.exception.BadRequestException;
import com.mirac.usermanagement.exception.WrongPasswordException;
import com.mirac.usermanagement.model.User;
import com.mirac.usermanagement.repository.UserRepository;
import com.mirac.usermanagement.service.UserService;
import com.mirac.usermanagement.util.PasswordEncryptionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author miracy
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User signUpUser(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            // user exists, therefore we should login if the password is true.
            validatePassword(user, existingUser);
            existingUser.setLoggedIn(true);
            userRepository.save(existingUser);
            return existingUser;
        } else {
            user.setLoggedIn(true);
            String hashedPass = PasswordEncryptionUtil.generateHash(user.getPassword());
            user.setPassword(hashedPass);
            return userRepository.insert(user);
        }
    }

    @Override
    public User retrieveUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username);

        if (user != null) {
            userRepository.deleteById(user.getId());
        }

    }

    @Override
    public User loginUser(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            validatePassword(user, existingUser);
            existingUser.setLoggedIn(true);
            userRepository.save(existingUser);
            return existingUser;
        } else {
            throw new BadRequestException("User does not exists.");
        }
    }

    /**
     * Validates the password and throws error if it's not valid.
     *
     * @param user
     * @param existingUser
     */
    private void validatePassword(User user, User existingUser) {
        boolean isPasswordTrue = PasswordEncryptionUtil
                .validatePassword(user.getPassword(), existingUser.getPassword());
        if (!isPasswordTrue) {
            throw new WrongPasswordException("Password is wrong!");
        }
    }

    @Override
    public boolean logoutUser(String username) {
        User existingUser = userRepository.findByUsername(username);
        if (existingUser != null) {
            existingUser.setLoggedIn(false);
            userRepository.save(existingUser);
            return true;
        } else {
            throw new BadRequestException("User does not exists.");
        }
    }
}
