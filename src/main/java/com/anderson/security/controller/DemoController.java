package com.anderson.security.controller;

import com.anderson.security.todos.TodosEntity;
import com.anderson.security.todos.TodosService;
import com.anderson.security.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class DemoController {

    private final TodosService todosService;
    private final UserService userService;

    @GetMapping("/list")
    public List<TodosEntity> listTest() {
        return todosService.getAll();
    }

    @PostMapping("/new")
    public ResponseEntity<String> add(@RequestBody TodosEntity todo) {
        todosService.addItem(todo);
        return new ResponseEntity<>("Todo added", HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long userID = userService.getUserId(username);
        return "The user id is " + userID;

    }


}
