package no.nith.sivpal12.tv.show.utils.exeptions;

public class TsuUnknownExeption extends TsuException {

    public TsuUnknownExeption(String message) {
        super(message);
    }

    public TsuUnknownExeption(String message, Throwable cause) {
        super(message, cause);
    }

}
