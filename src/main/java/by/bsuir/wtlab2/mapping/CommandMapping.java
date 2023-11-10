package by.bsuir.wtlab2.mapping;

import by.bsuir.wtlab2.annotations.WebCommand;
import by.bsuir.wtlab2.constants.HttpMethod;
import jakarta.servlet.http.HttpServletRequest;

public record CommandMapping(String mapping, HttpMethod method) {
    public static CommandMapping of(HttpServletRequest request) {
        return new CommandMapping(request.getPathInfo(), HttpMethod.valueOf(request.getMethod()));
    }
    public static CommandMapping of(WebCommand webCommand) {
        return new CommandMapping(webCommand.mapping(), webCommand.method());
    }

    @Override
    public String toString() {
        return method + " " + mapping;
    }
}
