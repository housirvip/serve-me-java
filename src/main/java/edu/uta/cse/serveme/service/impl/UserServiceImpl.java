package edu.uta.cse.serveme.service.impl;

import com.google.common.collect.Lists;
import edu.uta.cse.serveme.constant.ErrorMessage;
import edu.uta.cse.serveme.entity.User;
import edu.uta.cse.serveme.entity.UserInfo;
import edu.uta.cse.serveme.entity.UserToken;
import edu.uta.cse.serveme.repository.UserInfoRepository;
import edu.uta.cse.serveme.repository.UserRepository;
import edu.uta.cse.serveme.repository.UserTokenRepository;
import edu.uta.cse.serveme.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author housirvip
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final UserTokenRepository userTokenRepository;

    @Value("${user.role}")
    private String[] initRole;

    @Value("${user.points}")
    private Integer initPoints;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User register(User auth) {

        // check if email already exist
        if (auth.getEmail() != null) {
            userRepository.findByEmail(auth.getEmail()).ifPresent(u -> {
                throw new RuntimeException(ErrorMessage.EMAIL_EXIST);
            });
        }
        // check if phone already exist
        if (auth.getPhone() != null) {
            userRepository.findByPhone(auth.getPhone()).ifPresent(u -> {
                throw new RuntimeException(ErrorMessage.PHONE_EXIST);
            });
        }

        User user = new User();
        user.setEmail(auth.getEmail());
        user.setUsername(auth.getUsername());
        user.setPhone(auth.getPhone());
        user.setFirebaseUid(auth.getFirebaseUid());
        user.setPoints(initPoints);
        user.setRole(Lists.newArrayList(initRole));

        userRepository.save(user);

        UserInfo userInfo = new UserInfo();
        userInfo.setUid(user.getId());
        userInfoRepository.save(userInfo);

        UserToken userToken = new UserToken();
        userToken.setUid(user.getId());
        userTokenRepository.save(userToken);

        return user;
    }

    @Override
    public User userById(Long uid) {
        return userRepository.findById(uid).orElse(null);
    }

    @Override
    public User userByFirebaseUid(String firebaseUid) {
        return userRepository.findByFirebaseUid(firebaseUid).orElse(null);
    }

    @Override
    public UserInfo infoByUid(Long uid) {
        return userInfoRepository.findByUid(uid).orElse(null);
    }

    @Override
    public Integer updateUser(User user) {
        return null;
    }

    @Override
    public Integer updateInfo(UserInfo userInfo) {
        return null;
    }

    @Override
    public UserToken tokenByUid(Integer uid) {
        return null;
    }

    @Override
    public Long updateToken(UserToken userToken) {
        userTokenRepository.findByUid(userToken.getUid()).ifPresent(t -> {
            userToken.setId(t.getId());
            t.setFcmToken(userToken.getFcmToken());
            userTokenRepository.save(t);
        });
        return userToken.getId();
    }
}
