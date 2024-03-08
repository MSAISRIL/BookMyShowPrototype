package com.bookshow.BookMyShowPrototype.controller;

import com.bookshow.BookMyShowPrototype.models.ShowTimings;
import com.bookshow.BookMyShowPrototype.models.TheatreDetails;
import com.bookshow.BookMyShowPrototype.service.IUserService;
import com.bookshow.BookMyShowPrototype.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/theatre")
public class TheatreController {

    private final IUserService userService;
    private final TheatreService theatreService;
    @Autowired
    public TheatreController(IUserService userService, TheatreService theatreService) {
        this.userService = userService;
        this.theatreService = theatreService;
    }
    @PostMapping("/addTheatre")
    public ResponseEntity<?> addTheatreDetails(@RequestBody TheatreDetails theatreDetails, @RequestParam String loginToken){
        if(userService.verifyToken(loginToken)){
            return theatreService.addTheatreDetails(theatreDetails);
        }
        return new ResponseEntity<>("User logged out please login again", HttpStatus.EXPECTATION_FAILED);
    }
    @GetMapping("/getShows")
    public ResponseEntity<?> getMovies(@RequestParam String loginToken, @RequestParam String theatreName){
        if(userService.verifyToken(loginToken)){
            System.out.println("Token Authentication Successful");
            return theatreService.getAllShows(theatreName);
        }
        return new ResponseEntity<>("User logged out please login again", HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping("/getMovieShows")
    public ResponseEntity<?> getMovieShows(@RequestParam String loginToken, @RequestParam String movieName){
        if(userService.verifyToken(loginToken)){
            return theatreService.getMovieShows(movieName);
        }
        return new ResponseEntity<>("User logged out please login again", HttpStatus.EXPECTATION_FAILED);
    }
    @PostMapping("/addMovieShows")
    public ResponseEntity<?> addShowsToTheatre(@RequestBody ShowTimings showTimings, @RequestParam String loginToken){
        if(userService.verifyToken(loginToken)){
            return theatreService.addShows(showTimings);
        }
        return new ResponseEntity<>("User logged out please login again", HttpStatus.EXPECTATION_FAILED);
    }

}
