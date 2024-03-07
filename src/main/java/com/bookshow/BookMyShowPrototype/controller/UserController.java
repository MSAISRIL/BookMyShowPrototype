package com.bookshow.BookMyShowPrototype.controller;

import com.bookshow.BookMyShowPrototype.models.BookMyShowUser;
import com.bookshow.BookMyShowPrototype.models.Movie;
import com.bookshow.BookMyShowPrototype.models.UserLogin;
import com.bookshow.BookMyShowPrototype.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/addMovie")
    public ResponseEntity<?> addMovie(@RequestBody Movie movie, @RequestParam String loginToken){

        if(userService.verifyToken(loginToken))
            return userService.addMovie(movie);
        return new ResponseEntity<>("User logged out please login again", HttpStatus.EXPECTATION_FAILED);
    }
    @GetMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam String loginToken){
        return userService.logOut(loginToken);
    }

}
