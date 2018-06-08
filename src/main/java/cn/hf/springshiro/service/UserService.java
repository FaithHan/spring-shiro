package cn.hf.springshiro.service;

import cn.hf.springshiro.entity.User;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 用户service
 *
 * @author : FaithHan
 * @since : 2018/6/8
 */
@Service
public class UserService {

    /**
     * 用户数据存储
     */
    private static final Map<String, User> USERS = new LinkedHashMap<>();

    static {
        USERS.put("admin", new User(1, "admin", "admin"));
        USERS.put("zhangsan", new User(2, "zhangsan", "zhangsan"));
        USERS.put("lisi", new User(3, "lisi", "lisi"));
        USERS.put("wangwu", new User(4, "wangwu", "wangwu"));
    }


    /**
     * 返回用户列表
     *
     * @return
     */
    public List<User> list() {
        return Collections.unmodifiableList(new ArrayList<>(USERS.values()));
    }

    /**
     * 根据用户名返回用户
     *
     * @param username
     * @return
     */
    public User getUserByName(String username) {
        return USERS.get(username);
    }

    /**
     * 根据主键返回用户
     *
     * @param id
     * @return
     */
    public User getById(Integer id) {
        return new ArrayList<>(USERS.values()).get(id - 1);
    }

}
