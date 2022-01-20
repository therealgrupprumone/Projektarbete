package se.iths.projektarbete.exception;

public class ToShortPasswordException  extends RuntimeException {

    public ToShortPasswordException(String message) {
        super(message);
    }
}
