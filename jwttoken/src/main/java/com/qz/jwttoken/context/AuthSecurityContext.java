package com.qz.jwttoken.context;

import com.qz.jwttoken.entity.Users;

public class AuthSecurityContext {
    public static ThreadLocal<Users> UserContext = new ThreadLocal<>();

    public static Users getUserContext() {
        return UserContext.get();
    }

    public static void setUserContext(Users user) {
        UserContext.set(user);
    }
}
