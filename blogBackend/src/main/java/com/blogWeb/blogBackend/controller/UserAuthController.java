package com.blogWeb.blogBackend.controller;

import com.blogWeb.blogBackend.dto.LoginDTO;
import com.blogWeb.blogBackend.dto.UpdateUserDTO;
import com.blogWeb.blogBackend.dto.UserDTO;
import com.blogWeb.blogBackend.dto.UserRegisterDTO;
import com.blogWeb.blogBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserAuthController {
    @Autowired private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterDTO registerDTO){
        UserDTO userDTO = userService.registerUser(registerDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        UserDTO login = userService.login(loginDTO);
        return new ResponseEntity<>(login,HttpStatus.OK);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody UpdateUserDTO updateUserDTO){
        UserDTO userDTO = userService.updateUser(userId, updateUserDTO);
        return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
