package ru.evasmall.tm.repository;

import ru.evasmall.tm.entity.User;
import ru.evasmall.tm.entity.enums.RoleEnum;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private final List<User> users = new ArrayList<>();

    public List<User> findAll() {
        return users;
    }

    public User findByLogin(final String login) {
        for (final User user: users) {
            if(user.getLogin().equals(login)) return user;
        }
        return null;
    }

    public User create(final String login) {
        final User user = new User(login);
        users.add(user);
        return user;
    }

    public User create(final String login, String firstname, String lastname, String middlname, String email, String password, RoleEnum role) {
        final User user = new User(login);
        user.setLogin(login);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setMiddlname(middlname);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        users.add(user);
        return user;
    }

    public User removeByLogin (final String login) {
        final User user = findByLogin(login);
        if (user == null) return null;
        users.remove(user);
        return user;
    }

}
