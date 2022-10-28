<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
</head>
<body>
<form  action="${pageContext.request.contextPath}/member/list.do">
    이름: <input type="text" name="name"/> <br />
    닉네임: <input type="text" name="nickname"/> <br />
    비밀번호:<input type="password" name="password"/> <br />
    <h1>회원목록</h1>
    <input type="submit" value="전송"/>
</body>
</html>