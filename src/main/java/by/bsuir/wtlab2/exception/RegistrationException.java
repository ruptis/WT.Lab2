package by.bsuir.wtlab2.exception;

public class RegistrationException extends ServiceException {
    public RegistrationException(String message) {
        super(message);
    }

    public RegistrationException(String message, ServiceException cause) {
        super(message, cause);
    }
}
