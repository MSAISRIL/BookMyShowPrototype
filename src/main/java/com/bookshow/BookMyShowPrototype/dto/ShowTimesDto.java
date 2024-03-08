package com.bookshow.BookMyShowPrototype.dto;

import com.bookshow.BookMyShowPrototype.models.TheatreDetails;
import lombok.Data;
import java.util.*;

@Data
public class ShowTimesDto {

    private TheatreDetails theatreDetails;

    List<ShowsDto> shows;
}
