MLD : 

users (*pk_user*, username, password, created_at)

threads (*pk_thread*, title, content, #fk_user, created_at)

comments (*pk_comment*, content, #fk_user, #fk_thread, created_at)

subscriptions (*pk_subscription*, #fk_user, #fk_thread, created_at)

likes (*pk_like*, #fk_user, #fk_comment, created_at)

messages (*pk_message*, #fk_sender, #fk_receiver, content, created_at)

MCD : 

