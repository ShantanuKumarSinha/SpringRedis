package com.shann.springredis.controller;

import com.shann.springredis.exceptions.UserNotFoundException;
import com.shann.springredis.model.User;
import com.shann.springredis.service.RedisService;
import com.shann.springredis.service.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    private RedisService redisService;

    public UserController(UserService userService, RedisService redisService) {
        this.userService = userService;
        this.redisService = redisService;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) throws UserNotFoundException {
        var cachedUser = redisService.get(id, User.class);
        if (cachedUser==null) {
            var user = userService.getUser(Long.valueOf(id));
            redisService.set(id, user, 600);
            return user;
        }
        return cachedUser;
    }


}
