package com.bookshow.BookMyShowPrototype.service;

import com.bookshow.BookMyShowPrototype.models.ShowTimings;
import com.bookshow.BookMyShowPrototype.models.TheatreDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public interface TheatreService {
    ResponseEntity<?> getAllShows(String theatreName);
    ResponseEntity<?> getMovieShows(String movieName);

    ResponseEntity<?> addShows(ShowTimings showTimings);

    ResponseEntity<?> addTheatreDetails(TheatreDetails theatreDetails);
}
