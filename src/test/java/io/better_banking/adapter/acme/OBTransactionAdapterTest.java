package io.better_banking.adapter.acme;

import com.acme.banking.model.OBActiveOrHistoricCurrencyAndAmount9;
import com.acme.banking.model.OBCreditDebitCode1;
import com.acme.banking.model.OBMerchantDetails1;
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

    @Test
    public void testMerchant() {
        OBTransaction6 transaction6 = new OBTransaction6();
        transaction6.setAccountId("1234567");
        transaction6.setCreditDebitIndicator(OBCreditDebitCode1.DEBIT);
        transaction6.setAmount(amount());
        transaction6.setMerchantDetails(merchantDetails());
        Transaction transaction = adapter.adapt(transaction6);
        assertEquals("acme", transaction.getMerchantName());
    }

    @Test
    public void testInvalidAmount() {
        OBTransaction6 transaction6 = new OBTransaction6();
        transaction6.setAccountId("1234567");
        transaction6.setCreditDebitIndicator(OBCreditDebitCode1.DEBIT);
        transaction6.setAmount(invalidAmount());
        Transaction transaction = adapter.adapt(transaction6);
        assertEquals(0.0d, transaction.getAmount());
    }

    @Test
    public void testAmount() {
        OBTransaction6 transaction6 = new OBTransaction6();
        transaction6.setAccountId("1234567");
        transaction6.setCreditDebitIndicator(OBCreditDebitCode1.DEBIT);
        transaction6.setAmount(amount());
        Transaction transaction = adapter.adapt(transaction6);
        assertEquals(100.0d, transaction.getAmount());
    }


    private OBActiveOrHistoricCurrencyAndAmount9 amount() {
        OBActiveOrHistoricCurrencyAndAmount9 amount = new OBActiveOrHistoricCurrencyAndAmount9();
        amount.setAmount("100.00");
        amount.setCurrency("USD");
        return amount;
    }

    private OBActiveOrHistoricCurrencyAndAmount9 invalidAmount() {
        var amount = new OBActiveOrHistoricCurrencyAndAmount9();
        amount.setCurrency("100.00");
        amount.setAmount("USD");
        return amount;
    }

    private OBMerchantDetails1 merchantDetails() {
        OBMerchantDetails1 merchant = new OBMerchantDetails1();
        merchant.setMerchantName("acme");
        merchant.setMerchantCategoryCode("25");
        return merchant;
    }
}
