package com.techprimers.cache.springredisexample;


import com.techprimers.cache.springredisexample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/springredis")
@EnableCaching
public class UserResource {

    @Autowired
    private UserRepository userRepository;

    public UserResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring Boot and Redis";
    }

    @PostMapping("/user/adduser")
    public User add(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @PostMapping("/user/updateuser")
    public String update(@RequestBody User user) {
        userRepository.update(user);
        return "Given user updated";
    }

    @GetMapping("/user/{id}")
    @Cacheable(key = "#id", value = "USER")
    public User getUser(@PathVariable("id") final String id) {
        return userRepository.findById(id);

    }

    @DeleteMapping("/user/delete/{id}")
    @CacheEvict(key = "#id", value = "USER")
    public String delete(@PathVariable("id") final String id) {
        userRepository.delete(id);
        return "Given user with id " + id + " deleted ";
    }

    @GetMapping("/user/all")
    public Map<String, User> all() {
        return userRepository.findAll();
    }
}
