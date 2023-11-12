package by.bsuir.wtlab2.constants;

import lombok.Getter;

@Getter
public enum Roles {
    ADMIN("admin"),
    USER("user");

    private final String name;

    Roles(String name) {
        this.name = name;
    }

}
