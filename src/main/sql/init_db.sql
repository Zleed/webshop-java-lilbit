--
-- PostgresSQL database dump
--

-- Dumped from database version 9.5.6
-- Dumped by pg_dump version 9.5.6

ALTER TABLE IF EXISTS ONLY public.product DROP CONSTRAINT IF EXISTS pk_product_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.product DROP CONSTRAINT IF EXISTS fk_product_category_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.product DROP CONSTRAINT IF EXISTS fk_supplier_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.product_category DROP CONSTRAINT IF EXISTS pk_product_category_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.supplier DROP CONSTRAINT IF EXISTS pk_supplier_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.users DROP CONSTRAINT IF EXISTS pk_hash CASCADE;


DROP TABLE IF EXISTS public.product;
DROP SEQUENCE IF EXISTS public.product_id_seq;
CREATE TABLE product (
    id serial NOT NULL,
    name varchar(30),
    description text,
    price float,
    currency varchar(4),
    product_category_id integer,
    supplier_id integer
);


DROP TABLE IF EXISTS public.product_category;
DROP SEQUENCE IF EXISTS public.product_category_id_seq;
CREATE TABLE product_category (
    id serial NOT NULL,
    name varchar(30),
    description text,
    department text
);


DROP TABLE IF EXISTS public.supplier;
DROP SEQUENCE IF EXISTS public.supplier_id_seq;
CREATE TABLE supplier (
    id serial NOT NULL,
    name varchar(30),
    description text
);

DROP TABLE IF EXISTS public.users;
DROP SEQUENCE IF EXISTS public.hash_seq;
CREATE TABLE users
(
    name varchar(50),
    email varchar(255),
    hash varchar(60)
);


ALTER TABLE ONLY product
    ADD CONSTRAINT pk_product_id PRIMARY KEY (id);

ALTER TABLE ONLY product_category
    ADD CONSTRAINT pk_product_category_id PRIMARY KEY (id);

ALTER TABLE ONLY supplier
    ADD CONSTRAINT pk_supplier_id PRIMARY KEY (id);

ALTER TABLE ONLY product
    ADD CONSTRAINT fk_product_category_id FOREIGN KEY (product_category_id) REFERENCES product_category(id);

ALTER TABLE ONLY product
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (supplier_id) REFERENCES supplier(id);

ALTER TABLE ONLY users
    ADD CONSTRAINT pk_hash PRIMARY KEY (hash);


-- INSERT INTO product VALUES (0, 'ischler', 500);
-- INSERT INTO product VALUES (1, 'túrós rétes', 470);
-- INSERT INTO product VALUES (2, 'csokitorta szelet', 540);
-- INSERT INTO product VALUES (3, 'pite', 320);
-- INSERT INTO product VALUES (4, 'mignon  ', 470);
-- SELECT pg_catalog.setval('product_id_seq', 1, true);
--
-- INSERT  INTO  orders VALUES  (0, 'kovács béla', '305139622', 'asd@asd.com','place for text message', '2019-06-22', '2019-06-26');
-- INSERT  INTO  orders VALUES  (1, 'varga hajnalka', '708132276', 'baguvix@gmul.com', 'place for text message', '2019-06-24', '2019-06-28');
-- SELECT pg_catalog.setval('orders_id_seq', 1, true);
--
--
-- INSERT  INTO  order_details VALUES  (0, 0, 13);
-- INSERT  INTO  order_details VALUES  (0, 1, 23);
-- INSERT  INTO  order_details VALUES  (0, 3, 2);
-- INSERT  INTO  order_details VALUES  (1, 1, 3);
-- INSERT  INTO  order_details VALUES  (1, 0, 8);
-- INSERT  INTO  order_details VALUES  (1, 4, 15);
-- INSERT  INTO  order_details VALUES  (1, 2, 2);