package by.training.paragliding.service.transaction;

import by.training.paragliding.dao.Transaction;

abstract class AbstractTransactionBasedService {
    /**
     * Transaction.
     */
    protected Transaction transaction;

    AbstractTransactionBasedService(final Transaction newTransaction) {
        transaction = newTransaction;
    }

    static final String ROLL_BACK_EXC_MSG = "Rollback failed";
}
