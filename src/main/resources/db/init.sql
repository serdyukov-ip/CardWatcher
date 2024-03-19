-- Banks table
CREATE TABLE Banks (
    id SERIAL PRIMARY KEY, -- unique bank id
    bank_name VARCHAR(50) NOT NULL -- bank name
);

CREATE TABLE PaymentStatus (
    id SERIAL PRIMARY KEY, -- unique status id
    status_name VARCHAR(50) NOT NULL -- name of the status (success, pending, overdue)
);

CREATE TABLE Currencies (
    id SERIAL PRIMARY KEY, -- unique currency id
    currency_name VARCHAR(50) NOT NULL -- currency name
);

CREATE TABLE Users (
    id SERIAL PRIMARY KEY, -- unique user id
    user_name VARCHAR(50), -- name/nickname of the user's choice
    login VARCHAR(50) NOT NULL, -- login
    password VARCHAR(200) -- password
);


CREATE TABLE CreditCards (
    id SERIAL PRIMARY KEY, -- unique credit card id
    id_user INTEGER NOT NULL REFERENCES Users(id) ON DELETE CASCADE, -- user identifier
    id_bank INTEGER NOT NULL, -- bank id
    id_currency INTEGER NOT NULL, -- currency id
    card_name VARCHAR(100) NOT NULL, -- card name
    last_four_digit VARCHAR(4) NOT NULL, -- last 4 digits of the map
    limit_amount NUMERIC(10, 2) NOT NULL, -- fund limit set by the bank
    total_debit NUMERIC(10, 2) NOT NULL, -- Total debt amount
    grace_period INTEGER NOT NULL, -- number of days of grace period
    interest_rate NUMERIC(5, 2) NOT NULL, -- interest rate on the card
    statement_day INTEGER NOT NULL CHECK (statement_day >= 1 AND statement_day <= 31), -- Day of the month on which the statement is generated
    FOREIGN KEY (id_user) REFERENCES Users(id),
    FOREIGN KEY (id_bank) REFERENCES Banks(id),
    FOREIGN KEY (id_currency) REFERENCES Currencies(id)
);

CREATE TABLE CreditCardPayments (
    id SERIAL PRIMARY KEY, -- unique payment id
    credit_card_id INTEGER NOT NULL REFERENCES CreditCards(id) ON DELETE CASCADE, -- unique credit card id
    dt_first_payment DATE NOT NULL, -- date of the first payment within the grace period
    bank_dt_payment DATE NOT NULL, -- payment date specified by the bank
    recommend_payment_dt DATE NOT NULL, -- recommended payment date
    calc_dt_full_repayment DATE NOT NULL, -- calculated date of repayment of the entire debt
    amount NUMERIC(10, 2) NOT NULL, -- payment amount
    payment_dt DATE, -- the user indicated that he made the payment on the date
    payment_status_id INTEGER NOT NULL, -- payment status
    description TEXT, -- user comment
    FOREIGN KEY (credit_card_id) REFERENCES CreditCards(id),
    FOREIGN KEY (payment_status_id) REFERENCES PaymentStatus(id)
);

insert into Banks (bank_name)
VALUES
    ('Alfa Bank'), ('Gazprom Bank'), ('Home Bank'), ('Opening bank'), ('Raiffeisen'),
    ('Renaissance Credit'), ('Rosbank'), ('SBER'), ('Tinkoff Bank'), ('VTB');

insert into PaymentStatus (status_name) VALUES ('SUCCESS'), ('PENDING'), ('OVERDUE');

insert into currencies (currency_name) VALUES ('EUR'), ('RUB'), ('USD');

insert into users(user_name, login, password) values
                                                  ('test1', 'user1', 'pass1234'),
                                                  ('test2', 'user2', 'pass1234'),
                                                  ('test3', 'user3', 'pass1234');

insert into CreditCards (
    id_user, -- user id
    id_bank , -- bank id
    card_name, -- currency id
    last_four_digit, -- card name
    id_currency, -- last 4 digits of the card
    limit_amount, -- fund limit set by the bank
    grace_period, -- number of days of grace period
    interest_rate, -- interest rate on the card
    statement_day, -- Total amount owed
    total_debit
)
values
    (1, 1, 'Alfa', '0001', 2, 150000, 50, 57.43, 1, 0),
    (1, 2, 'Gazprom', '0002', 2, 300000, 180, 24.45, 10, 0),
    (1, 3, 'Home Bank', '0003', 2, 170000, 120, 40.46, 26, 0),
    (1, 4, 'Otrkitie', '0004', 2, 150000, 180, 38.08, 15, 0),
    (1, 5, 'Raiffeisen', '0005', 2, 200000, 100, 23.73, 18, 0),
    (1, 6, 'Renaissance', '0006', 2, 30000, 200, 53.23, 1, 0),
    (1, 7, 'Rosbank', '0007', 2, 150000, 120, 34.53, 4, 0),
    (1, 8, 'SBER', '0008', 2, 50000, 120, 58.06, 5, 0),
    (1, 9, 'Tinkoff', '0009', 2, 200000, 55, 35.79, 10, 0),
    (1, 10, 'VTB', '0010', 2, 120000, 50, 53.29, 12, 0),
    (2, 1, 'Alfa', '0001', 2, 150000, 50, 57.43, 1, 0),
    (2, 2, 'Gazprom', '0002', 2, 300000, 180, 24.45, 10, 0),
    (2, 3, 'Home Bank', '0003', 2, 170000, 120, 40.46, 26, 0)
;

/*insert into creditcardpayments (
    credit_card_id, -- unique id of the credit card
    dt_first_payment, -- date of the first payment within the grace period
    bank_dt_payment, -- payment date specified by the bank
    recommend_payment_dt, -- recommended payment date
    calc_dt_full_repayment, -- calculated date of repayment of the entire debt
    amount, -- payment amount
    payment_status_id, -- payment status
    description -- user comment
)
values
    (1, '03/01/2024', '04/06/2024', '04/05/2024', '04/20/2024', 7259.88, 2, 'Lorem ipsum dolor sit amet'),
    (2, '10.03.2024', '16.04.2024', '14.04.2024', '06.09.2024', 8687.33, 2, 'consectetur adipiscing elit'),
    (3, '26.03.2024', '30.04.2024', '28.04.2024', '24.07.2024', 9449.52, 2, 'sed do eiusmod tempor incididunt ut labore et dolore magna aliqua'),
    (4, '15.03.2024', '01.05.2024', '30.04.2024', '11.09.2024', 8269.8, 2, 'Ut enim ad minim veniam'),
    (5, '18.03.2024', '23.04.2024', '21.04.2024', '26.06.2024', 4467.24, 2, 'quis nostrud exercitation ullamco laboris'),
    (6, '03/01/2024', '04/10/2024', '04/09/2024', '09/17/2024', 2744.5, 2, 'nisi ut aliquip ex ea commodo consequat'),
    (7, '03/04/2024', '04/01/2024', '03/29/2024', '07/02/2024', 5692.03, 2, 'Duis aute irure dolor in reprehenderit in voluptate'),
    (8, '03/05/2024', '04/18/2024', '04/17/2024', '07/03/2024', 7237.34, 2, 'velit esse cillum dolore eu fugiat nulla pariatur'),
    (9, '10.03.2024', '28.04.2024', '27.04.2024', '04.05.2024', 7528.72, 2, 'Excepteur sint occaecat cupidat non proident'),
    (10, '12.03.2024', '14.04.2024', '13.04.2024', '01.05.2024', 9934.07, 2, 'qui officia deserunt mollit anim id est laborum')
;*/