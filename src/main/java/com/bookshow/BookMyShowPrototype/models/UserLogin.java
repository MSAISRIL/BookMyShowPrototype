package com.bookshow.BookMyShowPrototype.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin {

    private String emailId;
    private String password;
}
