package com.mirac.usermanagement.service;

import com.mirac.usermanagement.model.User;

import java.util.List;

/**
 * Service interface to handle all of the operations about the {@link User} entity.
 *
 * @author miracy
 */
public interface UserService {

    /**
     * Signs up the given {@code user} or login if it exists.
     *
     * @param user
     * @return user in logged in status
     */
    User signUpUser(User user);

    /**
     * Retrieves the user with the given {@code username}.
     * @param username
     * @return found user
     */
    User retrieveUser(String username);

    /**
     * Retrieves all of the users that are in the system.
     * TODO: Pagination and searching/sorting criteria should be added in the future.
     * @return list of users
     */
    List<User> getAllUsers();

    /**
     * <b>Hard deletes</b> the user with the given {@code username}.
     *
     * @param username
     */
    void deleteUser(String username);

    /**
     * Logs in the user if the password is correct.
     *
     * @param user
     * @return user in logged in status
     */
    User loginUser(User user);

    /**
     * Logs out the user which has the given {@code username}.
     * @param username
     * @return true if the user is successfully logged out.
     */
    boolean logoutUser(String username);
}
