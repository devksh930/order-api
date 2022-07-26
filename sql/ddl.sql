create table order_info
(
    order_number  varchar(255) not null
        primary key,
    created_date  datetime(6)  null,
    modified_date datetime(6)  null,
    payment_date  datetime(6)  null,
    account_id    bigint       null,
    product_id    bigint       null,
    constraint FK_orderInfo_account
        foreign key (account_id) references account (id),
    constraint FK_orderInfo_product
        foreign key (product_id) references product (product_id)
)
    charset = utf8mb4;

create table account
(
    id             bigint auto_increment
        primary key,
    account_gender varchar(255) null,
    account_role   varchar(255) null,
    email          varchar(100) not null,
    name           varchar(20)  not null,
    nickname       varchar(30)  not null,
    password       varchar(255) not null,
    phone_number   varchar(20)  not null,
    constraint UK_account_email       unique (email)
)
    charset = utf8mb4;

create table product
(
    product_id    bigint auto_increment
        primary key,
    created_date  datetime(6)  null,
    modified_date datetime(6)  null,
    product_name  varchar(100) not null
)
    charset = utf8mb4;

