package com.holmanskih.obsidere.controllers;

import com.holmanskih.obsidere.Utils;
import com.holmanskih.obsidere.model.UserType;
import com.holmanskih.obsidere.model.PaymentInfo;
import com.holmanskih.obsidere.model.User;
import com.holmanskih.obsidere.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);
    private UserService userService;

    @Autowired
    public void setAuthService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String getTemplate(HttpServletRequest request, Model model) {
        String accessToken = Utils.getAccessTokenCookie(request);
        if(accessToken == null) {
            return "/auth";
        }
        User user = userService.getUserByToken(accessToken);
        if(user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
        if (user.getPayment() == null) {
            model.addAttribute("paymentInfo", new PaymentInfo());
        }

        if (user.getAccountType() == UserType.Investor) {
            return "investor_profile";
        } else {
            return "business_profile";
        }
    }

    @PostMapping("/payment/new")
    public RedirectView signIn(@ModelAttribute("paymentInfo") PaymentInfo paymentInfo, HttpServletRequest request, BindingResult result) {
        if(result.hasErrors()) {
            logger.error("payment err", result.getAllErrors());
            return new RedirectView("error");
        }

        // get user by cookie id
        String accessToken = Utils.getAccessTokenCookie(request);
        if(accessToken == null) {
            return new RedirectView("/");
        }
        User user = userService.getUserByToken(accessToken);
        if(user == null) {
            return new RedirectView("/");
        }

        // add payment information for user account
        paymentInfo.setBalance(10000000);
        userService.addPaymentInfo(user.getId(), paymentInfo);

        return new RedirectView("/profile");
    }
}
