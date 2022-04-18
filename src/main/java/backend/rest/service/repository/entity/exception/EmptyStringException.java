package backend.rest.service.repository.entity.exception;

public class EmptyStringException extends RuntimeException {
    public EmptyStringException() {
        super("EMPTY STRING");
    }
}
