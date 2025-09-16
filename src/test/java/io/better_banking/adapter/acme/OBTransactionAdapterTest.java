package io.better_banking.adapter.acme;

import com.acme.banking.model.OBActiveOrHistoricCurrencyAndAmount9;
import com.acme.banking.model.OBCreditDebitCode1;
import com.acme.banking.model.OBTransaction6;
import io.better_banking.model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OBTransactionAdapterTest {

    private final OBTransactionAdapter adapter = new OBTransactionAdapter();

    @Test
    public void testNullMerchant() {
        OBTransaction6 transaction6 = new OBTransaction6();
        transaction6.setAccountId("1234567");
        transaction6.setCreditDebitIndicator(OBCreditDebitCode1.DEBIT);
        transaction6.setAmount(amount());
        Transaction transaction = adapter.adapt(transaction6);
        assertEquals(100.0d, transaction.getAmount());
    }


    private OBActiveOrHistoricCurrencyAndAmount9 amount() {
        var amount = new OBActiveOrHistoricCurrencyAndAmount9();
        amount.setAmount("100.00");
        amount.setCurrency("USD");
        return amount;
    }
}
