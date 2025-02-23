<%--
  Created by IntelliJ IDEA.
  User: kellianmirey
  Date: 20/02/2025
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="">
    <title>HomePage</title>
    <script src="https://unpkg.com/@tailwindcss/browser@4"></script>
</head>
<body>
  <div class="flex flex-row w-screen h-screen">
    <div class="sideBar flex flex-col bg-white">
      <div class="control">
        <a>LoginPlaceHolder</a>
        <a>Disconnect</a>
      </div>
      <div class="threads">
        <div class="flex flex-row">
          <h4>Fils</h4>
          <a>ThreadsSettings</a>
        </div>
      </div>
    </div>
    <div class="postsContainer w-full h-full bg-gray-100">
      <div class="posts">

      </div>
      <div class="postContainer absolute bottom-0">
        <div class="userEntry flex-box justify-center border-2 border-red-500">
          <form class="">
            <input type="text" name="messageInput" placeholder="Message">
            <input type="submit" name="submit" value="Envoyer">
          </form>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
