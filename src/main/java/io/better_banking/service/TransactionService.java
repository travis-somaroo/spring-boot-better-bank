package io.better_banking.service;

import io.better_banking.model.Transaction;
import io.better_banking.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public List<Transaction> findAllByAccountNumber(final Integer accountNumber) {
        return transactionRepository.findAllByAccountNumber(accountNumber);
    }
}
