package edu.uta.cse.serveme.repository;

import edu.uta.cse.serveme.entity.UserToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author housirvip
 */
public interface UserTokenRepository extends CrudRepository<UserToken, Long> {

    /**
     * find user_token by uid
     *
     * @param uid Long
     * @return UserInfo
     */
    Optional<UserToken> findByUid(Long uid);
}
