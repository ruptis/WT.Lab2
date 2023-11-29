package by.bsuir.wtlab2.exception;

public class DAOException extends ProjectException {
    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Exception innerException) {
        super(message, innerException);
    }

    public DAOException(Exception innerException) {
        super(innerException);
    }
}
