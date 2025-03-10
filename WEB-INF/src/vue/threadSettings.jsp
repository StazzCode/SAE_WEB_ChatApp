<%@ page import="model.dao.UsersDAO" %>
<%@ page import="model.dto.Thread" %>
<%@ page import="model.dto.User" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: kellianmirey
  Date: 09/03/2025
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>IUT Link</title>
    <script src="https://unpkg.com/@tailwindcss/browser@4"></script>
    <script src="https://cdn.jsdelivr.net/npm/shadcn-ui@0.9.5/+esm"></script>
</head>

<%
    User user = (User) request.getSession().getAttribute("user");
    UsersDAO usersDAO = new UsersDAO();
    Thread selectedThread = (Thread) request.getSession().getAttribute("selectedThread");
    ArrayList<User> subscribers = usersDAO.findAllFromSubscription(selectedThread.getId());
%>

<body>
<a href="homepage">Back to HomePage</a>
  <div class="SubscribersList">
    <h1>Subscribers</h1>
    <div class="subscribers">
        <% if (subscribers != null) { for(User u : subscribers) { if (u.getId() != user.getId()) { %>
            <div class="subscriber">
                <p><%= u.getUsername()%></p>
                <form action="threadSettings" method="post">
                    <input type="hidden" name="action" value="removeSubscriber">
                    <input type="hidden" name="subscriberId" value="<%=u.getId()%>">
                    <input type="submit" value="Remove">
                </form>
            </div>
        <%}}}%>
    </div>
  </div>
<div class="Delete">
    <form action="threadSettings" method="post">
        <input type="hidden" name="action" value="deleteThread">
        <input type="submit" value="Delete">
    </form>
</div>
</body>
</html>
