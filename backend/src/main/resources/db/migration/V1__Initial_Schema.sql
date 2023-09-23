CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE EXTENSION IF NOT EXISTS postgis;

CREATE TABLE category (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name TEXT NOT NULL,
    CONSTRAINT category_name_uniq UNIQUE (name)
);

CREATE TABLE product (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name TEXT NOT NULL,
    description TEXT,
    category_id UUID NOT NULL,
    price NUMERIC NOT NULL,
    gps_coordinates geometry(Point, 4326) NOT NULL,
    views BIGINT NOT NULL DEFAULT 0,
    image TEXT,
    CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES category (id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE INDEX product_gps_coordinates_gist ON product USING gist(gps_coordinates);

CREATE TABLE price_history (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    product_id UUID NOT NULL,
    price NUMERIC NOT NULL,
    timestamp TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT (now() AT TIME ZONE 'UTC'),
    CONSTRAINT fk_price_history_product FOREIGN KEY (product_id) REFERENCES product (id)
        ON UPDATE CASCADE ON DELETE CASCADE
);
