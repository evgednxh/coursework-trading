SHOW SCHEMAS;

CREATE TABLE payment_info
(
    id          INT     NOT NULL AUTO_INCREMENT,
    card_number INT(16) NOT NULL,
    balance     INT     NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user
(
    id           INT                               NOT NULL AUTO_INCREMENT,
    username     VARCHAR(100)                      NOT NULL,
    email        VARCHAR(40)                       NOT NULL,
    password     VARCHAR(40)                       NOT NULL,
    account_type ENUM ('Investor','BusinessOwner') NOT NULL,
    access_token VARCHAR(10),
    payment_id   INT,
    PRIMARY KEY (id),
    CONSTRAINT fk_user_id FOREIGN KEY (payment_id) REFERENCES payment_info (id)
);

# needs normalization
CREATE TABLE stock
(
    id          INT         NOT NULL AUTO_INCREMENT,
    seller_id   INT         NOT NULL,
    trade_name  VARCHAR(40) NOT NULL,
    market_name VARCHAR(40) NOT NULL,
    trade_price INT(40)     NOT NULL,
    amount      INT(40)     NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_seller_id FOREIGN KEY (seller_id) REFERENCES user (id)
);

# needs normalization
CREATE TABLE sold_stock
(
    id            INT         NOT NULL AUTO_INCREMENT,
    investor_id   INT         NOT NULL,
    seller_id     INT         NOT NULL,
    trade_name    VARCHAR(40) NOT NULL,
    market_name   VARCHAR(40) NOT NULL,
    trade_price   INT(40)     NOT NULL,
    bought_amount INT(40)     NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_sold_stock_investor_id FOREIGN KEY (investor_id) REFERENCES user (id),
    CONSTRAINT fk_sold_stock_seller_id FOREIGN KEY (seller_id) REFERENCES user (id)
);

INSERT INTO user(id, username, email, password, account_type, access_token)
VALUES (100, 'hello', 'email@gmail.com', 'password', 'Investor', 'qwertyuiop');
INSERT INTO stock(seller_id, trade_name, market_name, trade_price, amount)
VALUES (100, 'TESLA', 'Tesla', 5000, 60);
INSERT INTO stock(seller_id, trade_name, market_name, trade_price, amount)
VALUES (100, 'ALPHB', 'Alphabet', 30000, 100);
INSERT INTO stock(seller_id, trade_name, market_name, trade_price, amount)
VALUES (100, 'APPLE', 'Apple', 900, 43);