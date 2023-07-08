package com.eren.carecommerce.repository;

import com.eren.carecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);


    List<User> findAll();
    void deleteUserById(String id);
}
