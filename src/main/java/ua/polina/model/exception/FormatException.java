package ua.polina.model.exception;

public class FormatException extends Exception {
    public FormatException(String message, String expression) {
        super(message + expression);
    }
}
