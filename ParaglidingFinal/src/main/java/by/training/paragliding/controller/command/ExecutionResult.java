package by.training.paragliding.controller.command;

public class ExecutionResult {
    private final boolean isForward;
    private final String url;

    public ExecutionResult(final boolean newIsForward, final String newUrl) {
        isForward = newIsForward;
        url = newUrl;
    }

    public boolean isForward() {
        return isForward;
    }

    public String getUrl() {
        return url;
    }
}
