package ru.evasmall.tm.entity.enums;

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
