package com.heshaowei.sso.server.repository;

import com.heshaowei.sso.server.entity.SysUserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SysUserPermissionRepository extends JpaRepository<SysUserPermission, Integer> {
    Optional<List<SysUserPermission>> findAllByUsername(String username);

    void deleteAllByUsername(String username);
}
