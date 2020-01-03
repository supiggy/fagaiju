package com.scu.fagaiju.mapper.extend;

import com.scu.fagaiju.common.domain.Role;
import com.scu.fagaiju.mapper.RoleMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapperExtend extends RoleMapper {
    List<Role> selectRoleWithPermissions();
}