package edu.uta.cse.serveme.repository;

import edu.uta.cse.serveme.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author housirvip
 */
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * find user by phone
     *
     * @param phone String
     * @return User
     */
    Optional<User> findByPhone(String phone);

    /**
     * find user by email
     *
     * @param email String
     * @return User
     */
    Optional<User> findByEmail(String email);

    /**
     * use it to check if exist same email,phone,username
     *
     * @param email    String
     * @param phone    String
     * @param username String
     * @return List
     */
    List<User> findByEmailOrPhoneOrUsername(String email, String phone, String username);
}
