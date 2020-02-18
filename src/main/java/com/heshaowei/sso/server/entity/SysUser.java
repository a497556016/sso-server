package com.heshaowei.sso.server.entity;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class SysUser extends BaseEntity {
   private String username;
   private String password;
   private String phone;
}
