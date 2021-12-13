package com.holmanskih.obsidere.controllers;

import com.holmanskih.obsidere.Utils;
import com.holmanskih.obsidere.model.PaymentInfo;
import com.holmanskih.obsidere.model.SoldStock;
import com.holmanskih.obsidere.model.Stock;
import com.holmanskih.obsidere.model.User;
import com.holmanskih.obsidere.services.UserService;
import com.holmanskih.obsidere.services.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class StockController {
    private static final Logger logger = LoggerFactory.getLogger(StockController.class);

    private final UserService userService;
    private final StockService stockService;

    public StockController(UserService userService, StockService stockService) {
        this.userService = userService;
        this.stockService = stockService;
    }

    @GetMapping("/home")
    public String getTemplate(HttpServletRequest request, Model model) {
        String accessToken = Utils.getAccessTokenCookie(request);
        if(accessToken == null) {
            return "redirect:/";
        }
        User user = userService.getUserByToken(accessToken);
        if(user == null) {
            return "redirect:/";
        }

        List<Stock> availableStocks = stockService.getAvailableStocks();

        model.addAttribute("userType", user.getUserType());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("stockList", availableStocks);
        model.addAttribute("buyStock", new Stock());
        return "home";
    }
    
    @PostMapping("/stock/buy")
    public String buyStock(@RequestBody Stock stock, HttpServletRequest request, BindingResult result) {
        logger.info("buying new stock " + stock);

        if(result.hasErrors()) {
            logger.error("buy stock err", result.getAllErrors());
            return "error"; // TODO: add error page
        }

        // get user by cookie id
        String accessToken = Utils.getAccessTokenCookie(request);
        if(accessToken == null) {
            return "redirect:/";
        }
        User user = userService.getUserByToken(accessToken);
        if(user == null) {
            return "redirect:/";
        }

        // here must be a db transaction !
        try {

            // check money amount
            float price = stock.getAmount() * stock.getTradePrice();
            PaymentInfo info = user.getPayment();
            if (info == null) {
                return "redirect:/errorPayment";
            }

            float balance = info.getBalance();
            if(price > balance) {
                return "redirect:/errorPayment";
            }

            // save bought stocks to user account
            User seller = userService.getDataById(stock.getSeller().getId());
            SoldStock soldStock = new SoldStock(stock, user, seller);
            stockService.buyStocks(soldStock);

            // reduce money amount
            // get payment id by user id
//        int paymentID = userService.getDataById(user.getId());

            // update payment balance by received id
            userService.updatePaymentBalance(user.getPayment().getId(), (int) (balance - price));
        } catch(Exception e) {
            logger.error("buying new stock err", e);
            return "redirect:/errorPayment";
        }

        logger.debug("buying new stock");
        return "home";
    }

    @GetMapping("/errorPayment")
    public String createStock() {
        return "error_payment";
    }

    @PostMapping("/stock/new")
    public String createStock(@ModelAttribute("stock") Stock stock, HttpServletRequest request, BindingResult result) {
        logger.info("creating new stock " + stock);

        if(result.hasErrors()) {
            logger.error("create stock err", result.getAllErrors());
            return "error";
        }

        // get user by cookie id
        String accessToken = Utils.getAccessTokenCookie(request);
        if(accessToken == null) {
            return "redirect:/";
        }
        User user = userService.getUserByToken(accessToken);
        if(user == null) {
            return "redirect:/";
        }

        stock.setSeller(user);
        stockService.create(stock);

        return "redirect:/profile";
    }
}
