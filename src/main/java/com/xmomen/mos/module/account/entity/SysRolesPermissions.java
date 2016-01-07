package com.xmomen.mos.module.account.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "sys_roles_permissions")
public class SysRolesPermissions extends BaseMybatisModel {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private Long roleId;

    /**
     * 
     */
    private Long permissionId;

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

    @Column(name = "role_id")
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
        if(roleId == null){
              removeValidField("roleId");
              return;
        }
        addValidField("roleId");
    }

    @Column(name = "permission_id")
    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
        if(permissionId == null){
              removeValidField("permissionId");
              return;
        }
        addValidField("permissionId");
    }
}