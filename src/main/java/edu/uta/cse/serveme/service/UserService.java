package edu.uta.cse.serveme.service;

import edu.uta.cse.serveme.entity.User;
import edu.uta.cse.serveme.entity.Vendor;

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
    User findUserById(Long uid);

    /**
     * select a user where equal param firebaseUid
     *
     * @param firebaseUid String
     * @return User
     */
    User findUserByFirebaseUid(String firebaseUid);

    /**
     * select a vendor where equal param user
     *
     * @param user User
     * @return User
     */
    Vendor findVendorByUser(User user);

    /**
     * update user where equal param user
     *
     * @param user User
     * @return User
     */
    User update(User user);

    /**
     * update vendor for user
     *
     * @param vendor Vendor
     * @return Vendor
     */
    Vendor update(Vendor vendor);
}
