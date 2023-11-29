package by.bsuir.wtlab2.exception;

public class DAOConfigurationException extends ProjectException {
    public DAOConfigurationException(String message) {
        super(message);
    }

    public DAOConfigurationException(String message, Exception innerException) {
        super(message, innerException);
    }
}
