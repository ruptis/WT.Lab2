package by.bsuir.wtlab2.constants;

public enum RequestPrefixes {
    VIEWS_PREFIX("/WEB-INF/views/"),
    RESOURCES_PREFIX("/resources"),
    COMMAND_PREFIX("/command");

    private final String value;

    RequestPrefixes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
