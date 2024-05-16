package ru.gb.my_first_crud.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.my_first_crud.model.User;
import ru.gb.my_first_crud.repository.UserRepository1;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository1 userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }


    public User getOne(int id) {
        return userRepository.getOne(id);
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }
}
