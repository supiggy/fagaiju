package com.scu.fagaiju.controller;

import com.github.pagehelper.PageInfo;
import com.scu.fagaiju.common.domain.Role;
import com.scu.fagaiju.common.utils.Result;
import com.scu.fagaiju.service.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class RoleController {
    @Autowired
    private RoleServiceImpl roleService;

    @GetMapping("/role")
    @ResponseBody
    public Object listRoles(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit) {
        PageInfo<Role> pageInfo = roleService.findRoles(page, limit);
        return Result.success(pageInfo);
    }


    @DeleteMapping("/role")
    @ResponseBody
    public Object deleteRoles(@RequestBody Integer[] ids){
        int i = roleService.removeRoleById(ids);
        return i >= 1 ? Result.success() : Result.fail();
    }

    @DeleteMapping("/role/{id}")
    public Object deleteRoleById(@PathVariable("id") int id){
        int i = roleService.removeRoleById(id);
        return i >= 1 ? Result.success() : Result.fail();
    }

    @PostMapping("/saverole")
    @ResponseBody
    public Result.JSONResultMap  saveRole(HttpServletRequest request){
        List<String> stringList = Arrays.asList(request.getParameterValues("permissionIds"));
        List<Integer> integerList = new ArrayList<>();
        for (String s : stringList) {
            integerList.add(Integer.parseInt(s));
        }
        String role = request.getParameter("role");

        Role role1 = new Role(role,integerList);

        roleService.saveRole(role1);
        return Result.success();
    }

    @GetMapping("/addrole")
    public String addPage(Model model) {
        model.addAttribute("role", new Role());
        return "views/role-add";
    }


    @PutMapping("/editRole")
    public Object updateRole(@RequestBody Role role){
        int i = roleService.modifyRole(role);
        return i >= 1 ? Result.success() : Result.fail();
    }
}
