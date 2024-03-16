package com.patika.kredinbizdenservice.model;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

public class UserFactory {

    private UserManager userManager;
    public UserFactory(UserManager userManager){
        this.userManager= userManager;
    }
    public boolean createUser(String name, String surname, LocalDate birthDate, String email, String password, String phoneNumber, Boolean isActive) throws NoSuchAlgorithmException {
        User newUser = new User(name, surname, birthDate, email, password, phoneNumber, isActive);

        return userManager.addUser(newUser);

    }

    public boolean createUser(String name, String surname, String email, String password, String phoneNumber, Boolean isActive) throws NoSuchAlgorithmException {
        User newUser = new User(name, surname, email, password, phoneNumber, isActive);

        return userManager.addUser(newUser);
    }

}
