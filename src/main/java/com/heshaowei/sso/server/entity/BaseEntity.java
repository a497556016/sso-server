package com.heshaowei.sso.server.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
}
