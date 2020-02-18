package com.heshaowei.sso.server.controller;

import com.heshaowei.sso.server.entity.SysUser;
import com.heshaowei.sso.server.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @Autowired
    private JwtTokenStore tokenStore;
    @Autowired
    private SysUserRepository userRepository;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/error")
    public String error(){
        return "error";
    }

    @GetMapping("/user")
    public ResponseEntity<Object> user(String token){
        OAuth2Authentication authentication = tokenStore.readAuthentication(token);
        SysUser user = userRepository.findByUsername(authentication.getPrincipal().toString()).orElse(new SysUser());
        user.setPassword(null);
        return ResponseEntity.ok(user);
    }
}
