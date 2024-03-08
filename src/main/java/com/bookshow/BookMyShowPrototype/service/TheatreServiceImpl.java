package com.bookshow.BookMyShowPrototype.service;

import com.bookshow.BookMyShowPrototype.dto.ShowTimesDto;
import com.bookshow.BookMyShowPrototype.dto.ShowsDto;
import com.bookshow.BookMyShowPrototype.models.ShowTimings;
import com.bookshow.BookMyShowPrototype.models.TheatreDetails;
import com.bookshow.BookMyShowPrototype.repository.ShowTimingsRepository;
import com.bookshow.BookMyShowPrototype.repository.TheatreRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TheatreServiceImpl implements TheatreService{

    private final TheatreRepository theatreRepository;


    private final ShowTimingsRepository showTimingsRepository;
    @Autowired
    public TheatreServiceImpl(TheatreRepository theatreRepository, ShowTimingsRepository showTimingsRepository) {
        this.theatreRepository = theatreRepository;
        this.showTimingsRepository = showTimingsRepository;
    }

    @Override
    public ResponseEntity<?> getAllShows(String theatreName) {
        Optional<TheatreDetails> theatreDetails= theatreRepository.findByTheatreName(theatreName);
        ShowTimesDto dto = new ShowTimesDto();
        if(theatreDetails.isPresent()) {
            dto.setTheatreDetails(theatreDetails.get());
            List<ShowsDto> shows= new ArrayList<>();
            for(ShowTimings st:showTimingsRepository.getAllShowsInTheatre(theatreDetails.get().getTheatreCode())){
                ShowsDto sdto = new ShowsDto();
                sdto.setMovieName(st.getMovieName());
                sdto.setShowTimes(st.getShowTimes());
                shows.add(sdto);
            }
            dto.setShows(shows);

            return new ResponseEntity<>(dto , HttpStatus.OK);
        }

            return new ResponseEntity<>("Theatre with name: "+theatreName+" does not exist", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getMovieShows(String movieName) {

        Optional<ShowTimings> shows=showTimingsRepository.findByMovieName(movieName);
        if(shows.isPresent()){
            ShowsDto dto = new ShowsDto();
            dto.setMovieName(movieName);
            dto.setShowTimes(shows.get().getShowTimes());
           return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return  new ResponseEntity<>("Shows with Movie name: "+movieName+" does not exist",
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addShows(ShowTimings showTimings) {

        return new ResponseEntity<>(showTimingsRepository.save(showTimings), HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<?> addTheatreDetails(TheatreDetails theatreDetails) {
        Optional<TheatreDetails> theatre=theatreRepository.findByTheatreName(theatreDetails.getTheatreName());
        if(!theatre.isPresent()){
            return new ResponseEntity<>(theatreRepository.save(theatreDetails), HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Theatre with name: "+theatreDetails.getTheatreName()+" already exist",
                HttpStatus.OK);
    }
}
