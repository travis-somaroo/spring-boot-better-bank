package io.better_banking.repository;

import com.acme.banking.model.OBReadTransaction6;
import io.better_banking.adapter.acme.OBTransactionAdapter;
import io.better_banking.model.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TransactionApiRestClient implements TransactionApiClient {

    private final OBTransactionAdapter adapter;

    private final WebClient client;

    @Override
    public List<Transaction> findAllByAccountNumber(final Integer accountNumber) {
        OBReadTransaction6 res = client
                .get()
                .uri("accounts/{accountNumber}/transactions", accountNumber)
                .retrieve()
                .bodyToMono(OBReadTransaction6.class)
                .block();

        if (res == null || res.getData() == null) {
            return Collections.emptyList();
        }

        return res.getData().getTransaction().stream()
                .map(adapter::adapt)
                .collect(Collectors.toList());
    }
}
