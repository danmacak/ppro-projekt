SET SQL_SAFE_UPDATES = 0;

INSERT INTO address VALUES (1, 'HK', '63', 'Nakládalova třída', '50012');
INSERT INTO address VALUES (2, 'HK', 345, 'Kujónova street', 34444);

INSERT INTO user VALUES ('buh', 'buh@gmail.com', true, 'Jesus', '1234', 'Christ', '696969696', 1);
INSERT INTO user VALUES ('cook1', 'cook1@gmail.com', 1, 'Honza', 1234, 'Kuchař', 123456789, 2);
INSERT INTO user VALUES ('chef1', 'chef1@gmail.com', 1, 'Honza', 1234, 'Šéfkuchař', 123456789, 2);

INSERT INTO dinnertable VALUES (1, 4); 

INSERT INTO booking VALUES (1, '2014-12-12', 18, 22, 'buh', 1);

INSERT INTO delivery VALUES ('Osobní odběr', 'Restaurant Gersthálka', 0.00, '777666555');
INSERT INTO delivery VALUES ('Rozvoz do domu', 'DameJidlo s.r.o.', 50.00, '741741741');

INSERT INTO customer_order VALUES (1, '2014-08-08', 350.50, 'buh', 'Osobní odběr');

INSERT INTO role VALUES ('ROLE_ADMIN');
INSERT INTO role VALUES ('ROLE_USER');
INSERT INTO role VALUES ('ROLE_COOK');
INSERT INTO role VALUES ('ROLE_WAITER');
INSERT INTO role VALUES ('ROLE_CHEF');

INSERT INTO user_roles VALUES ('buh', 'ROLE_USER');
INSERT INTO user_roles VALUES ('cook1', 'ROLE_COOK');

INSERT INTO dishgeneral VALUES ('1', 'MAIN', '120', '470', 'Svickova', '220.50', '120', '38');
INSERT INTO dishgeneral VALUES ('2', 'DRINK', '35', '120', 'Cocacola', '20.20', '36', '24');
INSERT INTO dishgeneral VALUES ('3', 'MAIN', '10', '270', 'Rostena', '285.50', '140', '30');

INSERT INTO dishloc VALUES ('cs', '1', 'Svíčková výtečná za přihlížení Fapoleona', 'Svíčková na smetaně');
INSERT INTO dishloc VALUES ('en', '1', 'Swizckowa', 'Svickova');
INSERT INTO dishloc VALUES ('cs', '2', 'Coca cola výtečná za přihlížení Fapoleona', 'Cola na smetaně');
INSERT INTO dishloc VALUES ('en', '2', 'Coca', 'Cocs');
INSERT INTO dishloc VALUES ('cs', '3', 'Roštěná podávaná za skučení kujónů', 'Kujóní roštěná');
INSERT INTO dishloc VALUES ('en', '3', 'Roastbeaf - best while farting', 'Roastbeaf');

INSERT INTO ingredient_general VALUES (1, 15, 26, 12.50, 15, 78, 'VEGETABLE');
INSERT INTO ingredient_general VALUES (2, 23, 45, 23.40, 34, 34, 'VEGETABLE');
INSERT INTO ingredient_general VALUES (3, 73, 75, 10.35, 14, 21, 'VEGETABLE');
INSERT INTO ingredientloc VALUES ('cs', 1, 'Mrkev');
INSERT INTO ingredientloc VALUES ('en', 1, 'Carrot');
INSERT INTO ingredientloc VALUES ('cs', 2, 'Brambory');
INSERT INTO ingredientloc VALUES ('en', 2, 'Potatos');
INSERT INTO ingredientloc VALUES ('cs', 3, 'Celer');
INSERT INTO ingredientloc VALUES ('en', 3, 'Celery');

INSERT INTO shift VALUES (1, 10, 22, 'THURSDAY');
INSERT INTO shift VALUES (2, 10, 22, 'TUESDAY');
INSERT INTO shift VALUES (3, 6, 20, 'WEDNESDAY');

INSERT INTO user_shift VALUES ('cook1', 1);
INSERT INTO user_shift VALUES ('cook1', 1);
INSERT INTO user_shift VALUES ('cook1', 1);


