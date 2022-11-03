INSERT INTO indv_master(objid, address_line1, address_line2, created_dt, city, country, email, first_name, last_name, fyndr_handle, is_business, ctry_code, state, postal_code, bizid, qrid, yob, gender, account_status) 
            VALUES (1L, 'addressline1', 'addressline2', '2022-09-03T10:37:30.00Z', 'city1', 'country1', 'test@email1.com', 'firstName1', 'lastName1', 'fyndrHandle1', false, '+91', 'state1', '111011', null, 123, '1902', 'M', 'ACTIVE');

INSERT INTO indv_master(objid, address_line1, address_line2, created_dt, city, country, email, first_name, last_name, fyndr_handle, is_business, ctry_code, state, postal_code, bizid, qrid, yob, gender, account_status) 
            VALUES (5L, 'addressline2', 'addressline2', '2022-09-03T10:37:30.00Z', 'city2', 'country2', 'test@email2.com', 'firstName2', 'lastName2', 'fyndrHandle2', true, '+91', 'state2', '111012', 1, 123, '1902', 'M', 'INACTIVE');

INSERT INTO indv_master(objid, address_line1, address_line2, created_dt, city, country, email, first_name, last_name, fyndr_handle, is_business, ctry_code, state, postal_code, bizid, qrid, yob, gender, account_status) 
            VALUES (7L, 'addressline3', 'addressline3', '2022-09-03T10:37:30.00Z', 'city3', 'country3', 'test@email3.com', 'firstName3', 'lastName3', 'fyndrHandle3', true, '+91', 'state3', '111013', 2, 123, '1902', 'M', 'ACTIVE');

INSERT INTO indv_master(objid, address_line1, address_line2, created_dt, city, country, email, first_name, last_name, fyndr_handle, is_business, ctry_code, state, postal_code, bizid, qrid, yob, gender, account_status) 
            VALUES (8L, 'addressline4', 'addressline4', '2022-09-03T10:37:30.00Z', 'city4', 'country4', 'test@email4.com', 'firstName4', 'lastName4', 'fyndrHandle4', false, '+91', 'state4', '111014', null, 123, '1902', 'M', 'SUSPENDED');

INSERT INTO indv_master(objid, address_line1, address_line2, created_dt, city, country, email, first_name, last_name, fyndr_handle, is_business, ctry_code, state, postal_code, bizid, qrid, yob, gender, account_status) 
            VALUES (9L, 'addressline5', 'addressline5', '2022-09-03T10:37:30.00Z', 'city5', 'country5', 'test@email5.com', 'firstName5', 'lastName5', 'fyndrHandle5', false, '+91', 'state5', '111015', null, 123, '1902', 'M', 'DELETED');

INSERT INTO biz_master (objid, biz_name, biz_type, website) 
            VALUES (1L, 'bizName1', 'food1', 'testwebsite1');

INSERT INTO biz_master (objid, biz_name, biz_type, website) 
            VALUES (2L, 'bizName2', 'food2', 'testwebsite2');

INSERT INTO code_master (objid)
            VALUES (10L);

INSERT INTO cmpn(objid, goal, status)
            VALUES(10L, 'all', 'active');
