package edu.uta.cse.serveme.service;

import edu.uta.cse.serveme.entity.User;

/**
 * @author housirvip
 */
public interface UserService {

    /**
     * register a new account, then return jwt token
     *
     * @param user User
     * @return String
     */
    User register(User user);

    /**
     * select a user where equal param uid
     *
     * @param uid Long
     * @return User
     */
    User userById(Long uid);

    /**
     * select a user where equal param firebaseUid
     *
     * @param firebaseUid String
     * @return User
     */
    User userByFirebaseUid(String firebaseUid);

    /**
     * update user where equal param user
     *
     * @param user User
     * @return Integer
     */
    User update(User user);
}
