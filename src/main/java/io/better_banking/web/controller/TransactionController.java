package io.better_banking.web.controller;

import io.better_banking.service.TransactionService;
import io.better_banking.web.dto.TransactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/{accountNumber}")
    public List<TransactionDto> findAllByAccountNumber(@PathVariable("accountNumber") final Integer accountNumber) {
        return transactionService.findAllByAccountNumber(accountNumber)
                .stream()
                .map(TransactionDto::toDto)
                .collect(toList());
    }

}
