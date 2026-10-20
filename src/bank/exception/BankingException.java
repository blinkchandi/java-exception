package bank.exception;

public abstract class BankingException extends Exception {

    private static final long serialVersionUID = 1L;

    protected BankingException(String message) {
        super(message);
    }

    protected BankingException(String message, Throwable cause) {
        super(message, cause);
    }
}
