package edu.uta.cse.serveme.repository;

import edu.uta.cse.serveme.entity.Order;
import edu.uta.cse.serveme.entity.User;
import edu.uta.cse.serveme.entity.Vendor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author housirvip
 */
public interface OrderRepository extends CrudRepository<Order, Long> {

    /**
     * find order by order id and user
     *
     * @param id   Long
     * @param user User
     * @return User
     */
    Optional<Order> findByIdAndUser(Long id, User user); ///

    /**
     * find order by order id and vendor
     *
     * @param id     Long
     * @param vendor Vendor
     * @return User
     */
    Optional<Order> findByIdAndVendor(Long id, Vendor vendor);
}
