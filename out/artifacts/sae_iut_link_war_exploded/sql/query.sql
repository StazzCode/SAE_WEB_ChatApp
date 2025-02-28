SELECT * FROM users;

SELECT * FROM threads;

SELECT * FROM posts;

SELECT * FROM threads WHERE owner_id = 1;

SELECT * FROM posts WHERE thread_id = 1;

SELECT * FROM posts WHERE author_id = 1;

SELECT owner_id, COUNT(*) as discussion_count FROM threads GROUP BY owner_id;

SELECT author_id, COUNT(*) as posts_count FROM posts GROUP BY author_id;

SELECT * FROM threads ORDER BY created_at DESC LIMIT 10;

SELECT * FROM posts ORDER BY created_at DESC LIMIT 10;