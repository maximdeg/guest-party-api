## Database test script 

```
create database db_guestparty_dev;
CREATE TABLE `db_guestparty_dev`.`guest` (
  `id_guest` INT ZEROFILL NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `invited` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_guest`));
INSERT INTO guest (name, invited) VALUES('Juan', 'true');
INSERT INTO guest (name, invited) VALUES('Eduardo', 'false');
INSERT INTO guest (name, invited) VALUES('Josefina', 'true');
INSERT INTO guest (name, invited) VALUES('Elsa', 'false');
INSERT INTO guest (name, invited) VALUES('Pablo', 'true');

```
