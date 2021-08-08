package com.techprimers.cache.springredisexample.controller;


import com.techprimers.cache.springredisexample.service.UserService;
import com.techprimers.cache.springredisexample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/springredis")
@EnableCaching
public class UserResource {

    @Autowired
    private UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring Boot and Redis";
    }

    @PostMapping("/user/adduser")
    public User add(@RequestBody User user) {
        userService.save(user);
        return user;
    }

    @PutMapping("/user/updateuser")
    @CachePut(key = "#user.id", value = "USER")
    public User update(@RequestBody User user) {
        userService.update(user);
        return user;
    }

    @GetMapping("/user/{id}")
    @Cacheable(key = "#id", value = "USER")
    public User getUser(@PathVariable("id") final String id) {
        return userService.findById(id);

    }

    @DeleteMapping("/user/delete/{id}")
    @CacheEvict(key = "#id", value = "USER")
    public String delete(@PathVariable("id") final String id) {
        userService.delete(id);
        return "Given user with id " + id + " deleted ";
    }

    @GetMapping("/user/all")
    public Map<String, User> all() {
        return userService.findAll();
    }
}
