package technifutur.be.technifutur.exceptions;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException() {
        super("entity not found.");
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
