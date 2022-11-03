INSERT INTO indv_master (objid, address_line1, address_line2, created_dt, city, country, email, first_name, last_name, fyndr_handle, is_business, ctry_code, state, postal_code, bizid, qrid, yob, gender, account_status) 
            VALUES (7L, 'addressline3', 'addressline3', '2022-09-03T10:37:30.00Z', 'city3', 'US', 'test@email3.com', 'firstName3', 'lastName3', 'fyndrHandle3', true, '+91', 'state3', '111013', 2, 123, '1902', 'M', 'ACTIVE');

INSERT INTO indv_master (objid, address_line1, address_line2, created_dt, city, country, email, first_name, last_name, fyndr_handle, is_business, ctry_code, state, postal_code, bizid, qrid, yob, gender, account_status) 
            VALUES (8L, 'addressline4', 'addressline4', '2022-09-03T10:37:30.00Z', 'city4', 'IN', 'test@email4.com', 'firstName4', 'lastName4', 'fyndrHandle4', false, '+91', 'state4', '111014', 5, 5, '1902', 'M', 'ACTIVE');

INSERT INTO indv_master (objid, address_line1, address_line2, created_dt, city, country, email, first_name, last_name, fyndr_handle, is_business, ctry_code, state, postal_code, bizid, qrid, yob, gender, account_status) 
            VALUES (9L, 'addressline5', 'addressline5', '2022-09-05T10:37:30.00Z', 'city5', 'US', 'test@email5.com', 'firstName5', 'lastName5', 'fyndrHandle5', false, '+91', 'state4', '111015', 6, 54, '1902', 'M', 'ACTIVE');

INSERT INTO indv_master (objid, address_line1, address_line2, created_dt, city, country, email, first_name, last_name, fyndr_handle, is_business, ctry_code, state, postal_code, bizid, qrid, yob, gender, account_status) 
            VALUES (10L, 'addressline6', 'addressline6', '2022-09-15T10:37:30.00Z', 'city6', 'US', 'test@email6.com', 'firstName6', 'lastName6', 'fyndrHandle6', false, '+91', 'state4', '111016', 7, 51, '1902', 'M', 'ACTIVE');
            
INSERT INTO biz_master (objid, biz_name, biz_type, website) 
            VALUES (2L, 'bizName1', 'food1', 'testwebsite1');

INSERT INTO biz_master (objid, biz_name, biz_type, website) 
            VALUES (5L, 'bizName2', 'food2', 'testwebsite2');
            
INSERT INTO biz_master (objid, biz_name, biz_type, website) 
            VALUES (6L, 'bizName3', 'food2', 'testwebsite3');

INSERT INTO biz_master (objid, biz_name, biz_type, website) 
            VALUES (7L, 'bizName4', 'food2', 'testwebsite4');
            
INSERT INTO invoice (objid, channel, invoice_dt, base_amount, tax_amount, discount_amount, tip_amount, status, bizid, currency, currency_symbol)
VALUES(1L, 'catalog', '2022-09-03T10:37:30.00Z', 1.00, 2.00, 3.00, 4.00, 'paid', 2, 'USD', '$');

INSERT INTO invoice (objid, channel, invoice_dt, base_amount, tax_amount, discount_amount, tip_amount, status, bizid, currency, currency_symbol)
VALUES(2L, 'offers', '2022-04-03T10:37:30.00Z', 50.00, 60.00, 70.00, 80.00, 'paid', 2, 'USD', '$');

INSERT INTO invoice (objid, channel, invoice_dt, base_amount, tax_amount, discount_amount, tip_amount, status, bizid, currency, currency_symbol)
VALUES(5L, 'offers', '2022-06-03T10:37:30.00Z', 30.00, 10.00, 5.00, 2.00, 'paid', 5, 'USD', '$');

INSERT INTO invoice (objid, channel, invoice_dt, base_amount, tax_amount, discount_amount, tip_amount, status, bizid, currency, currency_symbol)
VALUES(6L, 'catalog', '2021-09-03T10:37:30.00Z', 20.00, 5.00, 10.0, 5.00, 'paid', 6, 'USD', '$');

INSERT INTO invoice (objid, channel, invoice_dt, base_amount, tax_amount, discount_amount, tip_amount, status, bizid, currency, currency_symbol)
VALUES(3L, 'offers', '2022-09-13T10:37:30.00Z', 70.00, 10.00, 20.00, 30.00, 'paid', 7, 'USD', '$');

