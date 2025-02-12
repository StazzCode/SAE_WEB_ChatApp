DROP TABLE IF EXISTS messages CASCADE;
DROP TABLE IF EXISTS likes CASCADE;
DROP TABLE IF EXISTS subscriptions CASCADE;
DROP TABLE IF EXISTS comments CASCADE;
DROP TABLE IF EXISTS threads CASCADE;
DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users (
    pk_user SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE threads (
    pk_thread SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    content TEXT NOT NULL,
    fk_user INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_user) REFERENCES users(pk_user) ON DELETE CASCADE
);

CREATE TABLE comments (
    pk_comment SERIAL PRIMARY KEY,
    content TEXT NOT NULL,
    fk_user INT,
    fk_thread INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_user) REFERENCES users(pk_user) ON DELETE CASCADE,
    FOREIGN KEY (fk_thread) REFERENCES threads(pk_thread) ON DELETE CASCADE
);

CREATE TABLE subscriptions (
    pk_subscription SERIAL PRIMARY KEY,
    fk_user INT,
    fk_thread INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_user) REFERENCES users(pk_user) ON DELETE CASCADE,
    FOREIGN KEY (fk_thread) REFERENCES threads(pk_thread) ON DELETE CASCADE
);

CREATE TABLE likes (
    pk_like SERIAL PRIMARY KEY,
    fk_user INT,
    fk_comment INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_user) REFERENCES users(pk_user) ON DELETE CASCADE,
    FOREIGN KEY (fk_comment) REFERENCES comments(pk_comment) ON DELETE CASCADE
);

CREATE TABLE messages (
    pk_message SERIAL PRIMARY KEY,
    fk_sender INT,
    fk_receiver INT,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_sender) REFERENCES users(pk_user) ON DELETE CASCADE,
    FOREIGN KEY (fk_receiver) REFERENCES users(pk_user) ON DELETE CASCADE
);