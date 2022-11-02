INSERT INTO invoice (objid, tax_amount, base_amount, status, channel, tip_amount, discount_amount, invoice_dt, bizid, currency, currency_symbol)
values (1L, 2.01, 20.3, 'paid', 'offers', 4.3, 1.2, '2022-09-03T10:37:30.00Z', 9L, 'US', '#');

INSERT INTO invoice (objid, tax_amount, base_amount, status, channel, tip_amount, discount_amount, invoice_dt, bizid, currency, currency_symbol)
values (2L, 1.01, 30.20, 'paid', 'promo', 1.01, 2.00, '2022-09-03T10:37:30.00Z', 8L, 'US', '#');

INSERT INTO invoice (objid, tax_amount, base_amount, status, channel, tip_amount, discount_amount, invoice_dt, bizid, currency, currency_symbol)
values (3L, 2.120, 200.3, 'paid', 'interaction', 5.3, 1.6, '2022-09-03T10:37:30.00Z', 7L, 'US', '#');

INSERT INTO invoice (objid, tax_amount, base_amount, status, channel, tip_amount, discount_amount, invoice_dt, bizid, currency, currency_symbol)
values (4L, 8.00, 568.300, 'paid', 'catalog', 10.3, 20.50, '2022-09-03T10:37:30.00Z', 8L,'US', '#');

INSERT INTO country (objid, iso_code, currency_symbol, currency)
values (12L, 'US', '#', 'US');

