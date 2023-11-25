package by.bsuir.wtlab2.controller.commands.implementations.results;

public class NotFoundResult extends StatusCodeResult {
    private static final int NOT_FOUND_STATUS_CODE = 404;
    public NotFoundResult() {
        super(NOT_FOUND_STATUS_CODE);
    }
}
