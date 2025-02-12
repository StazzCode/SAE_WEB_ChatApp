SELECT * FROM users;

SELECT * FROM threads;

SELECT * FROM comments;

SELECT * FROM threads WHERE fk_user = 1;

SELECT * FROM comments WHERE fk_thread = 1;

SELECT * FROM comments WHERE fk_user = 1;

SELECT fk_user, COUNT(*) as discussion_count FROM threads GROUP BY fk_user;

SELECT fk_user, COUNT(*) as comment_count FROM comments GROUP BY fk_user;

SELECT * FROM threads ORDER BY created_at DESC LIMIT 10;

SELECT * FROM comments ORDER BY created_at DESC LIMIT 10;