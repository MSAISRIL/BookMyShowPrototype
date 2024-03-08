package com.bookshow.BookMyShowPrototype.models;

import com.bookshow.BookMyShowPrototype.converters.StringListConverter;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ShowTimings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "show_id")
    private Long showId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "theatre_code")
    private TheatreDetails theatreDetails;

    private String movieName;

    @Convert(converter = StringListConverter.class)
    private List<String> showTimes = new ArrayList<>();
}
