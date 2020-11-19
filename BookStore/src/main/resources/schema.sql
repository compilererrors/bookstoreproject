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



