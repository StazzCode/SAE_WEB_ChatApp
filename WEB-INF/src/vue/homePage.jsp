<%@ page import="java.util.ArrayList" %>
<%@ page import="model.dto.User" %>
<%@ page import="model.dto.Thread" %>
<%@ page import="model.dto.Post" %>
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
  if (user == null) {
    response.sendRedirect(request.getContextPath() + "/login");
  }
  ArrayList<Thread> threads = user.getThreads();
  Thread selectedThread = (Thread) request.getSession().getAttribute("selectedThread");
%>
<body>
<div class="flex flex-row w-screen h-screen max-h-screen text-lg">
  <div class="sideBar flex flex-1 flex-col max-w-sm w-sm h-full bg-white">
    <div class="control flex flex-row justify-around items-center m-4">
      <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="lucide lucide-user">
        <path d="M19 21v-2a4 4 0 0 0-4-4H9a4 4 0 0 0-4 4v2"></path>
        <circle cx="12" cy="7" r="4"></circle></svg>
      <a class="w-3xs text-xl font-semibold truncate">
        <%= (user.getUsername() == null ? "LoginPlaceHolder" : user.getUsername()) %>
      </a>
      <form class="disconnect relative max-w-7 max-h-7 w-7 h-7" action="homepage" method="post">
        <svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="lucide lucide-log-out absolute">
          <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"></path>
          <polyline points="16 17 21 12 16 7"></polyline>
          <line x1="21" x2="9" y1="12" y2="12"></line>
        </svg>
        <input type="hidden" name="disconnect" value="true">
        <input class="absolute w-full h-full" type="submit" value="">
      </form>
    </div>
    <hr>
    <div class="threads flex flex-col p-4 m-4 rounded-[16px] bg-gray-200 overflow-hidden">
      <div class="flex flex-row justify-between mb-2">
        <span class="flex flex-row items-center">
          <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="lucide lucide-hash">
            <line x1="4" x2="20" y1="9" y2="9"></line>
            <line x1="4" x2="20" y1="15" y2="15"></line>
            <line x1="10" x2="8" y1="3" y2="21"></line>
            <line x1="16" x2="14" y1="3" y2="21"></line>
          </svg>
          <a class="text-xl">Threads</a>
        </span>
        <a href="addThread" class="flex justify-center items-center size-8 rounded-full bg-gray-100">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" width="20" height="20" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6">
            <path stroke-linecap="round" stroke-linejoin="round" d="M12 4.5v15m7.5-7.5h-15"></path>
          </svg>
        </a>
      </div>
      <hr>
      <div class="threadContainer my-4 overflow-auto">
        <% if (threads != null){ for(Thread t : threads) { %>
        <div class="thread flex flex-row justify-between">
          <a class="truncate flex-1" href="homepage?selectedThread=<%= t.getId() %>"><%=t.getTitle()%></a>

          <a href="threadSettings?selectedThread=<%= t.getId() %>" <% if (t.getUserId() != user.getId()) { %> hidden <% } %>>
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="lucide lucide-settings">
              <path d="M12.22 2h-.44a2 2 0 0 0-2 2v.18a2 2 0 0 1-1 1.73l-.43.25a2 2 0 0 1-2 0l-.15-.08a2 2 0 0 0-2.73.73l-.22.38a2 2 0 0 0 .73 2.73l.15.1a2 2 0 0 1 1 1.72v.51a2 2 0 0 1-1 1.74l-.15.09a2 2 0 0 0-.73 2.73l.22.38a2 2 0 0 0 2.73.73l.15-.08a2 2 0 0 1 2 0l.43.25a2 2 0 0 1 1 1.73V20a2 2 0 0 0 2 2h.44a2 2 0 0 0 2-2v-.18a2 2 0 0 1 1-1.73l.43-.25a2 2 0 0 1 2 0l.15.08a2 2 0 0 0 2.73-.73l.22-.39a2 2 0 0 0-.73-2.73l-.15-.08a2 2 0 0 1-1-1.74v-.5a2 2 0 0 1 1-1.74l.15-.09a2 2 0 0 0 .73-2.73l-.22-.38a2 2 0 0 0-2.73-.73l-.15.08a2 2 0 0 1-2 0l-.43-.25a2 2 0 0 1-1-1.73V4a2 2 0 0 0-2-2z"></path>
              <circle cx="12" cy="12" r="3"></circle></svg>
          </a>
        </div>
        <% }} %> <!-- Fin de la boucle -->
      </div>
    </div>
  </div>
  <div class="postsContainer relative flex-3 bg-gray-100">
    <% if (selectedThread != null) { %>
    <% } %>
    <div class="relative flex flex-col justify-between h-screen">
      <div class="posts grid grid-cols-1 p-4 h-full overflow-auto" <% if (selectedThread == null) {%> style="display: none" <%} %>>
        <%
          ArrayList<Post> posts = (selectedThread != null) ? selectedThread.getPosts() : null;
          User previousAuthor = null;

          if (posts != null) {
            for(Post p : posts) { %>
        <% if (previousAuthor == null || previousAuthor.getId() != p.getAuthor().getId()) { %>
        <% if (previousAuthor != null) { %></div><% } %>
      <div class="chat flex flex-col w-1/2<% if (p.getAuthor().getId() == user.getId()) { %> justify-self-end items-end <% } %>">
        <div class="chat-header gap-2 items-baseline <% if (p.getAuthor().getId() == user.getId()) { %> flex-row-reverse <% } %>">
          <% if (p.getAuthor().getUsername().equals(selectedThread.getOwnerUsername())) { %>
          <div class="flex justify-center items-center w-6 h-6 rounded-full bg-blue-500 text-white">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="lucide lucide-shield-user">
              <path d="M20 13c0 5-3.5 7.5-7.66 8.95a1 1 0 0 1-.67-.01C7.5 20.5 4 18 4 13V6a1 1 0 0 1 1-1c2 0 4.5-1.2 6.24-2.72a1.17 1.17 0 0 1 1.52 0C14.51 3.81 17 5 19 5a1 1 0 0 1 1 1z"></path>
              <path d="M6.376 18.91a6 6 0 0 1 11.249.003"></path>
              <circle cx="12" cy="11" r="4"></circle>
            </svg>
          </div>
          <% } %>
          <a class="text-lg font-medium"><%= p.getAuthor().getUsername() %></a>
          <time class="text-xs opacity-50"><%=new SimpleDateFormat("HH:mm").format(p.getCreatedAt())%></time>
        </div>
        <% } %>
        <div class="chat-bubble font-normal text-clip break-words p-2 mb-1 rounded-lg <% if (p.getAuthor().getId() == user.getId()) { %> bg-linear-to-tr from-blue-200 from-10% via-blue-300 via-70 to-blue-400 to-100% before:bg-transparent<% }  else { %> bg-gray-300 <% } %>">
          <p><%= p.getContent() %></p>
        </div>
        <% previousAuthor = p.getAuthor(); %>
        <% } %>
        <% if (previousAuthor != null) { %></div><% } %>
      <% } %>

      </div>
      <div class="postEntryContainer pb-6 pt-3 bg-transparent " <% if (selectedThread == null) {%> style="display: none" <%} %>>
        <div class="userEntry bg-transparent px-6">
          <form action="homepage" method="post" class="flex justify-between [&>input]:px-6 bg-gray-300 border-2 border-gray-200/50 p-1 rounded-full">
            <input class="flex-1 items-center border-0 outline-none" type="text" name="message" placeholder="Send message" required>
            <div class="btn btn-md ml-6 text-white rounded-full shadow-md shadow-blue-600/25 bg-blue-500 border-2 border-blue-400/25 hover:bg-blue-600 hover:shadow-blue-700/50 hover:border-blue-500/25 hover:shadow-lg transition delay-50 duration-300 ease-in-out">
              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="lucide lucide-send">
                <path d="M14.536 21.686a.5.5 0 0 0 .937-.024l6.5-19a.496.496 0 0 0-.635-.635l-19 6.5a.5.5 0 0 0-.024.937l7.93 3.18a2 2 0 0 1 1.112 1.11z"></path><path d="m21.854 2.147-10.94 10.939"></path></svg>
              <input class="" type="submit" id="submit" value="Send">
            </div>
          </form>
        </div>
      </div>
    </div>

  </div>
</div>
</body>
</html>
