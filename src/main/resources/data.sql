INSERT INTO PRODUCT_GROUP (NAME) VALUES ('Арматура');
INSERT INTO PRODUCT_GROUP (NAME) VALUES ('Трубы');
INSERT INTO PRODUCT_GROUP (NAME) VALUES ('Металлические листы');
INSERT INTO PRODUCT_GROUP (NAME) VALUES ('Товары народного потребления');

INSERT INTO products (ARTICLE, COUNT, NAME, GROUP_ID, PRICE) VALUES ('О-82', 57, 'Арматура стальная 2м 10мм', 1, 36.78);
INSERT INTO products (ARTICLE, COUNT, NAME, GROUP_ID, PRICE) VALUES ('А810', 57, 'Труба стальная 80мм', 2, 15.4);
INSERT INTO products (ARTICLE, COUNT, NAME, GROUP_ID, PRICE) VALUES ('Д1521-67', 57, 'Лист стальной 5мм', 3, 9.31);
INSERT INTO products (ARTICLE, COUNT, NAME, GROUP_ID, PRICE) VALUES ('А195-67', 57, 'Подставка для цветов 50см', 4, 5.55);


INSERT INTO customer (ADDRESS, IBAN, INN, NAME, PHONE) VALUES ('Москва, 10-я парковая улица, д.15 строение 3, 839218', 'BY06ALFA30120000120200000000', '2771829', 'ООО Мосстрой', '79158397718');
INSERT INTO customer (ADDRESS, IBAN, INN, NAME, PHONE) VALUES ('Минск, Проспект Димитрова, д.91, 839218', 'BY06ALFA3012000012027326100', '322345', 'ОАО ТендерГрупп', '375444478392');
INSERT INTO customer (ADDRESS, IBAN, INN, NAME, PHONE) VALUES ('Витебск, Проспект Мира, д. 74, 9239384', 'BY06INVEST1208382482349800291', '2771232', 'ОАО Трест 5', '375298432788');

INSERT INTO company (ADDRESS, IBAN, INN, NAME, PHONE) VALUES ('Могилев, улица курако,д. 28, 212020', 'BY06ALFA3012000743839800', '8291032', 'ОАО Могилевский металлургический завод', '375290001122')