package com.bookshow.BookMyShowPrototype.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "movie_details")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long movieId;
    @NotEmpty(message = "Movie Name is Required")
    @Column(unique = true)
    private String movieName;
    @NotEmpty(message = "Movie Director is Required")

    private String movieDirector;
    @NotEmpty(message = "Movie Length is Required")

    private String movieLength;
    @NotEmpty(message = " Male lead role Details is Required")
    private String leadRoleMale;
    @NotEmpty(message = "Female lead role is Required")
    private String leadRoleFeMale;

}
