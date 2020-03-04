package edu.uta.cse.serveme.service;

import edu.uta.cse.serveme.entity.Address;
import edu.uta.cse.serveme.entity.User;
import edu.uta.cse.serveme.entity.Vendor;
import edu.uta.cse.serveme.specification.VendorSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

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
     * @return Vendor
     */
    Vendor findVendorByUser(User user);

    /**
     * select a vendor where equal param vid
     *
     * @param vid Long
     * @return Vendor
     */
    Vendor findVendorById(Long vid);

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

    /**
     * update address for user
     *
     * @param address Address
     * @return Address
     */
    Address update(Address address);

    /**
     * get address by user
     *
     * @param user User
     * @return List
     */
    List<Address> getAddress(User user);

    /**
     * delete one address
     *
     * @param address User
     * @return Address
     */
    Address deleteAddress(Address address);

    /**
     * find vendors by spec and pageable
     *
     * @param vendorSpecification VendorSpecification
     * @param pageable            Pageable
     * @return Page
     */
    Page<Vendor> findVendors(VendorSpecification vendorSpecification, Pageable pageable);
}
