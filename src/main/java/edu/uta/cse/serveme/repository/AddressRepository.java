package edu.uta.cse.serveme.repository;

import edu.uta.cse.serveme.entity.Address;
import edu.uta.cse.serveme.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author housirvip
 */
public interface AddressRepository extends CrudRepository<Address, Long> {

    /**
     * find address by user
     *
     * @param user User
     * @return User
     */
    List<Address> findByUser(User user);

    /**
     * find address by user
     *
     * @param id   Long
     * @param user User
     * @return User
     */
    Optional<Address> findByIdAndUser(Long id, User user);
}
