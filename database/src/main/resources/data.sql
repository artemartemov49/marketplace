CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(64),
    last_name  VARCHAR(64),
    email      VARCHAR(64)  NOT NULL UNIQUE,
    phone      VARCHAR(64)  NOT NULL UNIQUE,
    password   VARCHAR(128) NOT NULL,
    role       VARCHAR(16)
);

CREATE TABLE country
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL UNIQUE
);

CREATE TABLE address
(
    id            BIGSERIAL PRIMARY KEY,
    unit_number   VARCHAR(2),
    street_number VARCHAR(64)  NOT NULL,
    address_line1 VARCHAR(128) NOT NULL,
    address_line2 VARCHAR(128),
    city          VARCHAR(128) NOT NULL,
    region        VARCHAR(128) NOT NULL,
    postal_code   VARCHAR(10)  NOT NULL,
    country_id    INT REFERENCES country
);

CREATE TABLE payment_type
(
    id    SERIAL PRIMARY KEY,
    value VARCHAR(32) NOT NULL UNIQUE
);

CREATE TABLE user_payment_method
(
    id              BIGSERIAL PRIMARY KEY,
    user_id         BIGINT REFERENCES users ON DELETE CASCADE,
    payment_type_id INT REFERENCES payment_type ON DELETE CASCADE,
    provider        VARCHAR(64),
    account_number  INT,
    expiry_date     DATE,
    is_default      BOOLEAN DEFAULT FALSE
);

CREATE TABLE merchant
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(64) NOT NULL UNIQUE,
    description VARCHAR(512)
);


CREATE TABLE product
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(64) NOT NULL,
    description VARCHAR(512),
    category_id INT REFERENCES product_category,
    merchant_id INT REFERENCES merchant
);

CREATE TABLE product_item
(
    id         SERIAL PRIMARY KEY,
    product_id INT REFERENCES product ON DELETE CASCADE,
    quantity   INT,
    price      INT
);

CREATE TABLE product_image
(
    id              SERIAL PRIMARY KEY,
    title           VARCHAR(64),
    url             VARCHAR(64),
    product_item_id INT REFERENCES product_item
);

--  self-join Phone, Shoes, TV
CREATE TABLE product_category
(
    id                 SERIAL PRIMARY KEY,
    title              VARCHAR(64) NOT NULL UNIQUE,
    parent_category_id INT REFERENCES product_category
);

-- size, color, memory etc
CREATE TABLE variation
(
    id          SERIAL PRIMARY KEY,
    value       VARCHAR(64) NOT NULL UNIQUE,
    category_id INT REFERENCES product_category ON DELETE CASCADE
);

-- XL, Black, 1 gb.
CREATE TABLE variation_option
(
    id           SERIAL PRIMARY KEY,
    value        VARCHAR(64) NOT NULL UNIQUE,
    variation_id INT REFERENCES variation ON DELETE CASCADE

);

CREATE TABLE product_item_variation_option
(
    product_item_id     INT NOT NULL REFERENCES product_item ON DELETE CASCADE,
    variation_option_id INT NOT NULL REFERENCES variation_option ON DELETE CASCADE,
    UNIQUE (product_item_id, variation_option_id)
);

-- while cart. for example it contains (Milk, Ipad, Apple)
CREATE TABLE shopping_cart
(
    id      BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users ON DELETE CASCADE
);

-- cart item (Milk)
CREATE TABLE shopping_cart_item
(
    id              SERIAL PRIMARY KEY,
    cart_id         BIGINT NOT NULL REFERENCES shopping_cart ON DELETE CASCADE,
    product_item_id INT    NOT NULL REFERENCES product_item ON DELETE CASCADE,
    quantity        INT    NOT NULL
);


CREATE TABLE orders
(
    id                 BIGSERIAL PRIMARY KEY,
    user_id            BIGINT REFERENCES users ON DELETE CASCADE,
    date_created       DATE,
    payment_method_id  BIGINT REFERENCES user_payment_method,
    shipping_method_id INT REFERENCES shipping_method,
    order_total        INT,
    order_status       VARCHAR(64)
);

CREATE TABLE order_address
(
    id         BIGSERIAL PRIMARY KEY,
    order_id   BIGINT NOT NULL REFERENCES orders ON DELETE CASCADE,
    address_id BIGINT NOT NULL REFERENCES address ON DELETE CASCADE,
    is_default BOOLEAN DEFAULT FALSE
);

CREATE TABLE shipping_method
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(16) NOT NULL UNIQUE,
    price INT
);

CREATE TABLE order_status
(
    id     SERIAL PRIMARY KEY,
    status VARCHAR(16) NOT NULL UNIQUE
);

CREATE TABLE order_line
(
    id              BIGSERIAL PRIMARY KEY,
    product_item_id INT REFERENCES product_item ON DELETE CASCADE,
    order_id        BIGINT REFERENCES orders ON DELETE CASCADE,
    quantity        INT,
    price           INT

);

-- CREATE TABLE user_review
-- (
--     id               SERIAL PRIMARY KEY,
--     user_id          INT REFERENCES product_item,
--     order_product_id INT REFERENCES order_line ON DELETE CASCADE,
--     rating_value     INT,
--     comment          VARCHAR
--
-- );

-- CREATE TABLE promotion
-- (
--     id            SERIAL PRIMARY KEY,
--     title         VARCHAR(64),
--     description   VARCHAR(512),
--     discount_rate INT,
--     start_date    DATE,
--     end_date      DATE
-- );
--
-- CREATE TABLE promotion_category
-- (
--     category_id  INT NOT NULL REFERENCES product_category ON DELETE CASCADE,
--     promotion_id INT NOT NULL REFERENCES promotion ON DELETE CASCADE,
--     UNIQUE (category_id, promotion_id)
-- );