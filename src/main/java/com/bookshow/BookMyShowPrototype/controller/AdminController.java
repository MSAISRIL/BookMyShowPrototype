package com.bookshow.BookMyShowPrototype.controller;

import com.bookshow.BookMyShowPrototype.models.Movie;
import com.bookshow.BookMyShowPrototype.models.UserLogin;
import com.bookshow.BookMyShowPrototype.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adminpage")
public class AdminController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/adminLogin")
    public ResponseEntity<?> adminLogin(@RequestBody UserLogin userLogin){
        if(userService.isAdmin(userLogin))
            return  userService.loginUser(userLogin);
        return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
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

    @DeleteMapping("/deleteMovie")
    public ResponseEntity<?> deleteMovie(@RequestParam Long movieId, @RequestParam String loginToken){
        if(userService.verifyToken(loginToken))
            return userService.deleteMovie(movieId);
        return new ResponseEntity<>("User logged out please login again", HttpStatus.EXPECTATION_FAILED);
    }

}
