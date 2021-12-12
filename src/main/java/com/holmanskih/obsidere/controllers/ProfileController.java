package com.holmanskih.obsidere.controllers;

import com.holmanskih.obsidere.Utils;
import com.holmanskih.obsidere.model.SoldStock;
import com.holmanskih.obsidere.model.UserType;
import com.holmanskih.obsidere.model.PaymentInfo;
import com.holmanskih.obsidere.model.User;
import com.holmanskih.obsidere.services.StockService;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfileController {
    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);
    private UserService userService;
    private StockService stockService;

    @Autowired
    public void setAuthService(UserService userService, StockService stockService) {
        this.userService = userService;
        this.stockService = stockService;
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

        if (user.getUserType() == UserType.Investor) {
            List<SoldStock> stockList = stockService.getInvestorStocks(user.getId());
            model.addAttribute("stocksInfo", stockList);
            return "investor_profile";
        } else {
            // Todo: change the information in the sql table about sold amount of stocks
            List<SoldStock> stockList = stockService.getBusinessStocks(user.getId());
            model.addAttribute("stocksInfo", stockList);
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
