INSERT INTO users (username, password) VALUES
('utilisateur1', 'motdepasse1'),
('utilisateur2', 'motdepasse2'),
('utilisateur3', 'motdepasse3');

INSERT INTO threads (title, owner_id) VALUES
('Discussion 1', 1),
('Discussion 2', 2),
('Discussion 3', 3);

INSERT INTO subscriptions (user_id, thread_id) VALUES
(1, 1),
(2, 1),
(3, 2);

INSERT INTO posts (content, author_id, thread_id) VALUES
                                                      ('Bienvenue dans ce nouveau fil de discussion !', 1, 1),
                                                      ('Salut !', 2, 1);

INSERT INTO likes (user_id, post_id) VALUES
(1, 1),
(2, 2);
