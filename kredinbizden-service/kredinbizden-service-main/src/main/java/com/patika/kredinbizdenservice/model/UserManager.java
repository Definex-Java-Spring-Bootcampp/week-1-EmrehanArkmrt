package com.patika.kredinbizdenservice.model;


import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserManager{
    private List<User> users = new ArrayList<>();

    public boolean addUser(User user) throws NoSuchAlgorithmException {
        String hashedPassword = PasswordUtils.hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        Optional<User> existingUser =users.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(user.getEmail()))
                .findAny();

        if(existingUser.isPresent()){

            return false;
        }
        users.add(user);
        return true;

    }

    public Optional<User> findUserWithMostApplications() { //En çok başvuru yapan kullanıcıyı bulan methodu yazın.
        return users.stream()
                .max((u1, u2) -> Integer.compare(u1.getApplicationList().size(), u2.getApplicationList().size()));
    }



}