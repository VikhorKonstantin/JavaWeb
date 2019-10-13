package by.training.paragliding.service;

import by.training.paragliding.dao.Transaction;

public abstract class AbstractTransactionBasedService<T>
        implements Service<T> {
    /**
     * Transaction.
     */
    protected Transaction transaction;

    public AbstractTransactionBasedService(final Transaction newTransaction) {
        transaction = newTransaction;
    }

    protected static final String ROLL_BACK_EXC_MSG = "Rollback failed";
}
