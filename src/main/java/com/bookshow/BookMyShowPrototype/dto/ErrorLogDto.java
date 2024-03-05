package com.bookshow.BookMyShowPrototype.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
@Component
@ToString
@Data
public class ErrorLogDto {

    ArrayList<String> errorLogs;

   ErrorLogDto(){
       this.errorLogs=new ArrayList<>();
   }

    public void addErrorLog(String log){
        errorLogs.add(log);
        this.setErrorLogs(errorLogs);
    }
}
