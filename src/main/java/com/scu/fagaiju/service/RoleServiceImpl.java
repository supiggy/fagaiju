package com.scu.fagaiju.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scu.fagaiju.common.domain.*;
import com.scu.fagaiju.mapper.RolePermissionMapper;
import com.scu.fagaiju.mapper.extend.RoleMapperExtend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapperExtend roleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public int addRole(Role role) {
        //插入角色表
        int insert = roleMapper.insert(role);

//       插入 角色-权限关联表

        List<Permission> permissions = role.getPermissions();
        RolePermissionKey rolePermissionKey = new RolePermissionKey();
        for (Permission permission : permissions) {
            rolePermissionKey.setRoleId(role.getId());
            rolePermissionKey.setPermissionId(permission.getId());
            rolePermissionMapper.insert(rolePermissionKey);
        }
        return insert;
    }

    @Override
    public int removeRoleById(Integer... id) {
        //       删除 角色-权限关联表
        RolePermissionExample example = new RolePermissionExample();
        example.createCriteria().andRoleIdIn(Arrays.asList(id));
        rolePermissionMapper.deleteByExample(example);
        //        删除角色表
        RoleExample example2 = new RoleExample();
        example2.createCriteria().andIdIn(Arrays.asList(id));
        roleMapper.deleteByExample(example2);

        return 1;
    }

    @Override
    public int modifyRole(Role role) {
        return 0;
    }

    @Override
    public List<Role> findRoleByExample() {
        return null;
    }

    @Override
    public Role findRoleById(Role role) {
        return null;
    }


    @Override
    public PageInfo<Role> findRoles(int page, int limit) {

        PageHelper.clearPage();

        List<Role> roles = roleMapper.selectRoleWithPermissions();

        PageHelper.startPage(page, limit);

        return new PageInfo<>(roles);
    }

    @Override
    public void saveRole(Role role) {
        if (role.getId() == null || "".equals(role.getId())) {
//            添加新角色
            System.out.println(role);

            role.setDisplayName(role.getRole());

            //        插入角色表
            roleMapper.insert(role);

            RoleExample example = new RoleExample();
            example.createCriteria().andRoleEqualTo(role.getRole());
            List<Role> roles = roleMapper.selectByExample(example);
            Integer roleId = roles.get(0).getId();

            //       插入 角色-权限关联表
            List<Integer> permissionIds = role.getPermissionIds();

            RolePermissionKey rolePermissionKey = new RolePermissionKey();
            for (Integer permissionId : permissionIds) {
                rolePermissionKey.setRoleId(roleId);
                rolePermissionKey.setPermissionId(permissionId);
                rolePermissionMapper.insert(rolePermissionKey);
            }


        } else {
//            更新角色信息
            this.modifyRole(role);
        }
    }
}
