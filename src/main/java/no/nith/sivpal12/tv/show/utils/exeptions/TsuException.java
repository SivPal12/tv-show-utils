package no.nith.sivpal12.tv.show.utils.exeptions;

public abstract class TsuException extends RuntimeException {

    public TsuException(String message) {
        super(message);
    }

    public TsuException(String message, Throwable cause) {
        super(message + " Nested message: " + cause.getMessage(), cause);
    }

}
