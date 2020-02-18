package com.heshaowei.sso.server.service;

import org.springframework.security.core.GrantedAuthority;

public class MyAuthority implements GrantedAuthority {
    private String authority;
    public MyAuthority(String authority) {
        this.authority = authority;
    }
    @Override
    public String getAuthority() {
        return authority;
    }
}
