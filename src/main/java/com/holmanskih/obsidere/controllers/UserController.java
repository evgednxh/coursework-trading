package com.holmanskih.obsidere.controllers;

import com.holmanskih.obsidere.Utils;
import com.holmanskih.obsidere.model.User;
import com.holmanskih.obsidere.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {
    public static final String ACCESS_TOKEN_COOKIE_NAME = "sid";

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    @Autowired
    public void setAuthService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/auth")
    public String middleware(Model model) {
        model.addAttribute("user", new User());
        return "auth";
    }

    @GetMapping("/")
    public RedirectView getTemplate(HttpServletRequest request, HttpServletResponse response) {
        String accessToken = Utils.getAccessTokenCookie(request);
        if(accessToken != null) {
            User user = userService.getUserByToken(accessToken);
            if(user == null) {
                Cookie cookie = new Cookie(ACCESS_TOKEN_COOKIE_NAME, null);
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                return new RedirectView("auth");
            }
            return new RedirectView("home");
        }
        return new RedirectView("auth");
    }

    @PostMapping("/signIn")
    public RedirectView signIn(@ModelAttribute("user") User user, HttpServletResponse response, BindingResult result) {
        if(result.hasErrors()) {
            logger.error("sign in err", result.getAllErrors());
            return new RedirectView("error");
        }
        String accessToken = userService.authorize(user);
        Cookie cookie = new Cookie(ACCESS_TOKEN_COOKIE_NAME, accessToken);
        response.addCookie(cookie);
        return new RedirectView("home");
    }

    @GetMapping("/signOut")
    public RedirectView signIn(@ModelAttribute("user") User user, HttpServletResponse response) {
        Cookie cookie = new Cookie(ACCESS_TOKEN_COOKIE_NAME, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return new RedirectView("index");
    }
}
