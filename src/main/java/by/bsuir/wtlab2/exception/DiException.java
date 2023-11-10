package by.bsuir.wtlab2.exception;

public class DiException extends ProjectException {
    public DiException(String message) {
        super(message);
    }

    public DiException(String message, Exception innerException) {
        super(message, innerException);
    }
}
