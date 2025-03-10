<%@ page import="java.util.ArrayList" %>
<%@ page import="model.dto.User" %>
<%@ page import="model.dto.Thread" %>
<%@ page import="model.dto.Post" %>
<%@ page import="java.time.temporal.ChronoUnit" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%--
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
    <link href="https://cdn.jsdelivr.net/npm/daisyui@5" rel="stylesheet" type="text/css" />
    <script src="https://unpkg.com/@tailwindcss/browser@4"></script>
</head>
<%
  User user = (User) request.getSession().getAttribute("user");
  ArrayList<Thread> threads = user.getThreads();
  Thread selectedThread = (Thread) request.getSession().getAttribute("selectedThread");
%>
<body>
  <div class="flex flex-row w-screen h-screen text-lg">
    <div class="sideBar flex flex-1 flex-col bg-white">
      <div class="control flex flex-row items-center px-4 py-2">
        <a class="mr-8 text-lg font-semibold">
          <%= (user.getUsername() == null ? "LoginPlaceHolder" : user.getUsername()) %>
        </a>
        <a>
          <div class="max-w-7 max-h-7">
            <svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="lucide lucide-log-out">
              <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"></path>
              <polyline points="16 17 21 12 16 7"></polyline>
              <line x1="21" x2="9" y1="12" y2="12"></line>
            </svg>
          </div>
        </a>
      </div>
      <hr>
      <div class="threads p-4">
        <div class="flex flex-row justify-between">
          <h4 class="">Fils</h4>
          <a href="addThread" class="px-4 max-w-10 max-h-10">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6">
              <path stroke-linecap="round" stroke-linejoin="round" d="M12 4.5v15m7.5-7.5h-15"></path>
            </svg>

          </a>
        </div>
        <div class="my-4">
          <% if (threads != null){ for(Thread t : threads) { %>
            <div class="thread">
              <a href="homepage?selectedThread=<%= t.getId() %>"><%=t.getTitle()%></a>
            </div>
          <% }} %> <!-- Fin de la boucle -->
        </div>
      </div>
    </div>
    <div class="postsContainer flex-3 bg-gray-100">
      <% if (selectedThread != null) { %>
      <div class="postHeader flex flex-row p-4 bg-white items-center">
        <h4 class="mr-4"><%=selectedThread.getTitle()%></h4>
        <a href="threadSettings" <% if (selectedThread.getUserId() != user.getId()) { %> hidden <% } %>>
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="lucide lucide-settings">
            <path d="M12.22 2h-.44a2 2 0 0 0-2 2v.18a2 2 0 0 1-1 1.73l-.43.25a2 2 0 0 1-2 0l-.15-.08a2 2 0 0 0-2.73.73l-.22.38a2 2 0 0 0 .73 2.73l.15.1a2 2 0 0 1 1 1.72v.51a2 2 0 0 1-1 1.74l-.15.09a2 2 0 0 0-.73 2.73l.22.38a2 2 0 0 0 2.73.73l.15-.08a2 2 0 0 1 2 0l.43.25a2 2 0 0 1 1 1.73V20a2 2 0 0 0 2 2h.44a2 2 0 0 0 2-2v-.18a2 2 0 0 1 1-1.73l.43-.25a2 2 0 0 1 2 0l.15.08a2 2 0 0 0 2.73-.73l.22-.39a2 2 0 0 0-.73-2.73l-.15-.08a2 2 0 0 1-1-1.74v-.5a2 2 0 0 1 1-1.74l.15-.09a2 2 0 0 0 .73-2.73l-.22-.38a2 2 0 0 0-2.73-.73l-.15.08a2 2 0 0 1-2 0l-.43-.25a2 2 0 0 1-1-1.73V4a2 2 0 0 0-2-2z"/>
            <circle cx="12" cy="12" r="3"/></svg>
        </a>
      </div>
      <% } %>
      <div class="posts grid grid-cols-1 p-4" <% if (selectedThread == null) {%> style="display: none" <%} %>>
        <%
          ArrayList<Post> posts = (selectedThread != null) ? selectedThread.getPosts() : null;

          if (posts != null) {
            for(Post p : posts) { %>
              <div class="post flex flex-col w-1/2 <% if (p.getAuthor().getId() == user.getId()) { %> justify-self-end items-end<% } %>">
                <div class="chat-header">
                  <a><%= p.getAuthor().getUsername() %></a>
                  <time class="text-xs opacity-50"><%=new SimpleDateFormat("HH:mm").format(p.getCreatedAt())%></time>
                </div>
                <div class="chat-bubble">
                  <p><%= p.getContent() %></p>
                </div>
              </div>
        <% } } %>

      </div>
      <div class="postEntryContainer absolute w-3/4 bottom-0 p-8" <% if (selectedThread == null) {%> style="display: none" <%} %>>
        <div class="userEntry">
          <form action="homepage" method="post" class="flex justify-between [&>input]:px-6">
            <input class="input input-md bg-gray-300 flex-1" type="text" name="message" placeholder="Message" required>
            <div class="mx-2 btn btn-primary">
              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="lucide lucide-send">
                <path d="M14.536 21.686a.5.5 0 0 0 .937-.024l6.5-19a.496.496 0 0 0-.635-.635l-19 6.5a.5.5 0 0 0-.024.937l7.93 3.18a2 2 0 0 1 1.112 1.11z"/><path d="m21.854 2.147-10.94 10.939"/></svg>
                <input class="" type="submit" id="submit" value="Envoyer">
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
