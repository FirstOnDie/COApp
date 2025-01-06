CREATE TABLE users (
       id SERIAL PRIMARY KEY,
       username VARCHAR(50) UNIQUE NOT NULL,
       password VARCHAR(100) NOT NULL,
       role VARCHAR(50) NOT NULL
);

INSERT INTO users (username, password, role)
VALUES ('user1', '$2a$10$V4b3MxWjAsUZhD/1kq/k.eKBLOaP05Uj1PazHl8RlbJNBZkXU09ZG', 'USER'); -- password: "password"