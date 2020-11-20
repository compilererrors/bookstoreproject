CREATE TABLE BOOK(
  ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  TITLE VARCHAR(64) NOT NULL,
  AUTHOR VARCHAR(64) NOT NULL,
  PRICE DOUBLE NOT NULL,
  DATE VARCHAR(64) NOT NULL,
  THUMBNAIL VARCHAR(255) NOT NULL

);



CREATE TABLE CART(
  ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  BOOKID BIGINT,
  QTY BIGINT,
);


  create table users(
      username varchar(50) not null primary key,
      password varchar(50) not null,
      enabled boolean not null);

  create table authorities (
      username varchar(50) not null,
      authority varchar(50) not null,
      constraint fk_authorities_users foreign key(username) references users(username));
      create unique index ix_auth_username on authorities (username,authority);