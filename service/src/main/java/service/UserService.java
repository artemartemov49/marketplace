package service;

import com.artem.entity.User;
import com.artem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    UserRepository userRepository;

    public String getUsers() {
        return userRepository.findAll().stream()
                .map(User::getFirstName)
                .collect(Collectors.joining("-", "{", "}"));
    }
}
