<%@ include file="../include/includes.jsp"%>
<c:url value="/addressbook" var="indexUrl"></c:url>
<%
response.sendRedirect((String)pageContext.getAttribute("indexUrl"));
%>