package com.bookshow.BookMyShowPrototype.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class BookMyShowUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email format should be example@mail.com")
    @NotEmpty(message = "Email ID is Required")
    @Column(unique = true)
    private String emailId;
    @Column(unique = true)
    private Long mobile;
    @NotEmpty(message = "First Name is Required")
    private String firstName;
    @NotEmpty(message = "Last Name is Required")
    private String lastName;
    @Column(columnDefinition = "BOOLEAN default false")
    private Boolean isAdmin;
    @NotEmpty(message = "Password is Required")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$", message = "Password should be atleast 6 character and should contain atleast one UpperCase, Lower Case Character and a Number")
    @Size(min = 6)
    private String password;

}
