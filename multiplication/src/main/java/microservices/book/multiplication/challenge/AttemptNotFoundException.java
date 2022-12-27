package microservices.book.multiplication.challenge;

public class AttemptNotFoundException extends RuntimeException {
    public AttemptNotFoundException(String message) {
        super(message);
    }
}
