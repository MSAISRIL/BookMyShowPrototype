package com.bookshow.BookMyShowPrototype.repository;

import com.bookshow.BookMyShowPrototype.models.LoginStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface LoginRepository extends JpaRepository<LoginStatus, String> {
    Optional<LoginStatus> findByloginToken(String loginToken);

    int deleteByloginToken(String loginToken);
}
