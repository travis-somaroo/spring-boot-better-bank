package io.better_banking.service;

import io.better_banking.model.Transaction;
import io.better_banking.repository.TransactionApiClient;
import io.better_banking.repository.TransactionRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionApiClient transactionApiClient;

    @CircuitBreaker(name = "myServiceCircuitBreaker", fallbackMethod = "findAllByAccountNumber")
    public List<Transaction> findAllByAccountNumber(final Integer accountNumber) {
        return transactionApiClient.findAllByAccountNumber(accountNumber);
    }

    private List<Transaction> findAllByAccountNumber(final Integer accountNumber, final Throwable throwable) {
        log.info("Falling back to database to get transactions");
        return transactionRepository.findAllByAccountNumber(accountNumber);
    }

}
