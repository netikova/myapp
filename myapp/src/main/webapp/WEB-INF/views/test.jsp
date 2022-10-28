<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	
</h1>

<P>  <h2>x + y = ${result}</h2></P>
     <ul>
        <c:forEach var="f" items="${testVo.food}">
             <li>${f}</li>
        </c:forEach>        
     
     </ul>
</body>
</html>
