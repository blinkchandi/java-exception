package bank.exception;


public class InvalidAccountException extends BankingException {

    private static final long serialVersionUID = 1L;

    public InvalidAccountException(String message) {
        super(message);
    }
}
