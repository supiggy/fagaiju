package com.scu.fagaiju.service;

import com.github.pagehelper.PageInfo;
import com.scu.fagaiju.common.domain.Role;

import java.io.Serializable;
import java.util.List;

public interface RoleService extends Serializable {

    //增删查改
    public int addRole(Role role);
    public int removeRoleById(Integer... id);
    public int modifyRole(Role role);


    //查
    public List<Role> findRoleByExample();
    public Role findRoleById(Role role);

    //分页
    PageInfo<Role> findRoles(int page, int limit);

    void saveRole(Role role);
}
