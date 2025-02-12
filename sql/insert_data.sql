INSERT INTO users (username, password) VALUES
('utilisateur1', 'motdepasse1'),
('utilisateur2', 'motdepasse2'),
('utilisateur3', 'motdepasse3');

INSERT INTO threads (title, content, fk_user) VALUES
('Discussion 1', 'Contenu de la discussion 1', 1),
('Discussion 2', 'Contenu de la discussion 2', 2),
('Discussion 3', 'Contenu de la discussion 3', 3);

INSERT INTO comments (content, fk_user, fk_thread) VALUES
('Commentaire 1 sur Discussion 1', 1, 1),
('Commentaire 2 sur Discussion 1', 2, 1),
('Commentaire 1 sur Discussion 2', 3, 2);

INSERT INTO subscriptions (fk_user, fk_thread) VALUES
(1, 1),
(2, 1),
(3, 2);

INSERT INTO likes (fk_user, fk_comment) VALUES
(1, 1),
(2, 2),
(3, 3);

INSERT INTO messages (fk_sender, fk_receiver, content) VALUES
(1, 2, 'Message privé de utilisateur1 à utilisateur2'),
(2, 3, 'Message privé de utilisateur2 à utilisateur3'),
(3, 1, 'Message privé de utilisateur3 à utilisateur1');