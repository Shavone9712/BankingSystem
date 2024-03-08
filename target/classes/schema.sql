DROP TABLE IF EXISTS Customer;

DROP TABLE IF EXISTS Account;

create table Customer(
  ID INT NOT NULL AUTO_INCREMENT,
  name varchar(100) not null,
  PRIMARY KEY ( ID )
);

create table Account(
   ID BIGINT NOT NULL,
   custid INT NOT NULL,
   status INT NOT NULL DEFAULT 1,
   amount DOUBLE NOT NULL DEFAULT 0.00,
   PRIMARY KEY (ID),
   FOREIGN KEY (custid) REFERENCES Customer(ID)
);