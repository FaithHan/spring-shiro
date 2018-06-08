package cn.hf.springshiro.controller;

import cn.hf.springshiro.entity.User;
import cn.hf.springshiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : FaithHan
 * @since : 2018/6/8
 */
@RestController
public class IndexController {

    @Autowired
    private UserService userService;


    /**
     * 登陆
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public String login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            try {
                subject.login(new UsernamePasswordToken(username, password, true));
            } catch (AuthenticationException e) {
                return "false";
            }
        }
        return "true";
    }

    /**
     * 根据id返回一个user,权限测试方法
     *
     * @param id
     * @return
     */
    @RequestMapping("user/{id}")
    @RequiresAuthentication
    public User getUser(@PathVariable("id") Integer id) {
        return userService.getById(id);
    }


}
