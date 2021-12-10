package se.iths.projektarbete.exception;

public class UserNameTakenException extends RuntimeException {

    public UserNameTakenException(String message) {
        super(message);
    }
}
