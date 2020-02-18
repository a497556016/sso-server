package com.heshaowei.sso.server.repository;

import com.heshaowei.sso.server.entity.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysPermissionRepository extends JpaRepository<SysPermission, Integer> {
}
