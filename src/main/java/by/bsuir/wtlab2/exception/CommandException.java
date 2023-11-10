package by.bsuir.wtlab2.exception;

public class CommandException extends ProjectException {
    public CommandException(String message) {
        super(message);
    }

    public CommandException(String message, Exception innerException) {
        super(message, innerException);
    }
}
