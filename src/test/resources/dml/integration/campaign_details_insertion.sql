
INSERT INTO biz_master (objid, biz_name, biz_type, website) 
            VALUES (101L, 'a', 'buisnessType', 'testwebsite1');

INSERT INTO biz_master (objid, biz_name, biz_type, website) 
            VALUES (102L, 'd', 'buisnessType', 'testwebsite2');

INSERT INTO biz_master (objid, biz_name, biz_type, website) 
            VALUES (103L, 'b', 'buisnessType1', 'testwebsite3');

INSERT INTO biz_master (objid, biz_name, biz_type, website) 
            VALUES (104L, 'c', 'buisnessType2', 'testwebsite4');
          
INSERT INTO indv_master(objid, address_line1, address_line2, created_dt, city, country, email, first_name, last_name, fyndr_handle, is_business, ctry_code, state, postal_code, bizid, qrid, yob, gender, account_status) 
            VALUES (103L, 'addressline3', 'addressline3', '2022-09-03T10:37:30.00Z', 'city3', 'US', 'test@email3.com', 'firstName3', 'lastName3', 'fyndrHandle3', true, '+91', 'state3', '101l', 101L, 123, '1902', 'M', 'ACTIVE');

INSERT INTO indv_master(objid, address_line1, address_line2, created_dt, city, country, email, first_name, last_name, fyndr_handle, is_business, ctry_code, state, postal_code, bizid, qrid, yob, gender, account_status) 
            VALUES (104L, 'addressline4', 'addressline4', '2022-09-03T10:37:30.00Z', 'city4', 'IN', 'test@email4.com', 'firstName4', 'lastName4', 'fyndrHandle4', false, '+91', 'state4', '102l', 102L, 5, '1902', 'M', 'SUSPENDED');
          
INSERT INTO indv_master(objid, address_line1, address_line2, created_dt, city, country, email, first_name, last_name, fyndr_handle, is_business, ctry_code, state, postal_code, bizid, qrid, yob, gender, account_status) 
            VALUES (105L, 'addressline5', 'addressline5', '2022-09-03T10:37:30.00Z', 'city3', 'US', 'test@email6.com', 'firstName5', 'lastName5', 'fyndrHandle5', true, '+91', 'state3', '101l', 103L, 123, '1902', 'M', 'ACTIVE');

INSERT INTO indv_master(objid, address_line1, address_line2, created_dt, city, country, email, first_name, last_name, fyndr_handle, is_business, ctry_code, state, postal_code, bizid, qrid, yob, gender, account_status) 
            VALUES (106L, 'addressline6', 'addressline6', '2022-09-03T10:37:30.00Z', 'city4', 'US', 'test@email7.com', 'firstName6', 'lastName6', 'fyndrHandle6', false, '+91', 'state4', '102l', 104L, 5, '1902', 'M', 'SUSPENDED');
      
INSERT INTO offer_purchase (objid, offer_id, offer_price, tax) VALUES(101, 201, 50, 4.7);
INSERT INTO offer_purchase (objid, offer_id, offer_price, tax) VALUES(102, 202, 50, 4.3);
INSERT INTO offer_purchase (objid, offer_id, offer_price, tax) VALUES(103, 203, 50, 4.7);
INSERT INTO offer_purchase (objid, offer_id, offer_price, tax) VALUES(104, 204, 50, 4.3);

INSERT INTO cmpn_offer (objid, offer_sold, status, cmpn_id) VALUES(201, 1, 'active', 1);
INSERT INTO cmpn_offer (objid, offer_sold, status, cmpn_id) VALUES(202, 1, 'active', 2);
INSERT INTO cmpn_offer (objid, offer_sold, status, cmpn_id) VALUES(203, 1, 'active', 3);
INSERT INTO cmpn_offer (objid, offer_sold, status, cmpn_id) VALUES(204, 1, 'active', 4);

INSERT INTO cmpn (objid, goal, status, title, bizid, end_dt, cmpn_type) values (1, 'goal', 'active', 'campaignName', 101l, '2022-09-03T10:37:30.00Z', 'campaignType');
INSERT INTO cmpn (objid, goal, status, title, bizid, end_dt, cmpn_type) values (2, 'goal', 'active', 'campaignName1', 102l, '2022-09-03T10:37:30.00Z', 'campaignType');

INSERT INTO cmpn (objid, goal, status, title, bizid, end_dt, cmpn_type) values (3, 'goal', 'active', 'campaignName2', 103l, '2022-09-03T10:37:30.00Z', 'campaignType');
INSERT INTO cmpn (objid, goal, status, title, bizid, end_dt, cmpn_type) values (4, 'goal', 'active', 'campaignName3', 104l, '2022-09-03T10:37:30.00Z', 'campaignType');

INSERT INTO country (objid, currency, currency_symbol, iso_code) values (103, 'USD', '$', 'US');
INSERT INTO country (objid, currency, currency_symbol, iso_code) values (104, 'INR', '₹', 'IN');
