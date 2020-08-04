package ru.evasmall.tm.service;

import ru.evasmall.tm.entity.User;
import ru.evasmall.tm.entity.enums.RoleEnum;
import ru.evasmall.tm.repository.UserRepository;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByLogin(String login) {
        if (login == null || login.isEmpty()) return null;
        return userRepository.findByLogin(login);
    }

    public User create(String login) {
        if (login == null || login.isEmpty()) return null;
        return userRepository.create(login);
    }

    public User create(String login, String firstname, String lastname, String middlname, String email, String password, RoleEnum role) {
        if (login == null || login.isEmpty()) return null;
        if (password == null || password.isEmpty()) return null;
        return userRepository.create(login, firstname, lastname, middlname, email, password, role);
    }

    public User removeByLogin(String login) {
        if (login == null || login.isEmpty()) return null;
        return userRepository.removeByLogin(login);
    }

}
