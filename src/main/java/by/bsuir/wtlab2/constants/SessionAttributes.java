package by.bsuir.wtlab2.constants;

import lombok.Getter;

@Getter
public enum SessionAttributes {
    ROLE("role"),
    USER_PRINCIPAL("userPrincipal"),
    LOCALE("locale");

    private final String value;

    SessionAttributes(String value) {
        this.value = value;
    }

}
