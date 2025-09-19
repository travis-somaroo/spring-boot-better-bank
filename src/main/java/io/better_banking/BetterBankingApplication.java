package io.better_banking;

import io.better_banking.model.Transaction;
import io.better_banking.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Date;

@SpringBootApplication
public class BetterBankingApplication {


    @Value("${io.better-banking.integration.base-url}")
    private String baseUrl;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(BetterBankingApplication.class, args);
    }

    @Bean
    @Profile(value = "dev")
    public ApplicationRunner runner(final TransactionRepository repository) {
        return args -> {
            repository.deleteAll();
            Transaction transaction = Transaction
                    .builder()
                    .type("Credit")
                    .date(new Date())
                    .accountNumber(1234567)
                    .currency("USD")
                    .amount(100.00)
                    .merchantName("Globex")
                    .merchantLogo("images/globex.png")
                    .build();
            repository.save(transaction);
        };
    }
}
