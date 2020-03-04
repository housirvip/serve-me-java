package edu.uta.cse.serveme.service.impl;

import edu.uta.cse.serveme.base.ErrorMessage;
import edu.uta.cse.serveme.entity.Address;
import edu.uta.cse.serveme.entity.User;
import edu.uta.cse.serveme.entity.UserRole;
import edu.uta.cse.serveme.entity.Vendor;
import edu.uta.cse.serveme.repository.AddressRepository;
import edu.uta.cse.serveme.repository.UserRepository;
import edu.uta.cse.serveme.repository.VendorRepository;
import edu.uta.cse.serveme.service.UserService;
import edu.uta.cse.serveme.specification.VendorSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author housirvip
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final VendorRepository vendorRepository;
    private final AddressRepository addressRepository;

    @Value("${user.role}")
    private UserRole[] initRole;

    @Value("${user.points}")
    private Integer initPoints;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User register(User auth) {
        User user = new User();
        user.setEmail(auth.getEmail());
        user.setUsername(auth.getUsername());
        user.setPhone(auth.getPhone());
        user.setFirebaseUid(auth.getFirebaseUid());
        user.setPoints(initPoints);
        user.setRole(Arrays.asList(initRole));
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Long uid) {
        return userRepository.findById(uid).orElseThrow(() -> new RuntimeException(ErrorMessage.USER_NOT_FOUND));
    }

    @Override
    public User findUserByFirebaseUid(String firebaseUid) {
        return userRepository.findByFirebaseUid(firebaseUid).orElse(null);
    }

    @Override
    public Vendor findVendorByUser(User user) {
        return vendorRepository.findByUser(user).orElseThrow(() -> new RuntimeException(ErrorMessage.VENDOR_NOT_FOUND));
    }

    @Override
    public User update(User user) {
        user.setFirebaseUid(null);
        return userRepository.save(user);
    }

    @Override
    public Vendor update(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @Override
    public Address update(Address address) {
        addressRepository.findByIdAndUser(address.getId(), address.getUser()).ifPresentOrElse(
                a -> addressRepository.save(address),
                () -> {
                    address.setId(null);
                    addressRepository.save(address);
                });
        return address;
    }

    @Override
    public List<Address> getAddress(User user) {
        return addressRepository.findByUser(user);
    }

    @Override
    public Address deleteAddress(Address address) {
        addressRepository.findByIdAndUser(address.getId(), address.getUser()).ifPresentOrElse(
                a -> {
                    a.setUser(null);
                    addressRepository.save(a);
                },
                () -> {
                    throw new RuntimeException(ErrorMessage.ADDRESS_NOT_FOUND);
                });
        return address;
    }

    @Override
    public Page<Vendor> findVendors(VendorSpecification vendorSpecification, Pageable pageable) {
        return vendorRepository.findAll(vendorSpecification, pageable);
    }
}
