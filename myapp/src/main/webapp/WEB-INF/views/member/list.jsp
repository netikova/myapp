<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
    <div class="col">
    <h1>회원목록</h1>
    <a href="${pageContext.request.contextPath}/member/add.do">
         <button type="button" class="btn btn-primary"><i class="bi-person-plus"></i>회원추가</button></a>
    <table class="table table-striped">
       <thead>
           <tr class="table-dark"><th>아이디</th><th>이름</th><th>포인트</th></tr>
       </thead>   
       <tbody>
		   <c:forEach var="vo" items="${memList}">	    
	           <tr>
	              <td><a href="${pageContext.request.contextPath}/member/edit.do?memId=${vo.memId}"><c:out value="${vo.memId}" /></a></td>
	              <td><c:out value="${vo.memName}"/></td>
	              <td>${vo.memPoint}</td>  <!-- int형이라 안에 script 가 존재할 수 없기 때문에 보안상 굳이 c:out을 안써줘도 된다. -->
	           </tr>
           </c:forEach>
       </tbody>    
    </table>
    </div>
</div>
