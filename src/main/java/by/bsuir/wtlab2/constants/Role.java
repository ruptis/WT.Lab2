package by.bsuir.wtlab2.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    USER("user", "/"),
    ADMIN("admin", "/admin"),
    BANNED("banned", "/banned"),
    GUEST("guest", "/login");

    private final String name;
    private final String homePage;
}
