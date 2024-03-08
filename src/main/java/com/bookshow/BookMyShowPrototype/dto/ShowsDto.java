package com.bookshow.BookMyShowPrototype.dto;

import com.bookshow.BookMyShowPrototype.converters.StringListConverter;
import lombok.Data;

import javax.persistence.Convert;
import java.util.ArrayList;
import java.util.List;
@Data
public class ShowsDto {

    private String movieName;

    private List<String> showTimes;
}
