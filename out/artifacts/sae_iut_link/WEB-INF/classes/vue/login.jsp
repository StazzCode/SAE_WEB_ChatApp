<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Connexion</title>
</head>
<body>
    <div class="container">
        <h2>Connexion à IUT Link</h2>
        <form action="Authent?action=login" method="post">
            <input type="text" name="username" placeholder="Nom d'utilisateur" required>
            <input type="password" name="password" placeholder="Mot de passe" required>
            <button type="submit">Se connecter</button>
        </form>
        <div>
            <p>Pas de compte ? <a href="Authent?action=register">Créer un compte</a></p>
        </div>
    </div>
</body>
</html>