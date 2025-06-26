show databases;

CREATE DATABASE belajar_spring_data_jpa;

USE belajar_spring_data_jpa;

# table categories
CREATE TABLE categories(
	id 		BIGINT  NOT NULL AUTO_INCREMENT,
	name 	VARCHAR(100) NOT NULL,
	PRIMARY KEY (id)
)ENGINE=InnoDb;

DESC categories;

# table products
CREATE TABLE products(
	id 		BIGINT NOT NULL AUTO_INCREMENT,
	name 	VARCHAR(100) NOT NULL,
	price 	BIGINT NOT NULL,
	category_id BIGINT NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT fk_product_categories
	FOREIGN KEY (category_id) REFERENCES categories(id)
)ENGINE=innoDB;

DESC products;

# alter table categories
ALTER TABLE categories
	ADD created_date TIMESTAMP;

ALTER TABLE categories
	ADD last_modified_date TIMESTAMP;
