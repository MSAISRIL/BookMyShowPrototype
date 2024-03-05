package com.bookshow.BookMyShowPrototype.service;

import com.bookshow.BookMyShowPrototype.dto.ErrorLogDto;
import com.bookshow.BookMyShowPrototype.models.BookMyShowUser;
import com.bookshow.BookMyShowPrototype.models.LoginStatus;
import com.bookshow.BookMyShowPrototype.models.UserLogin;
import com.bookshow.BookMyShowPrototype.repository.LoginRepository;
import com.bookshow.BookMyShowPrototype.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService{
    private final UserRepository userRepository;
    private final ErrorLogDto errorLogDto;

    private final LoginRepository loginRepository;

    @Autowired
    UserServiceImpl(UserRepository userRepository, ErrorLogDto errorLogDto, LoginRepository loginRepository){
        this.userRepository=userRepository;
        this.errorLogDto=errorLogDto;
        this.loginRepository = loginRepository;
    }
    @Override
    public ResponseEntity<?> addUser(BookMyShowUser user) {
        try {
           return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
        } catch (DataIntegrityViolationException ex) {
            String msg = "";
            if (ex.getCause().getCause() instanceof SQLException) {
                SQLException e = (SQLException) ex.getCause().getCause();
                if (e.getMessage().contains("Key")) {
                    msg = e.getMessage().split("Key")[1];
                }
            }
            errorLogDto.addErrorLog(msg);
            return new ResponseEntity<>(errorLogDto, HttpStatus.PRECONDITION_FAILED);
        }
        catch (ConstraintViolationException e){
            errorLogDto.addErrorLog(e.getConstraintViolations().stream()
                    .map(ConstraintViolation::getMessage).collect(Collectors.joining(" ,")));
            return new ResponseEntity<>(errorLogDto, HttpStatus.PRECONDITION_FAILED);
        }
        catch (TransactionSystemException e){
            errorLogDto.addErrorLog(e.getMessage());
            return new ResponseEntity<>(errorLogDto, HttpStatus.PRECONDITION_FAILED);
        }
    }

    @Override
    public ResponseEntity<?> viewAllUsers() {
        try {
            return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            errorLogDto.addErrorLog(e.getMessage());
            return new ResponseEntity<>(errorLogDto, HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<?> loginUser(UserLogin userLogin) {

        Optional<BookMyShowUser> user =userRepository.findByEmailId(userLogin.getEmailId());
        if(user.isPresent()){
            if(user.get().getPassword().equals(userLogin.getPassword())){
                Optional<LoginStatus> status = loginRepository.findById(userLogin.getEmailId());
                if(!status.isPresent()) {
                    return new ResponseEntity<>("Login Successful\n"+loginRepository.save(new LoginStatus(userLogin.getEmailId(), tokenGenerator())), HttpStatus.OK);
                }
                return new ResponseEntity<>("Login Successful\n"+status.get(),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Login Failed Invalid User Email / Password", HttpStatus.OK);
    }

    private String tokenGenerator(){
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }




}
