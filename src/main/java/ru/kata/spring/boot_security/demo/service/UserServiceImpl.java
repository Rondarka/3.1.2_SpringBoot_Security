package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User с id: " + id + " не обнаружен");
        }
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User с id: " + id + " не обнаружен"));
    }

    @Transactional
    @Override
    public void updateUser(Integer id, User user) {
        User updateUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User с id: " + id + " не обнаружен"));
        updateUser.setName(user.getName());
        updateUser.setLogin(user.getLogin());
        updateUser.setPassword(passwordEncoder.encode(user.getPassword()));
        updateUser.setRoles(user.getRoles());
        userRepository.save(updateUser);
    }
}
