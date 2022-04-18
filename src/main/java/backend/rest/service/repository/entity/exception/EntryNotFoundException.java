package backend.rest.service.repository.entity.exception;

public class EntryNotFoundException extends RuntimeException {
    public EntryNotFoundException() {
        super("ENTRY NOT FOUND");
    }
}
