package com.test.userLoginTest;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tianfusheng
 * @date 2018/9/8
 */
@Service
public class UserService {
    private static Map<String, User> users = new HashMap<String, User>();

    static {
        users.put("zhangSan", new User("zhangSan", "123", 1));
        users.put("liSi", new User("liSi", "123", 2));
    }

    public User login(String username, String password) {
        User user = users.get(username);
        if (user == null) return null;
        return user.getPassword().equals(password) ? user : null;
    }
}