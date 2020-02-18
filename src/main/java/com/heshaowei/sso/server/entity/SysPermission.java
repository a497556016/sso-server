package com.heshaowei.sso.server.entity;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class SysPermission extends BaseEntity {
    private String name;
    private String remark;
}
