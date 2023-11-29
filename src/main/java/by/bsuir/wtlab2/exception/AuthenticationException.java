package by.bsuir.wtlab2.exception;

public class AuthenticationException extends ServiceException {
    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, ServiceException cause) {
        super(message, cause);
    }
}
