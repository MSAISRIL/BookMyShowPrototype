package com.bookshow.BookMyShowPrototype.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginStatus {
    @Id
    @NotEmpty(message = "Email ID is Required")
    @Column(unique = true)
    private String emailId;

    private String loginToken;
}
