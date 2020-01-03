package com.scu.fagaiju.controller;

import com.github.pagehelper.PageInfo;
import com.scu.fagaiju.common.domain.User;
import com.scu.fagaiju.common.utils.Result;
import com.scu.fagaiju.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public Object login(User user){
//        Admin adminDB = adminServie.findAdminByLoginName(admin);
//        if(adminDB==null){
//            return Result.fail("用户名不存在");
//        }else{
//            if(adminDB.getPassword().equals(admin.getPassword())){
//                adminDB.setLastlogintime(new Date());
//                adminServie.modifyAdmin(adminDB);
//                return Result.success(adminDB,"登录成功");
//            }else{
//                return Result.fail("密码错误");
//            }
//        }
        User userDB = userService.findUserByLoginName(user);
        if(userDB==null){
            return  Result.fail();
        }else {
            if (userDB.getPassword().equals(user.getPassword())){
                return Result.success(userDB,"登录成功");
            }else {
                return Result.fail("密码错误");
            }
        }
    }


    @GetMapping("/user")
    @ResponseBody
    public Object listUsers(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        PageInfo<User> list = userService.findUsers(page, limit);
        return Result.success(list);
    }

    @DeleteMapping("/user/{id}")
    @ResponseBody
    public Object deleteUser(@PathVariable("id") Integer id) {
        userService.removeUserById(id);
        return Result.success();
    }

    @GetMapping("editUser")
    public String editPage(@RequestParam("id") Integer id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);

        return "views/user-edit";
    }

    @GetMapping("addUser")
    public String addPage(Model model) {
        model.addAttribute("user", new User());

        return "views/user-edit";
    }


    @PostMapping("save")
    @ResponseBody
    public Result.JSONResultMap save(User user) {
        userService.save(user);
        return Result.success();
    }


    //删除多个
    @DeleteMapping("/user")
    @ResponseBody
    public Object deleteUsers(@RequestBody Integer[] ids){
        int i = userService.removeUserById(ids);
        return i >= 1 ? Result.success() : Result.fail();
    }
}
