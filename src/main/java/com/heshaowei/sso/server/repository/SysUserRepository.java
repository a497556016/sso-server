package com.heshaowei.sso.server.repository;

import com.heshaowei.sso.server.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SysUserRepository extends JpaRepository<SysUser, Integer> {
    Optional<SysUser> findByUsername(String username);
}
