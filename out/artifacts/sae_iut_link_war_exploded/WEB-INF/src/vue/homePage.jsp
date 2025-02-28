<%@ page import="java.util.ArrayList" %>
<%@ page import="model.dto.User" %>
<%@ page import="model.dto.Thread" %><%--
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
    <title>HomePage</title>
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
        <div class="flex flex-row">
          <h4>Fils</h4>
          <a>ThreadsSettings</a>
        </div>
        <% if (threads != null){ for(Thread t : threads) { %>
          <div class="thread">
            <a href="${pageContext.request.contextPath}/homepage/?selectedThread=<%= t.getId() %>"><%=t.getTitle()%></a>
          </div>
        <% }} %> <!-- Fin de la boucle -->
      </div>
    </div>
    <div class="postsContainer flex-3 bg-gray-100">
      <div class="posts" <% if (selectedThread == null) {%> style="display: none" <%} %>>
        <% if (selectedThread != null) { %>
        <div class="postHeader">
          <h4><%=selectedThread.getTitle()%></h4>
          <a>ThreadSettings</a>
        </div>
        <% } %>
        <div class="post">

        </div>

      </div>
      <div class="postEntryContainer absolute w-3/4 bottom-0 p-8" <% if (selectedThread == null) {%> style="display: none" <%} %>>
        <div class="userEntry">
          <form class="flex justify-between [&>input]:px-6">
            <input class="bg-gray-300 flex-1" type="text" name="message" placeholder="Message">
            <input type="submit" name="submit" value="Envoyer">
          </form>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
