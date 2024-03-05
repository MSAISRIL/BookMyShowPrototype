package com.bookshow.BookMyShowPrototype.repository;

import com.bookshow.BookMyShowPrototype.models.LoginStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<LoginStatus, String> {
}
