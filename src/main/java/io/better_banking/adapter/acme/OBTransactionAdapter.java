package io.better_banking.adapter.acme;

import com.acme.banking.model.OBTransaction6;
import io.better_banking.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

@Component
public class OBTransactionAdapter {

    public Transaction adapt(OBTransaction6 transaction6) {
        return transactionFn.apply(transaction6);
    }

    private final Function<OBTransaction6, Transaction> transactionFn = transaction6 -> {
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(Integer.valueOf(transaction6.getAccountId()));

        Optional<Double> amount = extract(transaction6, o -> Double.valueOf(o.getAmount().getAmount()));
        Optional<Double> rate = extract(transaction6, o -> o.getCurrencyExchange().getExchangeRate().doubleValue());
        transaction.setAmount(amount.orElse(0.0) * rate.orElse(1.0));

        extract(transaction6, o -> transaction6.getCurrencyExchange().getUnitCurrency()).ifPresent(transaction::setCurrency);
        extract(transaction6, o -> transaction6.getCreditDebitIndicator().toString()).ifPresent(transaction::setType);
        extract(transaction6, o -> transaction6.getMerchantDetails().getMerchantName()).ifPresent(transaction::setMerchantName);
        extract(transaction6, o -> new Date(transaction6.getValueDateTime().toLocalDate().toEpochDay())).ifPresent(transaction::setDate);
        return transaction;
    };

    private <T> Optional<T> extract(OBTransaction6 obTransaction6, Function<OBTransaction6, T> fn) {
        try {
            return Optional.of(fn.apply(obTransaction6));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }
}
