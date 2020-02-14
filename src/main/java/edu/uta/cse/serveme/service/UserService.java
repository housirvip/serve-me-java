package edu.uta.cse.serveme.service;

import edu.uta.cse.serveme.entity.User;
import edu.uta.cse.serveme.entity.UserInfo;
import edu.uta.cse.serveme.entity.UserToken;

/**
 * @author housirvip
 */
public interface UserService {
    /**
     * verify account and password, then return jwt token
     *
     * @param user User
     * @return String
     */
    String login(User user);

    /**
     * register a new account, then return jwt token
     *
     * @param user User
     * @return String
     */
    String register(User user);

    /**
     * select a user where equal param uid
     *
     * @param uid Long
     * @return User
     */
    User userById(Long uid);

    /**
     * select a user where equal param uid
     *
     * @param uid Long
     * @return User
     */
    UserInfo infoByUid(Long uid);

    /**
     * update user where equal param user
     *
     * @param user User
     * @return Integer
     */
    Integer updateUser(User user);

    /**
     * update userInfo where equal param userInfo
     *
     * @param userInfo UserInfo
     * @return Integer
     */
    Integer updateInfo(UserInfo userInfo);


    /**
     * select a user_token where equal param uid
     *
     * @param uid Integer
     * @return UserToken
     */
    UserToken tokenByUid(Integer uid);

    /**
     * update user_token where equal param userInfo
     *
     * @param userToken UserToken
     * @return Long
     */
    Long updateToken(UserToken userToken);
}
