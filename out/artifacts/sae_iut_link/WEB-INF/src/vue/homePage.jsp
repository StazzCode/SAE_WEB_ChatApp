<%@ page import="java.util.ArrayList" %>
<%@ page import="model.dto.User" %>
<%@ page import="model.dto.Thread" %>
<%@ page import="model.dto.Post" %>
<%@ page import="model.dao.UsersDAO" %><%--
  Created by IntelliJ IDEA.
  User: kellianmirey
  Date: 20/02/2025
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="">
    <title>IUT Link</title>
    <!-- <meta http-equiv="refresh" content="5"> --> <!-- Rafraîchissement toutes les 5s -->
    <script src="https://unpkg.com/@tailwindcss/browser@4"></script>
</head>
<%
  User user = (User) request.getSession().getAttribute("user");
  ArrayList<Thread> threads = user.getThreads();
  Thread selectedThread = (Thread) request.getSession().getAttribute("selectedThread");
%>
<body>
  <div class="flex flex-row w-screen h-screen">
    <div class="sideBar flex flex-1 flex-col bg-white">
      <div class="control">
        <a>
          <%= (user.getUsername() == null ? "LoginPlaceHolder" : user.getUsername()) %>
        </a>
        <a class="w-4 h-4 border-2 border-red-500">
          Disconnect
          <!-- <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="lucide lucide-log-out"><path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"></path><polyline points="16 17 21 12 16 7"></polyline><line x1="21" x2="9" y1="12" y2="12"></line></svg> -->
        </a>
      </div>
      <div class="threads">
        <div class="flex flex-row justify-between">
          <h4 class="px-4">Fils</h4>
          <a href="addThread" class="px-4">Add</a>
        </div>
        <% if (threads != null){ for(Thread t : threads) { %>
          <div class="thread">
            <a href="homepage?selectedThread=<%= t.getId() %>"><%=t.getTitle()%></a>
          </div>
        <% }} %> <!-- Fin de la boucle -->
      </div>
    </div>
    <div class="postsContainer flex-3 bg-gray-100">
      <% if (selectedThread != null) { %>
      <div class="postHeader">
        <h4><%=selectedThread.getTitle()%></h4>
        <a href="threadSettings" <% if (selectedThread.getUserId() != user.getId()) { %> hidden="hidden" <% } %>>ThreadSettings</a>
      </div>
      <% } %>
      <div class="posts grid grid-cols-1" <% if (selectedThread == null) {%> style="display: none" <%} %>>
        <%
          ArrayList<Post> posts = (selectedThread != null) ? selectedThread.getPosts() : null;

          if (posts != null) {
            for(Post p : posts) { %>
              <div class="post flex flex-col w-1/2 <% if (p.getAuthor().getId() == user.getId()) { %> justify-self-end items-end<% } %>">
                <div class="user">
                  <a><%= p.getAuthor().getUsername() %></a>
                </div>
                <div class="message">
                  <p><%= p.getContent() %></p>
                </div>
              </div>
        <% } } %>

      </div>
      <div class="postEntryContainer absolute w-3/4 bottom-0 p-8" <% if (selectedThread == null) {%> style="display: none" <%} %>>
        <div class="userEntry">
          <form action="homepage" method="post" class="flex justify-between [&>input]:px-6">
            <input class="bg-gray-300 flex-1" type="text" name="message" placeholder="Message" required>
            <input type="submit" id="submit" value="Envoyer">
          </form>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
