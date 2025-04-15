CREATE TABLE if not exists pay_request (
	id varchar(250),
	transaction_id varchar(250),
	payer_customer_id varchar(250),
	payer_account_id varchar(250),
	payee_bank varchar(250),
	payee_ifsc varchar(250),
	payee_account_type varchar(250),
	payee_account_number varchar(250),
	amount int,
	notes varchar(255),
	created_on timestamp,
	status varchar(20),
	failed_reason varchar(400),
	PRIMARY KEY (id)
);