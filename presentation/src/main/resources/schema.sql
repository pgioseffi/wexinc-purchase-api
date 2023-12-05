DROP SEQUENCE IF EXISTS purchase_identification_sequence;

CREATE SEQUENCE purchase_identification_sequence;

DROP TABLE IF EXISTS purchase;

CREATE TABLE purchase (
	id numeric,
	description VARCHAR(50) NOT NULL,
	transaction_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	amount decimal(13, 4) NOT NULL,
	CONSTRAINT CHK_AMOUNT CHECK (amount > 0),
	PRIMARY KEY(id));