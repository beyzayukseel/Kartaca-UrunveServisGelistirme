package com.beyzanur.accountservice.repository;

import com.beyzanur.accountservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    User getUserByUsername(String username);

    Optional<User> findByUsername(String username);
}
