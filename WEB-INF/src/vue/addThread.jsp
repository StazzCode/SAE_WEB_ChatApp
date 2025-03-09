<%@ page import="model.dto.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.dao.ThreadsDAO" %>
<%@ page import="model.dto.Thread" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: kellianmirey
  Date: 28/02/2025
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>IUT Link</title>
</head>
<body>
  <%
    User user = (User) request.getSession().getAttribute("user");
    ArrayList<Thread> threads = new ThreadsDAO().findAll();
  %>

  <a href="homepage">Back to HomePage</a>
  <div class="flex">
    <div class="ThreadsList">
      <% if (threads != null) { for(Thread t : threads) { if (!t.getOwnerUsername().equals(user.getUsername())) {%>
        <div class="thread border-2 border-red-500 flex flex-row justify-between">
          <div class="title"><%=t.getTitle()%></div>
          <div class="author"><%=t.getOwnerUsername()%></div>
          <form action="addThread" method="post" <% if (user.getThreads().stream().anyMatch(thread -> thread.getId() == t.getId())) { %> hidden="hidden" <% } %>>
            <input type="hidden" name="action" value="subscribe">
            <input type="hidden" name="threadId" value="<%= t.getId()%>">
            <input type="submit" value="Subscribe">
          </form>
          <form action="addThread" method="post" <% if (user.getThreads().stream().noneMatch(thread -> thread.getId() == t.getId())) { %> hidden="hidden" <% } %>>
            <input type="hidden" name="action" value="unsubscribe">
            <input type="hidden" name="threadId" value="<%= t.getId()%>">
            <input type="submit" value="Unsubscribe">
          </form>
        </div>
      <%}}}%>
    </div>
    <div class="AddThread">
      <form action="addThread?action=add" method="post">
        <input type="text" name="title" placeholder="Titre" required>
        <button type="submit">Ajouter</button>
      </form>
  </div>
</body>
</html>
