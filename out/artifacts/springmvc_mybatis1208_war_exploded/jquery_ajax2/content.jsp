<%@ page language="java" contentType="text/html; charset=utf-8"
 pageEncoding="utf-8"%>

<%
 if (application.getAttribute("content") == null)
  application.setAttribute("content", "content为空！！");
%>
<%
 if (request.getParameter("say") != null)
  application.setAttribute("content", application.getAttribute("content")
    + "<br/>"
    + request.getRemoteAddr()
    + ": "
    + request.getParameter("say"));
%>
<%=application.getAttribute("content")%> 
