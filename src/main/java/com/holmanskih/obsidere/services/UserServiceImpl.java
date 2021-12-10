package com.holmanskih.obsidere.services;

import com.holmanskih.obsidere.model.UserType;
import com.holmanskih.obsidere.model.PaymentInfo;
import com.holmanskih.obsidere.model.User;
import com.holmanskih.obsidere.repository.PaymentInfoRepository;
import com.holmanskih.obsidere.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PaymentInfoRepository paymentInfoRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository, PaymentInfoRepository paymentInfoRepository) {
        this.userRepository = userRepository;
        this.paymentInfoRepository = paymentInfoRepository;
    }

    @Override
    @Transactional
    public String authorize(User user) {
        // todo: check if such user already exists
        String token = this.generateRandomHash();
        user.setAccessToken(token);
        userRepository.add(user);
        return token;
    }

    @Override
    public String signOut(String token) {
        // update user - remove access token from db
        // redirect to sign in page
        return null;
    }

    @Override
    public User getUserData() {
        // TODO: add logic
        return new User("testuser", "test@gmail.com", "qwerty", UserType.Investor);
    }

    @Override
    @Transactional
    public User getUserByToken(String token) {
        User user = userRepository.getByToken(token);
        return user;
    }

    @Override
    @Transactional
    public User getDataById(int userID) {
        User user = userRepository.getByID(userID);
        return user;
    }

    @Override
    @Transactional
    public void addPaymentInfo(int userID, PaymentInfo paymentInfo) {
        int id = paymentInfoRepository.add(paymentInfo);
        paymentInfo.setId(id);
        userRepository.updateUserPaymentInfo(userID, paymentInfo);
    }

    @Override
    @Transactional
    public void updatePaymentBalance(int paymentID, int balance) {
        paymentInfoRepository.updateBalance(paymentID, balance);
    }

    private String generateRandomHash() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }
}
