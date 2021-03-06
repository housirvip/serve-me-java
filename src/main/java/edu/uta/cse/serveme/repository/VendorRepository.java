package edu.uta.cse.serveme.repository;

import edu.uta.cse.serveme.entity.User;
import edu.uta.cse.serveme.entity.Vendor;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author housirvip
 */
public interface VendorRepository extends CrudRepository<Vendor, Long>, JpaSpecificationExecutor<Vendor> {

    /**
     * find vendor by phone
     *
     * @param phone String
     * @return Vendor
     */
    Optional<Vendor> findByPhone(String phone);

    /**
     * find vendor by email
     *
     * @param email String
     * @return Vendor
     */
    Optional<Vendor> findByEmail(String email);

    /**
     * find vendor by user
     *
     * @param user Long
     * @return Vendor
     */
    Optional<Vendor> findByUser(User user);
}
