package com.scu.fagaiju.common.domain;

import java.util.ArrayList;
import java.util.List;

public class Role {
    private Integer id;

    private String role;

    private String displayName;

    //
    private List<Permission> permissions = new ArrayList<>();

    //
    private String authorities;

    private List<Integer> permissionIds;


    public Role() {
    }

    public Role(String role, List<Integer> permissionIds) {
        this.role = role;
        this.permissionIds = permissionIds;
    }

    public List<Integer> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<Integer> permissionIds) {
        this.permissionIds = permissionIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public Role setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
        return this;
    }

    public String getAuthorities() {
        return authorities;
    }

    public Role setAuthorities(String authorities) {
        this.authorities = authorities;
        return this;
    }
}