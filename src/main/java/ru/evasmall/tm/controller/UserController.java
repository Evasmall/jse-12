package ru.evasmall.tm.controller;

import ru.evasmall.tm.entity.User;
import ru.evasmall.tm.entity.enums.RoleEnum;
import ru.evasmall.tm.service.MD5Hash;
import ru.evasmall.tm.service.UserService;

import java.util.List;

public class UserController extends AbstractController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public int createUser() {
        System.out.println("[USER REGISTRATION]");
        System.out.println("[PLEASE ENTER YOUR NEW LOGIN:]");
        final String login = scanner.nextLine();
        if (login == null || login.isEmpty()) {
            System.out.println("[LOGIN MAST NOT BE EMPTY!]");
            System.out.println("[FAIL]");
            return -1;
        };
        //Проверка на существующие идентичные логины
        if (userService.findByLogin(login) != null) {
            System.out.println("[THIS LOGIN EXISTS!]");
            System.out.println("[FAIL]");
            return -1;
        };
        System.out.println("[PLEASE ENTER YOUR FIRSTNAME:]");
        final String firstname = scanner.nextLine();
        System.out.println("[PLEASE ENTER YOUR LASTNAME:]");
        final String lastname = scanner.nextLine();
        System.out.println("[PLEASE ENTER YOUR MIDDLNAME:]");
        final String middlname = scanner.nextLine();
        System.out.println("[PLEASE ENTER EMAIL:]");
        final String email = scanner.nextLine();
        System.out.println("[PLEASE ENTER PASSWORD:]");
        final String password = MD5Hash.getHash(scanner.nextLine(), "MD5");
        //Проверка на пустой пароль
        if (password == null || password.isEmpty()) {
            System.out.println("[PASSWORD MAST NOT BE EMPTY!]");
            System.out.println("[FAIL]");
            return -1;
        };
        final RoleEnum role = RoleEnum.USER;
        userService.create(login, firstname, lastname, middlname, email, password, role);
        System.out.println("[OK]");
        return 0;
    }

    public int listUser() {
        System.out.println("[LIST USER]");
        int index = 1;
        viewUsers(userService.findAll());
        System.out.println("[OK]");
        return 0;
    }

    public void viewUsers (final List<User> users) {
        if (users == null || users.isEmpty()) return;
        int index = 1;
        for (final User user: users) {
            System.out.println(index + ". LOGIN: " + user.getLogin() + "; FIRSTNAME: " + user.getFirstname() + "; MIDDLNAME: "
                    + user.getMiddlname() + "; LASTNAME: " + user.getLastname() + "; EMAIL: " + user.getEmail() + "; ROLE: " + user.getRole().name()
                    + "; PASSWORD: " + user.getPassword());
            index++;
        }
    }

    public int removeUserByLogin() {
        System.out.println("[REMOVE USER BY LOGIN]");
        System.out.println("[PLEASE ENTER YOUR ADMIN LOGIN:]");
        final String login_admin = scanner.nextLine();
        //Проверка на существование логина
        if (userService.findByLogin(login_admin) == null) {
            System.out.println("[THIS LOGIN NOT EXISTS!]");
            System.out.println("[FAIL]");
            return -1;
        };
        //Проверка на права администратора
        if (userService.findByLogin(login_admin).getRole().name().equals("ADMIN")) {
            System.out.println("[PLEASE ENTER YOUR PASSWORD:]");
            final String password_admin = scanner.nextLine();
            //Проверка пароля
            if (MD5Hash.getHash(password_admin, "MD5").equals(userService.findByLogin(login_admin).getPassword())) {
                System.out.println("[PLEASE ENTER LOGIN OF REMOVE USER:");
                final String login = scanner.nextLine();
                final User user = userService.removeByLogin(login);
                if (login == null) System.out.println("[FAIL]");
                else System.out.println("[OK]");
                return 0;
            }
            else {
                System.out.println("[INCORRECT PASSWORD!]");
                return -1;
            }
        }
        else {
            System.out.println("[THIS FUNCTIONALITY IS FOR ADMINS ONLY!]");
            return -1;
        }
    }

}
