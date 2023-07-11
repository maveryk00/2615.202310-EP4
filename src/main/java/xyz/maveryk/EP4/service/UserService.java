package xyz.maveryk.EP4.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.maveryk.EP4.entity.User;
import xyz.maveryk.EP4.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

}
