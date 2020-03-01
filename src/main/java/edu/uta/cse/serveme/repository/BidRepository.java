package edu.uta.cse.serveme.repository;

import edu.uta.cse.serveme.entity.Bid;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author housirvip
 */
public interface BidRepository extends CrudRepository<Bid, Long> {

    /**
     * find order by customer id
     *
     * @param uid Long
     * @return User
     */
    Optional<Bid> findByUid(Long uid);

    /**
     * find order by customer id
     *
     * @param id Long
     * @param uid Long
     * @return User
     */
    Optional<Bid> findByIdAndUid(Long id, Long uid);
}
