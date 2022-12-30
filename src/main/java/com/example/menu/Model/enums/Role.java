package com.example.menu.Model.enums;

import org.springframework.security.core.GrantedAuthority;

import java.util.*;

public enum Role implements GrantedAuthority {
    ROLE_USER,ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
    public static List<Role> getRoles(){
        ArrayList roles = new ArrayList<>();
        roles.add(ROLE_USER);
        roles.add(ROLE_ADMIN);
        return roles;
    }
}
