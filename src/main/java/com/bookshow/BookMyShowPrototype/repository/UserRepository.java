package com.bookshow.BookMyShowPrototype.repository;

import com.bookshow.BookMyShowPrototype.models.BookMyShowUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<BookMyShowUser, Long> {
    Optional<BookMyShowUser> findByEmailId(String emailId);
}
