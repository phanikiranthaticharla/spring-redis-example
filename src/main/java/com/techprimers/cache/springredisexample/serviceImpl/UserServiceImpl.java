package com.techprimers.cache.springredisexample.serviceImpl;

import com.techprimers.cache.springredisexample.model.User;
import com.techprimers.cache.springredisexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    private HashOperations hashOperations;


    public UserServiceImpl(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;

        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(User user) {
        hashOperations.put("USER", user.getId(), user);
    }

    @Override
    public Map<String, User> findAll() {
        return hashOperations.entries("USER");
    }

    @Override
    public User findById(String id) {
        System.out.println("Get Request hit the database!");
        return (User)hashOperations.get("USER", id);
    }

    @Override
    public void update(User user) {
        save(user);
    }

    @Override
    public void delete(String id) {

        hashOperations.delete("USER", id);
    }
}
