package com.heshaowei.sso.server.service;

import com.heshaowei.sso.server.entity.SysUser;
import com.heshaowei.sso.server.entity.SysUserPermission;
import com.heshaowei.sso.server.repository.SysUserPermissionRepository;
import com.heshaowei.sso.server.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private SysUserRepository userRepository;
    @Autowired
    private SysUserPermissionRepository userPermissionRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userRepository.findByUsername(username).orElse(null);
        if(null == sysUser) {
            throw new UsernameNotFoundException("用户名不存在！");
        }
        List<MyAuthority> authorities = new ArrayList<>();
        List<SysUserPermission> userPermissions = userPermissionRepository.findAllByUsername(username).orElse(null);
        if(null != userPermissions) {
            for (SysUserPermission userPermission : userPermissions) {
                authorities.add(new MyAuthority(userPermission.getPermission()));
            }
        }
        User user = new User(sysUser.getUsername(), sysUser.getPassword(), authorities);
        return user;
    }
}
