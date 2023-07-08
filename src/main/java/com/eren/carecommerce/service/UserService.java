package com.eren.carecommerce.service;

import com.eren.carecommerce.model.User;
import com.eren.carecommerce.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getAllUsers(){
        List<User> userList = userRepository.findAll();
        return userList;
    };

    @Transactional
    public void deleteUser(String id){
        userRepository.deleteUserById(id);
    }
}
