package com.eren.carecommerce.controller;


import com.eren.carecommerce.model.User;
import com.eren.carecommerce.service.UserService;
import jakarta.validation.constraints.Null;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService service;


        public UserController(UserService service) {
                this.service = service;
        }


    @GetMapping("/all")
        public ResponseEntity<List<User>>  getAllUsers() {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if(auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> INSIDE IF");
                List<User> userList = service.getAllUsers();
                return new ResponseEntity<>(userList, HttpStatus.OK);
            } else {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> INSIDE ELSE");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }


        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteUser(@PathVariable String id){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if(auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> INSIDE IF");
                 service.deleteUser(id);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> INSIDE ELSE");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }

}
