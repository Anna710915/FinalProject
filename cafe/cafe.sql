CREATE DATABASE cafe;
USE cafe;

CREATE TABLE users(
	user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	login VARCHAR(16),
	user_password VARCHAR(40),
	email VARCHAR(40),
	phone INT,
	birthday DATE,
	discount_id BIGINT,
	state_id BIGINT,
	role_id BIGINT,
CONSTRAINT user_state_key
FOREIGN KEY (state_id) REFERENCES user_state (state_id) 
ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT user_role_key
FOREIGN KEY (role_id) REFERENCES user_role (role_id)
ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT user_discount_key
FOREIGN KEY (discount_id) REFERENCES personal_discounts (discount_id)
ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE orders(
	order_id BIGINT PRIMARY KEY AUTO_INCREMENT,
	order_date TIMESTAMP,
	order_state ENUM('new','processing','cancelled','received','completed'),
	type_payment ENUM('cash','card'),
	user_comment VARCHAR(200),
	user_id BIGINT,
	address VARCHAR(100),
	total_cost DECIMAL(8,2),
CONSTRAINT user_key
FOREIGN KEY (user_id) REFERENCES users (user_id)
ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE menu(
	food_id BIGINT PRIMARY KEY AUTO_INCREMENT,
	name_food VARCHAR(50),
	picture_path VARCHAR(200),
	composition VARCHAR(200),
	weight DECIMAL(8,2),
	calories DECIMAL(8,2),
	cooking_time TIME,
	discount DECIMAL(3,2),
	price DECIMAL(8,2),
	section_id BIGINT,
	is_accessible BOOL,
CONSTRAINT section_key
FOREIGN KEY (section_id) REFERENCES sections (section_id)
ON UPDATE CASCADE ON DELETE CASCADE
);
 
CREATE TABLE orders_menu(
	order_id BIGINT,
	food_id BIGINT,
	dish_number INT,
CONSTRAINT order_menu_key
FOREIGN KEY (order_id) REFERENCES orders(order_id)
ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT food_key
FOREIGN KEY (food_id) REFERENCES menu(food_id)
ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE user_state(
	state_id BIGINT PRIMARY KEY AUTO_INCREMENT,
	state ENUM('new','active','blocked','unblocked')
);

CREATE TABLE user_role(
	role_id BIGINT PRIMARY KEY AUTO_INCREMENT,
	role_name ENUM('client','admin', 'guest')
);

CREATE TABLE personal_discounts(
	discount_id BIGINT PRIMARY KEY AUTO_INCREMENT,
	discount DECIMAL(3,2),
	year_orders INT
);

CREATE TABLE sections(
	section_id BIGINT PRIMARY KEY AUTO_INCREMENT,
	section_name VARCHAR(20),
	is_accessible BOOL
);