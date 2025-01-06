CREATE TABLE users (
       id SERIAL PRIMARY KEY,
       username VARCHAR(50) UNIQUE NOT NULL,
       password VARCHAR(100) NOT NULL,
       role VARCHAR(50) NOT NULL,
       refresh_token VARCHAR(255)
);

INSERT INTO users (username, password, role)
VALUES ('Admin', '$2a$10$XN04x/DKANQaVmE3.BI7dOxpT5feKn/ezhd.R0cnNkhtzNTV03GOK', 'USER','');