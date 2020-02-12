package edu.uta.cse.serveme.repository;

import edu.uta.cse.serveme.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author housirvip
 */
public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {

    /**
     * find user_info by uid
     *
     * @param uid Long
     * @return UserInfo
     */
    Optional<UserInfo> findByUid(Long uid);
}
