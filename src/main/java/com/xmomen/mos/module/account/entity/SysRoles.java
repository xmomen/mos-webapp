package com.xmomen.mos.module.account.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "sys_roles")
public class SysRoles extends BaseMybatisModel {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String role;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private Integer available;

    @Column(name = "id")
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        if(id == null){
              removeValidField("id");
              return;
        }
        addValidField("id");
    }

    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
        if(role == null){
              removeValidField("role");
              return;
        }
        addValidField("role");
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        if(description == null){
              removeValidField("description");
              return;
        }
        addValidField("description");
    }

    @Column(name = "available")
    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
        if(available == null){
              removeValidField("available");
              return;
        }
        addValidField("available");
    }
}