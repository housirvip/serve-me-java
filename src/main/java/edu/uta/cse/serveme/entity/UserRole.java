package edu.uta.cse.serveme.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author housirvip
 */
@Getter
@AllArgsConstructor
public enum UserRole {

    /**
     * UserRole
     */
    ROLE_ADMIN,
    ROLE_ROOT,
    ROLE_USER,
    ROLE_CUSTOMER,
    ROLE_VENDOR
}
