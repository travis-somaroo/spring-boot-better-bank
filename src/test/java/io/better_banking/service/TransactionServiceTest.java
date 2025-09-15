package io.better_banking.service;

import io.better_banking.BetterBankingApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest(classes = {BetterBankingApplication.class})
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Test
    public void testTransactionCount() throws InterruptedException {
        assertEquals(2, transactionService.findAllByAccountNumber(1234567).size());
//        Thread.sleep(600000);
    }
}
