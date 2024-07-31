CREATE TABLE patient (
id INTEGER NOT NULL AUTO_INCREMENT,
last_name VARCHAR(30) NOT NULL,
first_name VARCHAR(30) NOT NULL,
birthdate VARCHAR(15) NOT NULL,
gender VARCHAR(2) NOT NULL,
address VARCHAR(50),
phone_number VARCHAR(20)

);

INSERT INTO patient (last_name, first_name, birthdate, gender, address, phone_number)
VALUES
('TestNone', 'Test', '1966-12-31', 'F', '1 Brookside St', '100-222-3333'),
('TestBorderline', 'Test', '1945-06-24', 'M', '2 High St', '200-333-4444'),
('TestInDanger', 'Test', '2004-06-18', 'M', '3 Club Road', '300-444-5555'),
('TestEarlyOnset', 'Test', '2002-06-28', 'F', '4 Valley Dr', '400-555-6666');