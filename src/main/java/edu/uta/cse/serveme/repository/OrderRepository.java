package edu.uta.cse.serveme.repository;

import edu.uta.cse.serveme.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author housirvip
 */
public interface OrderRepository extends CrudRepository<Order, Long> {

    /**
     * find order by customer id
     *
     * @param uid Long
     * @return User
     */
    Optional<Order> findByUid(Long uid);

    /**
     * find order by vendor id
     *
     * @param vid Long
     * @return User
     */
    Optional<Order> findByVid(Long vid);
}
