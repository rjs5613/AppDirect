--Product DDL
CREATE TABLE IF NOT EXISTS products
(
  id BIGINT auto_increment NOT NULL,
  created timestamp ,
  base_price numeric(19,4),
  description character varying(255),
  name character varying(255) NOT NULL,
  CONSTRAINT products_pkey PRIMARY KEY (id)
);

--Store DDL
CREATE TABLE IF NOT EXISTS stores
(
  id BIGINT auto_increment NOT NULL,
  created timestamp ,
  description character varying(255),
  name character varying(255) NOT NULL,
  CONSTRAINT stores_pkey PRIMARY KEY (id)
);

--Product Price Store
CREATE TABLE IF NOT EXISTS product_store_price
(
  id BIGINT auto_increment NOT NULL,
  created timestamp ,
  product_name character varying(255),
  product_price numeric(19,4),
  store_name character varying(255),
  product_id bigint,
  store_id bigint,
  CONSTRAINT product_store_price_pkey PRIMARY KEY (id),
  CONSTRAINT fk75ebva6e33ouqn1qd35xn5va8 FOREIGN KEY (store_id)
      REFERENCES stores (id),
  CONSTRAINT fkasuq4fug022hmugovlw4egs41 FOREIGN KEY (product_id)
      REFERENCES products (id)
);

--Product Pricing Info

CREATE TABLE IF NOT EXISTS product_price_info
(
  id BIGINT auto_increment NOT NULL,
  created timestamp ,
  average_price numeric(19,4),
  base_price numeric(19,4),
  count integer NOT NULL,
  description character varying(255),
  highest_price numeric(19,4),
  ideal_price numeric(19,4),
  lowest_price numeric(19,4),
  product_id bigint,
  product_name character varying(255),
  CONSTRAINT product_price_info_pkey PRIMARY KEY (id)
);



INSERT INTO products(created, base_price, description, name) VALUES (CURRENT_TIMESTAMP, 15000, 'Apple Product 5', 'iPhone 5s');
INSERT INTO products(created, base_price, description, name) VALUES (CURRENT_TIMESTAMP, 15000, 'Apple Product 6', 'iPhone 6');
INSERT INTO products(created, base_price, description, name) VALUES (CURRENT_TIMESTAMP, 15000, 'Apple Product 6s', 'iPhone 6s');
INSERT INTO products(created, base_price, description, name) VALUES (CURRENT_TIMESTAMP, 15000, 'Apple Product 7', 'iPhone 7');
INSERT INTO products(created, base_price, description, name) VALUES (CURRENT_TIMESTAMP, 15000, 'Apple Product 8', 'iPhone 8');
INSERT INTO products(created, base_price, description, name) VALUES (CURRENT_TIMESTAMP, 15000, 'Apple Product X', 'iPhone X');




INSERT INTO stores(created, description, name) VALUES ( CURRENT_TIMESTAMP, 'Apple Certified Store', 'Unicorn');
INSERT INTO stores(created, description, name) VALUES ( CURRENT_TIMESTAMP, 'Tata Store', 'Chroma');
INSERT INTO stores(created, description, name) VALUES ( CURRENT_TIMESTAMP, 'Reliance Store', 'Reliance Digital');
INSERT INTO stores(created, description, name) VALUES ( CURRENT_TIMESTAMP, 'Retail Store', 'Independence Electronics');
INSERT INTO stores(created, description, name) VALUES ( CURRENT_TIMESTAMP, 'Authorized Store', 'Vijay Sales');
INSERT INTO stores(created, description, name) VALUES ( CURRENT_TIMESTAMP, 'Retail Store', 'Keyour Electronics');




INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 1, 1, 14500 , 'iPhone 5s', 'Unicorn');
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 1, 2, 15500 , 'iPhone 5s', 'Chroma');
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 1, 3, 16000 , 'iPhone 5s', 'Reliance Digital');
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 1, 4, 14000 , 'iPhone 5s', 'Independence Electronics');
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 1, 5, 17000 , 'iPhone 5s', 'Vijay Sales');
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 1, 6, 13500 , 'iPhone 5s', 'Keyour Electronics');
    
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 2, 1, 24500 , 'iPhone 6', 'Unicorn');
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 2, 2, 25500 , 'iPhone 6', 'Chroma');
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 2, 3, 26000 , 'iPhone 6', 'Reliance Digital');
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 2, 4, 24000 , 'iPhone 6', 'Independence Electronics');
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 2, 5, 27000 , 'iPhone 6', 'Vijay Sales');
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 2, 6, 23500 , 'iPhone 6', 'Keyour Electronics');

    
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 3, 1, 34500 , 'iPhone 6s', 'Unicorn');
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 3, 2, 35500 , 'iPhone 6s', 'Chroma');
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 3, 3, 36000 , 'iPhone 6s', 'Reliance Digital');
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 3, 4, 34000 , 'iPhone 6s', 'Independence Electronics');
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 3, 5, 37000 , 'iPhone 6s', 'Vijay Sales');
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 3, 6, 33500 , 'iPhone 6s', 'Keyour Electronics');
    
    
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 4, 1, 44500 , 'iPhone 7', 'Unicorn');
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 4, 2, 45500 , 'iPhone 7', 'Chroma');
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 4, 3, 46000 , 'iPhone 7', 'Reliance Digital');
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 4, 4, 44000 , 'iPhone 7', 'Independence Electronics');
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 4, 5, 47000 , 'iPhone 7', 'Vijay Sales');
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 4, 6, 43500 , 'iPhone 7', 'Keyour Electronics');
    
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 5, 1, 54500 , 'iPhone 8', 'Unicorn');
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 5, 2, 55500 , 'iPhone 8', 'Chroma');
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 5, 3, 56000 , 'iPhone 8', 'Reliance Digital');
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 5, 4, 54000 , 'iPhone 8', 'Independence Electronics');
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 5, 5, 57000 , 'iPhone 8', 'Vijay Sales');
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 5, 6, 53500 , 'iPhone 8', 'Keyour Electronics');

    
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 6, 1, 64500 , 'iPhone X', 'Unicorn');
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 6, 2, 65500 , 'iPhone X', 'Chroma');
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 6, 3, 66000 , 'iPhone X', 'Reliance Digital');
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 6, 4, 64000 , 'iPhone X', 'Independence Electronics');
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 6, 5, 67000 , 'iPhone X', 'Vijay Sales');
INSERT INTO product_store_price(created, product_id,store_id,product_price,product_name, store_name)
    VALUES (CURRENT_TIMESTAMP, 6, 6, 63500 , 'iPhone X', 'Keyour Electronics');

