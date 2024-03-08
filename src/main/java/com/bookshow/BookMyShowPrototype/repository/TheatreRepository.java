package com.bookshow.BookMyShowPrototype.repository;

import com.bookshow.BookMyShowPrototype.models.ShowTimings;
import com.bookshow.BookMyShowPrototype.models.TheatreDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;
public interface TheatreRepository extends JpaRepository<TheatreDetails, Long> {

    Optional<TheatreDetails> findByTheatreName(String theatreName);


}
