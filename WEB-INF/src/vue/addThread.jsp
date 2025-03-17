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
    <link href="https://cdn.jsdelivr.net/npm/daisyui@5" rel="stylesheet" type="text/css" />
    <script src="https://unpkg.com/@tailwindcss/browser@4"></script>
    <link rel="stylesheet" href="../../../css/global.css">
</head>
<body>
  <%
    User user = (User) request.getSession().getAttribute("user");
    ArrayList<Thread> threads = new ThreadsDAO().findWhereNotOwner(user.getId());
  %>

  <a href="homepage" class="absolute top-5 left-5 text-xl flex items-center gap-1">
    <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="lucide lucide-chevron-left">
      <path d="m15 18-6-6 6-6"/>
    </svg>
    Back to HomePage
  </a>
  <div class="flex justify-around items-center h-dvh p-16 overflow-hidden">
    <div class="ThreadsList flex flex-col h-2/3 bg-gray-300 p-4 gap-4 rounded-[16px] text-white overflow-auto">
      <% if (threads != null) { for(Thread t : threads) { if (!t.getOwnerUsername().equals(user.getUsername())) {%>
        <div class="thread flex flex-row items-center justify-between h-16 p-2 gap-6 bg-gray-600/50 rounded-[8px]">
          <div class="flex items-end gap-4 h-full mb-6">
            <div class="title flex flex-row gap-2">
              <a class="text-white/80">Title : </a>
              <a class="truncate"><%=t.getTitle()%></a>
            </div>
            <div class="author flex flex-row gap-2">
              <a class="text-white/80">Owner : </a>
              <a class="truncate"><%=t.getOwnerUsername()%></a>
            </div>
          </div>
          <form action="addThread" method="post" <% if (user.getThreads().stream().anyMatch(thread -> thread.getId() == t.getId())) { %> hidden="hidden" <% } %>>
            <input type="hidden" name="action" value="subscribe">
            <input type="hidden" name="threadId" value="<%= t.getId()%>">
            <input type="submit" class="btn text-white rounded-lg shadow-md shadow-blue-600/25 bg-blue-500 border-2 border-blue-400/25 hover:bg-blue-600 hover:shadow-blue-700/50 hover:border-blue-500/25 hover:shadow-lg transition delay-50 duration-300 ease-in-out" value="Subscribe">
          </form>
          <form action="addThread" method="post" <% if (user.getThreads().stream().noneMatch(thread -> thread.getId() == t.getId())) { %> hidden="hidden" <% } %>>
            <input type="hidden" name="action" value="unsubscribe">
            <input type="hidden" name="threadId" value="<%= t.getId()%>">
            <input type="submit" class="btn text-white rounded-lg shadow-md shadow-red-600/25 bg-red-500/95 border-2 border-red-400/25 hover:bg-red-600/95 hover:shadow-red-700/50 hover:border-red-500/25 hover:shadow-lg transition delay-50 duration-300 ease-in-out" value="Unsubscribe">
          </form>
          <hr>
        </div>
      <%}}}%>
    </div>
    <div class="divider divider-horizontal"></div>
    <div class="AddThread flex ">
      <form action="addThread?action=add" method="post">
        <input type="text" name="title" placeholder="Titre" required>
        <button type="submit">Ajouter</button>
      </form>
  </div>
</body>
</html>
