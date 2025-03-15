<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Connexion</title>
    <link href="https://cdn.jsdelivr.net/npm/daisyui@5" rel="stylesheet" type="text/css" />
    <script src="https://unpkg.com/@tailwindcss/browser@4"></script>
</head>
<body class="bg-gray-100 flex items-center justify-center min-h-screen">
<div class="container max-w-md mx-auto bg-white p-8 rounded-lg shadow-md">
    <h2 class="text-2xl font-semibold text-center mb-6">Connexion à IUT Link</h2>
    <form action="Authent?action=login" method="post" class="flex flex-col gap-4">
        <div class="form-control">
            <input type="text" name="username" placeholder="Nom d'utilisateur"
                   class="input input-bordered w-full" required>
        </div>
        <div class="form-control">
            <input type="password" name="password" placeholder="Mot de passe"
                   class="input input-bordered w-full" required>
        </div>
        <button type="submit" class="btn btn-primary w-full">Se connecter</button>
    </form>
    <div class="text-center mt-4">
        <p>Pas de compte ? <a href="Authent?action=register" class="text-blue-600 hover:underline">Créer un compte</a></p>
    </div>
</div>
</body>
</html>