package ru.evasmall.tm.enumerated;

public enum RoleEnum {
    USER,
    ADMIN;

    RoleEnum() {
    }

    @Override
    public String toString() {
        return RoleEnum.values().toString();
    }

}
