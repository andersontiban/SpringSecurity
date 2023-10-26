package com.anderson.security.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long getUserId(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        Long userId= Long.valueOf(user.get().getId());

        if (userId == null) {
            throw new UsernameNotFoundException("User with email " + email + " not found");
        }
        return userId;

    }


}
