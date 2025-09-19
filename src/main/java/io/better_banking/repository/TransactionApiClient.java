package io.better_banking.repository;

import io.better_banking.model.Transaction;

import java.util.List;

public interface TransactionApiClient {

    List<Transaction> findAllByAccountNumber(final Integer accountNumber);
}
