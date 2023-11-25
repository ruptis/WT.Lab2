package by.bsuir.wtlab2.entity;

import by.bsuir.wtlab2.constants.Role;

public interface UserDetails {
    long getId();
    String getUsername();
    Role getRole();
}
