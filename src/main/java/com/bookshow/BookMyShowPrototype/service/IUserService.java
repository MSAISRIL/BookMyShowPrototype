package com.bookshow.BookMyShowPrototype.service;

import com.bookshow.BookMyShowPrototype.models.BookMyShowUser;
import com.bookshow.BookMyShowPrototype.models.UserLogin;
import io.swagger.models.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface IUserService {
    public ResponseEntity<?> addUser(BookMyShowUser user);
    ResponseEntity<?> viewAllUsers();

    ResponseEntity<?> loginUser(UserLogin userLogin);
}
