package by.bsuir.wtlab2.exception;

import lombok.Getter;

@Getter
public class ProjectException extends Exception {
    private final Exception innerException;

    public ProjectException(String message) {
        super(message);
        innerException = null;
    }

    public ProjectException(String message, Exception innerException) {
        super(message);
        this.innerException = innerException;
    }

    public ProjectException(Exception innerException) {
        super(innerException);
        this.innerException = innerException;
    }
}
