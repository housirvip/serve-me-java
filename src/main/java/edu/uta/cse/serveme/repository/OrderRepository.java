package edu.uta.cse.serveme.repository;

import edu.uta.cse.serveme.entity.Order;
import edu.uta.cse.serveme.entity.User;
import edu.uta.cse.serveme.entity.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author housirvip
 */
public interface OrderRepository extends CrudRepository<Order, Long>, JpaSpecificationExecutor<Order> {

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

    /**
     * find orders by user
     *
     * @param user User
     * @return List
     */
    List<Order> findByUser(User user);

    /**
     * find orders by vendor
     *
     * @param vendor Vendor
     * @return List
     */
    List<Order> findByVendor(Vendor vendor);

    /**
     * find orders by user, support to pagination
     *
     * @param user     User
     * @param pageable Pageable
     * @return Page
     */
    Page<Order> findByUser(User user, Pageable pageable);
}
