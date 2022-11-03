CREATE TABLE IF NOT EXISTS indv_master (
    objid bigserial NOT NULL,
    address_line1 varchar(255) NOT NULL,
    address_line2 varchar(255) NULL,
    created_dt TIMESTAMP NOT NULL,
    city varchar(80) NOT NULL,
    country varchar(2) NOT NULL,
    email varchar(80) NOT NULL,
    first_name varchar(80) NULL,
    last_name varchar(80) NOT NULL,
    fyndr_handle varchar(32) NULL,
    is_business bool NOT NULL,
    phone varchar(10) NULL,
    ctry_code varchar(4) NULL,
    state varchar(40) NOT NULL,
    postal_code varchar(6) NOT NULL,
    bizid int8 NULL,
    qrid int8 NOT NULL,
    yob varchar(4) NULL,
    gender varchar(2) NULL,
    account_status varchar(16) NULL DEFAULT 'ACTIVE'::character varying
);
CREATE TABLE IF NOT EXISTS biz_master (
    objid bigserial NOT NULL,
    biz_name varchar(255) NOT NULL,
    biz_type varchar(255) NOT NULL,
    website varchar(255) NULL,
);
CREATE TABLE IF NOT EXISTS code_master (
    objid bigserial NOT NULL
);
CREATE TABLE IF NOT EXISTS invoice (
	objid bigserial NOT NULL, 
	base_amount float, 
	tax_amount float, 
	tip_amount float,
	discount_amount float,
	invoice_dt TIMESTAMP NOT NULL,
	status varchar(32),
	bizid int8 NULL,
	currency varchar(32),
	currency_symbol varchar(32),
	channel varchar(32)
);
CREATE TABLE IF NOT EXISTS cmpn (objid bigserial NOT NULL, goal varchar(32), status varchar(32), 
title varchar(254) ,bizid int8 NOT NULL, end_dt TIMESTAMP NOT NULL, cmpn_type varchar(24) NOT NULL
);
CREATE TABLE IF NOT EXISTS cmpn_offer (objid bigserial NOT NULL, offer_sold int4, status varchar(32), cmpn_id int8 NOT NULL, currency varchar(34), currency_symbol varchar(34)
);
CREATE TABLE IF NOT EXISTS offer_purchase  (
	objid bigserial NOT NULL,
	offer_id int8 NOT NULL,
	offer_price float8 NOT NULL,
	tax float8 NOT NULL DEFAULT 0,
	currency varchar(32),
	currency_symbol varchar(32),
	channel varchar(32)
);
CREATE TABLE IF NOT EXISTS country (
	objid bigserial NOT NULL,
	iso_code varchar(32),
	currency_symbol varchar(32),
	currency varchar(32)
);
	
