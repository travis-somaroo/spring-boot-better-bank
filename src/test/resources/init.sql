CREATE TABLE transactions
(
    id             bigint NOT NULL,
    account_number integer,
    amount         double precision,
    currency       varchar(255),
    date           timestamp,
    merchant_logo  varchar(255),
    merchant_name  varchar(255),
    type           varchar(255),
    PRIMARY KEY (id)
);

INSERT INTO transactions (id, type, date, account_number, currency, amount, merchant_name, merchant_logo)
VALUES (1, 'debit', CURRENT_TIMESTAMP, 1234567, 'USD', 100.00, 'Acme Ltd.', 'images/acme.png');

INSERT INTO transactions (id, type, date, account_number, currency, amount, merchant_name, merchant_logo)
VALUES (2, 'debit', CURRENT_TIMESTAMP, 1234567, 'USD', 5.00, 'Caffeinenation Inc', 'images/caffeinenation.png');
