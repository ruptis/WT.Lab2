package by.bsuir.wtlab2.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    USER("user"),
    ADMIN("admin"),
    BANNED("banned"),
    GUEST("guest");

    private final String name;
}
