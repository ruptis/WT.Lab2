package by.bsuir.wtlab2.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SessionAttributes {
    USER_DETAILS("user"),
    LOCALE("locale");

    private final String value;
}
