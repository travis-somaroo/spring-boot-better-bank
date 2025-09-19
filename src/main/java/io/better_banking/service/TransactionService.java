package io.better_banking.service;

import io.better_banking.model.Transaction;
import io.better_banking.repository.TransactionApiClient;
import io.better_banking.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionApiClient transactionApiClient;

    public List<Transaction> findAllByAccountNumber(final Integer accountNumber) {
        return transactionApiClient.findAllByAccountNumber(accountNumber);
    }
}
