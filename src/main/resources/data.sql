
INSERT INTO merit_bank_user (username, password, role) VALUES ('admin', 'admin', 'ROLE_ADMIN');
INSERT INTO cdoffering (id, interest_rate, term) VALUES (1, 0.1, 3);
INSERT INTO cdoffering (id, interest_rate, term) VALUES (2, 0.2, 6);
INSERT INTO cdoffering (id, interest_rate, term) VALUES (3, 0.33, 12);
INSERT INTO account_holder (combined_balance, first_name, last_name, middle_name, ssn, user_id) VALUES ('1000', 'Admin', 'McAdmin', 'D', '123456789', '1');
INSERT INTO savings_account (id, account_open_date, balance, interest_rate, account_holder_id) VALUES (1, now(), '1000', '0.1', '1');
INSERT INTO deposit (id, amount, origin, transaction_date, account, transaction_type) VALUES (1, '100', 'ATM', now(), '1', 'deposit');



INSERT INTO merit_bank_user (username, password, role) VALUES ('user', 'user', 'ROLE_USER');
INSERT INTO account_holder (combined_balance, first_name, last_name, middle_name, ssn, user_id) VALUES ('1000', 'User', 'McUser', 'G', '987654321', '2');
INSERT INTO savings_account (id, account_open_date, balance, interest_rate, account_holder_id) VALUES (2, now(), '800', '0.1', '2');
INSERT INTO deposit (id, amount, origin, transaction_date, account, transaction_type) VALUES ('2', 100, 'Cash', now(), '2', 'deposit');
INSERT INTO deposit (id, amount, origin, transaction_date, account, transaction_type) VALUES ('3', '200', 'ATM', now(), '2', 'deposit');
INSERT INTO deposit (id, amount, origin, transaction_date, account, transaction_type) VALUES ('4', '50', 'Check', now(), '2', 'deposit');
INSERT INTO deposit (id, amount, origin, transaction_date, account, transaction_type) VALUES ('5', '100', 'Check', now(), '2', 'deposit');
INSERT INTO withdrawl (id, amount, origin, transaction_date, account, transaction_type) VALUES ('6', '200', 'ATM', now(), '2', 'withdrawl');


INSERT INTO personal_checking_account (id, account_open_date, balance, interest_rate, account_holder_id) VALUES (3, now(), '200', '0.01', '2');
INSERT INTO deposit (id, amount, origin, transaction_date, account, transaction_type) VALUES ('7', 100, 'Cash', now(), '3', 'deposit');
INSERT INTO deposit (id, amount, origin, transaction_date, account, transaction_type) VALUES ('8', '200', 'ATM', now(), '3', 'deposit');
INSERT INTO withdrawl (id, amount, origin, transaction_date, account, transaction_type) VALUES ('9', '17', 'Check', now(), '3', 'withdrawl');
INSERT INTO deposit (id, amount, origin, transaction_date, account, transaction_type) VALUES ('10', '50', 'Check', now(), '3', 'deposit');
INSERT INTO withdrawl (id, amount, origin, transaction_date, account, transaction_type) VALUES ('11', '200', 'Check', now(), '3', 'withdrawl');

INSERT INTO merit_bank_user (username, password, role) VALUES ('rosa', 'rosa', 'ROLE_ADMIN');
INSERT INTO account_holder (combined_balance, first_name, last_name, ssn, user_id) VALUES ('250000', 'Caro', 'Rodriguez', '456789123', '3');
INSERT INTO merit_bank_user (username, password, role) VALUES ('spencer', 'spencer', 'ROLE_ADMIN');
INSERT INTO account_holder (combined_balance, first_name, last_name, ssn, user_id) VALUES ('250000', 'Spencer', 'LaFarge', '65498732', '4');
INSERT INTO merit_bank_user (username, password, role) VALUES ('matt', 'matt', 'ROLE_USER');
INSERT INTO account_holder (combined_balance, first_name, last_name, middle_name, ssn, user_id) VALUES ('250000', 'Matt', 'Chvatal', 'D', '546789120', '5');

