package com.holmanskih.obsidere.controllers;

import com.holmanskih.obsidere.Utils;
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

        // check money amount
        float price = stock.getAmount() * stock.getTradePrice();
        float balance = user.getPayment().getBalance();
        if(price > balance) {
            // TODO: show error message of low balance
            return "";
        }

        // get seller by stock id

        // save bought stocks to user account
        User seller = userService.getDataById(stock.getSeller().getId());
        SoldStock soldStock = new SoldStock(stock, user, seller);
        stockService.buyStocks(soldStock);

        // reduce money amount
        // get payment id by user id
//        int paymentID = userService.getDataById(user.getId());

        // update payment balance by received id
        userService.updatePaymentBalance(user.getPayment().getId(), (int) (balance - price));

        System.out.println(stock.getAmount());
        System.out.println(stock.getMarketName());
        System.out.println(stock.getTradeName());
        logger.debug("buying new stock");
        return "";
    }
}
