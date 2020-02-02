package edu.uta.cse.serveme.service.impl;

import com.github.pagehelper.Page;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import edu.uta.cse.serveme.constant.Constant;
import edu.uta.cse.serveme.constant.ErrorMessage;
import edu.uta.cse.serveme.constant.UserGroup;
import edu.uta.cse.serveme.entity.User;
import edu.uta.cse.serveme.entity.UserInfo;
import edu.uta.cse.serveme.mapper.UserInfoMapper;
import edu.uta.cse.serveme.mapper.UserMapper;
import edu.uta.cse.serveme.service.UserService;
import edu.uta.cse.serveme.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author housirvip
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserInfoMapper userInfoMapper;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    @Value("${user.role}")
    private String initRole;

    @Value("${user.group}")
    private UserGroup initGroup;

    @Value("${user.level}")
    private Integer initLevel;

    @Override
    public String login(User auth) {
        User user = userMapper.selcectByUsername(auth.getUsername());
        // Account wan't found
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
        // check if username, phone, or email already exist
        List<String> check = this.checkExist(auth);
        Preconditions.checkArgument(check.size() == 0, check.toString());

        User user = new User();
        user.setCreateTime(new Date());
        user.setEmail(auth.getEmail());
        user.setUsername(auth.getUsername());
        user.setPhone(auth.getPhone());
        user.setLevel(initLevel);
        user.setPassword(passwordEncoder.encode(auth.getPassword()));

        List<String> roles = Lists.newArrayList(Constant.ROLE_PREFIX + Constant.USER);
        if (!Constant.USER.equals(initRole)) {
            roles.add(Constant.ROLE_PREFIX + initRole);
        }
        user.setRole(roles);

        userMapper.insertSelective(user);

        UserInfo userInfo = new UserInfo();
        userInfo.setUid(user.getId());
        userInfo.setCreateTime(new Date());
        userInfoMapper.insertSelective(userInfo);

        return jwtUtils.encode(user.getId(), user.getRole());
    }

    @Override
    public User oneById(Integer uid) {
        User user = userMapper.selectByPrimaryKey(uid);
        user.setPassword(null);
        return user;
    }

    @Override
    public User oneByIdWithInfo(Integer uid) {
        User user = userMapper.selectByPrimaryKey(uid);
        user.setPassword(null);
        UserInfo userInfo = userInfoMapper.selectByUid(uid);
        user.setUserInfo(userInfo);
        return user;
    }

    @Override
    public Page<User> pageByParam(int pageNum, int pageSize, Map<String, Object> params) {
        return userMapper.listByParam(params);
    }

    @Override
    public Integer update(User user) {
        return null;
    }

    @Override
    public Integer updateInfo(UserInfo userInfo) {
        return null;
    }

    private List<String> checkExist(User userDto) {

        List<String> result = Lists.newArrayList();

        if (Boolean.TRUE.equals(userMapper.existUsername(userDto.getUsername()))) {
            result.add(ErrorMessage.USERNAME_EXIST);
        }
        if (Boolean.TRUE.equals(userMapper.existEmail(userDto.getEmail()))) {
            result.add(ErrorMessage.EMAIL_EXIST);
        }
        if (Boolean.TRUE.equals(userMapper.existPhone(userDto.getPhone()))) {
            result.add(ErrorMessage.PHONE_EXIST);
        }

        return result;
    }
}
