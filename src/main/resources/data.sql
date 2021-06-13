
INSERT INTO merit_bank_user (username, password, role) VALUES ('admin', 'admin', 'ROLE_ADMIN');
INSERT INTO cdoffering (id, interest_rate, term) VALUES (1, 0.1, 3);
INSERT INTO cdoffering (id, interest_rate, term) VALUES (2, 0.2, 6);
INSERT INTO cdoffering (id, interest_rate, term) VALUES (3, 0.33, 12);
INSERT INTO account_holder (combined_balance, first_name, last_name, middle_name, ssn, user_id) VALUES ('1000', 'admin', 'mcadmin', 'd', '123456789', '1');
INSERT INTO savings_account (account_open_date, balance, interest_rate, account_holder_id) VALUES (now(), '1000', '0.1', '1');
INSERT INTO transaction (transaction, amount, origin, transaction_date, account, transaction_type) VALUES ('deposit', '100', 'ATM', now(), '1', 'deposit');



INSERT INTO merit_bank_user (username, password, role) VALUES ('user', 'user', 'ROLE_USER');
INSERT INTO account_holder (combined_balance, first_name, last_name, middle_name, ssn, user_id) VALUES ('1000', 'User', 'McUser', 'G', '987654321', '2');
INSERT INTO savings_account (account_open_date, balance, interest_rate, account_holder_id) VALUES (now(), '800', '0.1', '2');
INSERT INTO transaction (transaction, amount, origin, transaction_date, account, transaction_type) VALUES ('deposit', '100', 'Cash', now(), '2', 'deposit');
INSERT INTO transaction (transaction, amount, origin, transaction_date, account, transaction_type) VALUES ('deposit', '200', 'ATM', now(), '2', 'deposit');
INSERT INTO transaction (transaction, amount, origin, transaction_date, account, transaction_type) VALUES ('deposit', '50', 'Check', now(), '2', 'deposit');
INSERT INTO transaction (transaction, amount, origin, transaction_date, account, transaction_type) VALUES ('deposit', '100', 'Check', now(), '2', 'deposit');
INSERT INTO transaction (transaction, amount, origin, transaction_date, account, transaction_type) VALUES ('withdrawl', '200', 'ATM', now(), '2', 'withdrawl');

