package com.bookshow.BookMyShowPrototype.Schedulers;

import com.bookshow.BookMyShowPrototype.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LoginScheduler {

    private final LoginRepository loginRepository;
    @Autowired
    public LoginScheduler(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Scheduled(cron = "*/10 * * * *")
    public void clearLoginRecords() {
        loginRepository.deleteAll();
    }
}
