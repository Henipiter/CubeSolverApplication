package exceptions;

public class IncorectMoveException extends Exception{

    public IncorectMoveException(String errorMessage) {
        super("Cannot make "+errorMessage+"move");
    }

}
