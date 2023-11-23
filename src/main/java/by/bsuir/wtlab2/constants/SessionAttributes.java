package by.bsuir.wtlab2.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SessionAttributes {
    USER("user"),
    LOCALE("locale");

    private final String value;
}
