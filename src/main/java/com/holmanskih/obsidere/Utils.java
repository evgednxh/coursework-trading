package com.holmanskih.obsidere;

import com.holmanskih.obsidere.controllers.UserController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Utils {
    public static String getAccessTokenCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null || cookies.length == 0) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if(cookie.getName().equals(UserController.ACCESS_TOKEN_COOKIE_NAME)) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
