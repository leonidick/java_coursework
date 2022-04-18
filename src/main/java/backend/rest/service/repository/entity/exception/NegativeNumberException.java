package backend.rest.service.repository.entity.exception;

public class NegativeNumberException extends RuntimeException {
    public NegativeNumberException() {
        super("NEGATIVE NUMBER");
    }
}
