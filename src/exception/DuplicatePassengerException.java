package exception;

public class DuplicatePassengerException
        extends Exception {

    public DuplicatePassengerException(
            String message
    ){

        super(message);

    }

}