package com.techprimers.cache.springredisexample;


import com.techprimers.cache.springredisexample.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/springredis")
public class UserResource {

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
    public User getUser(@PathVariable("id") final String id) {
        return userRepository.findById(id);

    }

    @GetMapping("/user/delete/{id}")
    public Map<String, User> delete(@PathVariable("id") final String id) {
        userRepository.delete(id);
        return all();
    }

    @GetMapping("/user/all")
    public Map<String, User> all() {
        return userRepository.findAll();
    }
}
