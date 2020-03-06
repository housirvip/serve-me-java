package edu.uta.cse.serveme.repository;

import edu.uta.cse.serveme.entity.Bid;
import edu.uta.cse.serveme.entity.Vendor;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author housirvip
 */
public interface BidRepository extends CrudRepository<Bid, Long>, JpaSpecificationExecutor<Bid> {

    /**
     * find order by vendor
     *
     * @param vendor Vendor
     * @return Bid
     */
    Optional<Bid> findByVendor(Vendor vendor);

    /**
     * find order by id and vendor
     *
     * @param id     Long
     * @param vendor Vendor
     * @return Bid
     */
    Optional<Bid> findByIdAndVendor(Long id, Vendor vendor);
}
