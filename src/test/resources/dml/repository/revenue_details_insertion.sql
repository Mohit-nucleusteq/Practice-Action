INSERT INTO indv_master(objid, address_line1, address_line2, created_dt, city, country, email, first_name, last_name, fyndr_handle, is_business, ctry_code, state, postal_code, bizid, qrid, yob, gender, account_status) 
            VALUES (7L, 'addressline3', 'addressline3', '2022-09-03T10:37:30.00Z', 'city3', 'US', 'test@email3.com', 'firstName3', 'lastName3', 'fyndrHandle3', true, '+91', 'state3', '111013', 2, 123, '1902', 'M', 'ACTIVE');

INSERT INTO indv_master(objid, address_line1, address_line2, created_dt, city, country, email, first_name, last_name, fyndr_handle, is_business, ctry_code, state, postal_code, bizid, qrid, yob, gender, account_status) 
            VALUES (8L, 'addressline4', 'addressline4', '2022-09-03T10:37:30.00Z', 'city4', 'US', 'test@email4.com', 'firstName4', 'lastName4', 'fyndrHandle4', false, '+91', 'state4', '111014', 5, 5, '1902', 'M', 'SUSPENDED');

INSERT INTO biz_master (objid, biz_name, biz_type, website) 
            VALUES (2L, 'bizName1', 'food1', 'testwebsite1');

INSERT INTO biz_master (objid, biz_name, biz_type, website) 
            VALUES (5L, 'bizName2', 'food2', 'testwebsite2');