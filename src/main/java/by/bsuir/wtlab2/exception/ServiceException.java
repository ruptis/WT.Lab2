package by.bsuir.wtlab2.exception;

public class ServiceException extends ProjectException {
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Exception innerException) {
        super(message, innerException);
    }
}
