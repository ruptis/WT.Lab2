package by.bsuir.wtlab2.controller.commands.results;

public class OkResult extends StatusCodeResult {
    private static final int OK_STATUS_CODE = 200;
    public OkResult() {
        super(OK_STATUS_CODE);
    }
}