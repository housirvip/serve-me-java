package edu.uta.cse.serveme.service.impl;

import edu.uta.cse.serveme.entity.User;
import edu.uta.cse.serveme.entity.UserRole;
import edu.uta.cse.serveme.entity.Vendor;
import edu.uta.cse.serveme.repository.UserRepository;
import edu.uta.cse.serveme.repository.VendorRepository;
import edu.uta.cse.serveme.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * @author housirvip
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final VendorRepository vendorRepository;

    @Value("${user.role}")
    private UserRole[] initRole;

    @Value("${user.points}")
    private Integer initPoints;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User register(User auth) {

        // check if email already exist
//        if (auth.getEmail() != null) {
//            userRepository.findByEmail(auth.getEmail()).ifPresent(u -> {
//                throw new RuntimeException(ErrorMessage.EMAIL_EXIST);
//            });
//        }
        // check if phone already exist
//        if (auth.getPhone() != null) {
//            userRepository.findByPhone(auth.getPhone()).ifPresent(u -> {
//                throw new RuntimeException(ErrorMessage.PHONE_EXIST);
//            });
//        }

        User user = new User();
        user.setEmail(auth.getEmail());
        user.setUsername(auth.getUsername());
        user.setPhone(auth.getPhone());
        user.setFirebaseUid(auth.getFirebaseUid());
        user.setPoints(initPoints);
        user.setRole(Arrays.asList(initRole));

        userRepository.save(user);

        return user;
    }

    @Override
    public User findUserById(Long uid) {
        return userRepository.findById(uid).orElse(null);
    }

    @Override
    public User findUserByFirebaseUid(String firebaseUid) {
        return userRepository.findByFirebaseUid(firebaseUid).orElse(null);
    }

    @Override
    public User update(User user) {
        // fields in below shouldn't be changed via update user info
        user.setVendor(null);
        user.setFirebaseUid(null);
        return userRepository.save(user);
    }

    @Override
    public Vendor update(Vendor vendor) {
        return vendorRepository.save(vendor);
    }
}
