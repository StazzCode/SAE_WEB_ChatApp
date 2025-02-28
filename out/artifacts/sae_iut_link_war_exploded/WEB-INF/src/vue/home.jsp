<html>
<head>
    <title>CampusTalk - Home</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <header>
        <h1>Welcome to CampusTalk</h1>
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="views/discussions.jsp">Discussions</a></li>
                <li><a href="views/profile.jsp">Profile</a></li>
            </ul>
        </nav>
    </header>
    <main>
        <h2>Latest Discussions</h2>
        <div id="discussion-list">
            <!-- Discussion items will be dynamically loaded here -->
        </div>
        <h2>Post a Message</h2>
        <form action="postMessage" method="post">
            <textarea name="message" rows="4" cols="50" placeholder="Write your message here..."></textarea><br>
            <input type="submit" value="Post Message">
        </form>
    </main>
    <footer>
        <p>&copy; 2023 CampusTalk. All rights reserved.</p>
    </footer>
</body>
</html>