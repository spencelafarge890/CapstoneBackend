
INSERT INTO merit_bank_user (username, password, role) VALUES ('admin', 'admin', 'ROLE_ADMIN');
INSERT INTO account_holder (first_name, last_name, middle_name, ssn, user_id) VALUES ('admin', 'mcadmin', 'd', '123456789', '1');
INSERT INTO savings_account (account_open_date, balance, interest_rate, account_holder_id) VALUES (now(), '1000', '0.001', '1');
INSERT INTO transaction (transaction, amount, origin, transaction_date) VALUES ('deposit', '100', 'ATM', now());
INSERT INTO savings_account_deposits (savings_account_id, deposits_id) VALUES ('1', '1');

