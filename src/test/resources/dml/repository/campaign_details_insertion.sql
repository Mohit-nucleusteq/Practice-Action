
INSERT INTO biz_master (objid, biz_name, biz_type, website) 
            VALUES (101L, 'a', 'buisnessType', 'testwebsite1');

INSERT INTO biz_master (objid, biz_name, biz_type, website) 
            VALUES (102L, 'b', 'buisnessType', 'testwebsite2');
            
INSERT INTO biz_master (objid, biz_name, biz_type, website) 
            VALUES (103L, 'c', 'buisnessType2', 'testwebsite3');
            

            
INSERT INTO indv_master(objid, address_line1, address_line2, created_dt, city, country, email, first_name, last_name, fyndr_handle, is_business, ctry_code, state, postal_code, bizid, qrid, yob, gender, account_status) 
            VALUES (103L, 'addressline3', 'addressline3', '2022-09-03T10:37:30.00Z', 'city3', 'US', 'test@email3.com', 'firstName3', 'lastName3', 'fyndrHandle3', true, '+91', 'state3', '111013', 101L, 123, '1902', 'M', 'ACTIVE');

INSERT INTO indv_master(objid, address_line1, address_line2, created_dt, city, country, email, first_name, last_name, fyndr_handle, is_business, ctry_code, state, postal_code, bizid, qrid, yob, gender, account_status) 
            VALUES (104L, 'addressline4', 'addressline4', '2022-09-03T10:37:30.00Z', 'city4', 'US', 'test@email4.com', 'firstName4', 'lastName4', 'fyndrHandle4', false, '+91', 'state4', '111014', 102l, 5, '1902', 'M', 'SUSPENDED');
            
INSERT INTO indv_master(objid, address_line1, address_line2, created_dt, city, country, email, first_name, last_name, fyndr_handle, is_business, ctry_code, state, postal_code, bizid, qrid, yob, gender, account_status) 
            VALUES (105L, 'addressline5', 'addressline5', '2022-09-03T10:37:30.00Z', 'city4', 'US', 'test@email5.com', 'firstName5', 'lastName5', 'fyndrHandle5', false, '+91', 'state4', '111014', 103l, 5, '1902', 'M', 'SUSPENDED');

      
INSERT INTO offer_purchase (objid, offer_id, offer_price, tax, currency, currency_symbol, channel) VALUES(101, 201, 50, 4.7, 'USD', '$', 'a');
INSERT INTO offer_purchase (objid, offer_id, offer_price, tax, currency, currency_symbol, channel) VALUES(102, 202, 50, 4.3, 'USD', '$', 'a');
INSERT INTO offer_purchase (objid, offer_id, offer_price, tax, currency, currency_symbol, channel) VALUES(103, 203, 50, 4.7, 'USD', '$', 'a');

INSERT INTO cmpn_offer (objid, cmpn_id, offer_sold) VALUES(201, 1, 13);
INSERT INTO cmpn_offer (objid, cmpn_id, offer_sold) VALUES(202, 2, 14); 
INSERT INTO cmpn_offer (objid, cmpn_id, offer_sold) VALUES(203, 3, 15);