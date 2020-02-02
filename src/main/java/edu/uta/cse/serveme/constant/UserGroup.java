package edu.uta.cse.serveme.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author housirvip
 */
@Getter
@AllArgsConstructor
public enum UserGroup {

    /**
     * Limited
     */
    Limited("limited"),
    /**
     * User
     */
    User("user"),
    /**
     * Vip
     */
    Vip("vip"),
    /**
     * Admin
     */
    Admin("admin"),
    /**
     * Root
     */
    Root("root");

    private String value;
}
