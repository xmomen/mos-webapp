package com.xmomen.mos.module.account.service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.mos.module.account.entity.SysUsers;
import com.xmomen.mos.module.account.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {


    private PasswordHelper passwordHelper;

    public void setPasswordHelper(PasswordHelper passwordHelper) {
        this.passwordHelper = passwordHelper;
    }

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 创建用户
     * @param user
     */
    @Transactional
    public SysUsers createUser(User user) {
        //加密密码
        String salt = passwordHelper.getSalt();
        String newPassword = passwordHelper.encryptPassword(user.getPassword(), user.getUsername(), salt);
        SysUsers sysUsers = new SysUsers();
        sysUsers.setSalt(UUID.randomUUID().toString().toUpperCase());
        sysUsers.setUsername(user.getUsername());
        sysUsers.setSalt(salt);
        sysUsers.setPassword(newPassword);
        sysUsers.setLocked(user.getLocked() ? 1 : 0);
        return mybatisDao.saveByModel(sysUsers);
    }

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    @Transactional
    public void changePassword(Long userId, String newPassword) {
        SysUsers user = mybatisDao.selectByPrimaryKey(SysUsers.class, userId);
        String salt = passwordHelper.getSalt();
        user.setPassword(newPassword);
        user.setSalt(salt);
        passwordHelper.encryptPassword(user.getPassword(), user.getUsername(), salt);
        mybatisDao.update(user);
    }

    /**
     * 添加用户-角色关系
     * @param userId
     * @param roleIds
     */
    public void correlationRoles(Long userId, Long... roleIds) {
    }


    /**
     * 移除用户-角色关系
     * @param userId
     * @param roleIds
     */
    public void uncorrelationRoles(Long userId, Long... roleIds) {
    }

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public SysUsers findByUsername(String username) {
        SysUsers sysUsers = new SysUsers();
        sysUsers.setUsername(username);
        return mybatisDao.selectOneByModel(sysUsers);
    }

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username) {
        return null;
    }

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username) {
        return null;
    }

}
