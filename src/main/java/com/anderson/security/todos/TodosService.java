package com.anderson.security.todos;

import com.anderson.security.user.User;
import com.anderson.security.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodosService {

    private final TodosRepository todosRepository;
    private final UserRepository userRepository;

    @Autowired
    public TodosService(TodosRepository todosRepository, UserRepository userRepository) {
        this.todosRepository = todosRepository;
        this.userRepository = userRepository;
    }


    public List<TodosEntity> getAll() {
        return todosRepository.findAll();
    }

    public ResponseEntity<String> addItem(TodosEntity todo) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
            Optional<User> user = userRepository.findByEmail(currentPrincipalName);

            if (user == null) {
                return new ResponseEntity<>("Could not retrieve or find user from DB", HttpStatus.CONFLICT);
            }
            todo.setUser(new User()
                    .builder().id(user.get().getId())
                    .firstName(user.get().getFirstName())
                    .lastName(user.get().getLastName())
                    .email(user.get().getEmail()).build());
            todosRepository.save(todo);
        } catch (Exception e) {
            //TODO add a logger
            System.out.println(e);
        }
        return new ResponseEntity<>("Added todo", HttpStatus.OK);
    }
}
