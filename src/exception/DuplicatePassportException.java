package exception;

public class DuplicatePassportException
        extends Exception {

    public DuplicatePassportException(
            String message
    ){

        super(message);

    }

}