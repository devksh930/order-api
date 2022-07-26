
INSERT INTO account (id, account_gender, account_role, email, name, nickname, password, phone_number) VALUES (1, 'MALE', 'ROLE_ADMIN', 'test@test.com', '김성호', 'asdfasdf', '$2a$10$AnH.cCq/2M3K9ZE98IraoeK0SPulxLIHqxyZBdU.cxc2jcTMQY9VS', '123123123123124');
INSERT INTO account (id, account_gender, account_role, email, name, nickname, password, phone_number) VALUES (2, 'MALE', 'ROLE_USER', 'test2@test.com', '홍길동', 'hong', '$2a$10$V4OGddTglosP2JCAOboBVeK25OUYSIsUS5zXNtjbN2wONDPtbVpNW', '123123123123124');

INSERT INTO product (product_id, created_date, modified_date, product_name) VALUES (1, '2022-07-26 23:13:10.615148', '2022-07-26 23:13:10.615148', '??‍?노트북');
INSERT INTO product (product_id, created_date, modified_date, product_name) VALUES (2, '2022-07-26 23:13:37.533144', '2022-07-26 23:13:37.533144', '?컴퓨터');

INSERT INTO order_info (order_number, created_date, modified_date, payment_date, account_id, product_id) VALUES ('5ufrdpE8KkjAjfX00slT', '2022-07-26 23:14:15.615482', '2022-07-26 23:14:48.096251', '2022-07-26 23:14:48.095833', 1, 2);
INSERT INTO order_info (order_number, created_date, modified_date, payment_date, account_id, product_id) VALUES ('XgnPuri3GGqhOpHi1dbf', '2022-07-26 23:14:10.568347', '2022-07-26 23:14:10.568347', null, 1, 1);

