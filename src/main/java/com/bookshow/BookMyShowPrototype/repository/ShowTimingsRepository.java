package com.bookshow.BookMyShowPrototype.repository;

import com.bookshow.BookMyShowPrototype.models.ShowTimings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ShowTimingsRepository extends JpaRepository<ShowTimings, Long> {
    Optional<ShowTimings> findByMovieName(String movieName);

    @Query(value = "select * from show_timings s where s.theatre_code=?1", nativeQuery = true)
    List<ShowTimings> getAllShowsInTheatre(Long theaterName);
}
