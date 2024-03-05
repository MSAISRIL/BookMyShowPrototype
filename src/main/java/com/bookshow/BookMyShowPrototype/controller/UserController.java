package com.bookshow.BookMyShowPrototype.controller;

import com.bookshow.BookMyShowPrototype.models.BookMyShowUser;
import com.bookshow.BookMyShowPrototype.models.UserLogin;
import com.bookshow.BookMyShowPrototype.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody BookMyShowUser user){
        return userService.addUser(user);
    }
    @GetMapping("/viewAllUsers")
    public ResponseEntity<?> getAll(){
        return userService.viewAllUsers();

    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLogin userLogin){

        return  userService.loginUser(userLogin);

    }
}
