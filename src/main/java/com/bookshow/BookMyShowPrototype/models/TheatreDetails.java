package com.bookshow.BookMyShowPrototype.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class TheatreDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name="theatre_code")
    private Long theatreCode;
    @NotEmpty(message = "Theatre Name is Required")
    private String theatreName;
    @NotEmpty(message = "Theatre Address is Required")
    private String theatreAddress;

//    @OneToMany(fetch = FetchType.LAZY)
//   @JoinTable( name = "shows", joinColumns = @JoinColumn ( name = "theatre_code"), inverseJoinColumns = @JoinColumn( name = "show_id"))
//    @OneToMany(mappedBy = "theatreDetails", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @ElementCollection
//    private List<ShowTimings> shows = new ArrayList<>();


}
