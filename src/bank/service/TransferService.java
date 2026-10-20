package bank.service;

import bank.exception.InsufficientFundsException;
import bank.exception.InvalidAccountException;
import bank.exception.TransferLimitExceededException;
import bank.model.BankAccount;


public class TransferService {

    public static final double TRANSFER_LIMIT = 10_000.0;

    /**
     * Transfère {@code amount} du compte source vers le compte destinataire.
     *
     * @throws IllegalArgumentException       si le montant n'est pas strictement positif
     * @throws InvalidAccountException        si un compte est null ou si source == destination
     * @throws TransferLimitExceededException si le montant dépasse {@link #TRANSFER_LIMIT}
     * @throws InsufficientFundsException     si le solde du compte source est insuffisant
     */
    public void transfer(BankAccount source, BankAccount destination, double amount)
            throws InvalidAccountException, TransferLimitExceededException, InsufficientFundsException {
        validate(source, destination, amount);

        source.debit(amount);
        destination.credit(amount);
    }

    private void validate(BankAccount source, BankAccount destination, double amount)
            throws InvalidAccountException, TransferLimitExceededException, InsufficientFundsException {

        if (source == null) {
            throw new InvalidAccountException("Le compte source est absent (null).");
        }
        if (destination == null) {
            throw new InvalidAccountException("Le compte destinataire est absent (null).");
        }
        if (source.getAccountNumber().equals(destination.getAccountNumber())) {
            throw new InvalidAccountException(
                    "Le compte source et le compte destinataire sont identiques : " + source.getAccountNumber());
        }
        if (!(amount > 0)) {
            throw new IllegalArgumentException("Le montant doit être strictement positif (reçu : " + amount + ").");
        }
        if (amount > TRANSFER_LIMIT) {
            throw new TransferLimitExceededException(amount, TRANSFER_LIMIT);
        }
        if (amount > source.getBalance()) {
            throw new InsufficientFundsException(source.getAccountNumber(), source.getBalance(), amount);
        }
    }
}
