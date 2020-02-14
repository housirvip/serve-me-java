package edu.uta.cse.serveme.service.impl;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import edu.uta.cse.serveme.constant.Constant;
import edu.uta.cse.serveme.constant.ErrorMessage;
import edu.uta.cse.serveme.entity.User;
import edu.uta.cse.serveme.entity.UserInfo;
import edu.uta.cse.serveme.entity.UserToken;
import edu.uta.cse.serveme.repository.UserInfoRepository;
import edu.uta.cse.serveme.repository.UserRepository;
import edu.uta.cse.serveme.repository.UserTokenRepository;
import edu.uta.cse.serveme.service.UserService;
import edu.uta.cse.serveme.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author housirvip
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final UserTokenRepository userTokenRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    @Value("${user.role}")
    private String initRole;

    @Value("${user.level}")
    private Integer initLevel;

    @Override
    public String login(User auth) {
        User user = userRepository.findByEmail(auth.getEmail()).orElse(null);

        // Account didn't found
        Preconditions.checkNotNull(user, ErrorMessage.ACCOUNT_NOT_FOUND);
        // Account was banned
        Preconditions.checkArgument(user.getEnable(), ErrorMessage.ACCOUNT_DISABLED);
        // verify username and password
        Preconditions.checkArgument(passwordEncoder.matches(auth.getPassword(), user.getPassword()), ErrorMessage.ACCOUNT_PASSWORD_ERROR);

        return jwtUtils.encode(user.getId(), user.getRole());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String register(User auth) {

        // check if email already exist
        userRepository.findByEmail(auth.getEmail()).ifPresent(u -> {
            throw new RuntimeException(ErrorMessage.EMAIL_EXIST);
        });

        User user = new User();
        user.setEmail(auth.getEmail());
        user.setUsername(auth.getUsername());
        user.setPhone(auth.getPhone());
        user.setLevel(initLevel);
        user.setEnable(true);
        user.setPassword(passwordEncoder.encode(auth.getPassword()));

        List<String> roles = Lists.newArrayList(Constant.ROLE_PREFIX + Constant.USER);
        if (!Constant.USER.equals(initRole)) {
            roles.add(Constant.ROLE_PREFIX + initRole);
        }
        user.setRole(roles);

        userRepository.save(user);

        UserInfo userInfo = new UserInfo();
        userInfo.setUid(user.getId());
        userInfoRepository.save(userInfo);

        UserToken userToken = new UserToken();
        userToken.setUid(user.getId());
        userTokenRepository.save(userToken);

        return jwtUtils.encode(user.getId(), user.getRole());
    }

    @Override
    public User userById(Long uid) {
        Optional<User> user = userRepository.findById(uid);
        return user.orElse(null);
    }

    @Override
    public UserInfo infoByUid(Long uid) {
        Optional<UserInfo> userInfo = userInfoRepository.findByUid(uid);
        return userInfo.orElse(null);
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
