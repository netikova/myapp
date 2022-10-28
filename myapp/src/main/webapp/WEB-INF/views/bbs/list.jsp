<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <!-- 날짜나 숫자를 원하는 형태(String)로 변환하고 국제화 기능을 제공한다. -->

<div class="row">
    <div class="col">
    <h1>게시글목록</h1>
    <a href="${pageContext.request.contextPath}/bbs/add.do">
         <button type="button" class="btn btn-primary"><i class="bi-person-plus"></i>게시글추가</button></a>
    <table class="table table-striped">
       <thead>
           <tr class="table-dark"><th>제목</th><th>작성자</th><th>등록일</th></tr>
       </thead>   
       <tbody>
		   <c:forEach var="vo" items="${bbsList}">	    
	           <tr>
	              <td><a href="${pageContext.request.contextPath}/bbs/edit.do?bbsNo=${vo.bbsNo}"><c:out value="${vo.bbsTitle}" /></a></td>
	              <td><c:out value="${vo.bbsWriter}"/></td>
	              <td><fmt:formatDate value="${vo.bbsRegDate}" pattern="yyyy/MM/dd HH:mm:ss"/></td>  
	           </tr>
           </c:forEach>
       </tbody>    
    </table>
    </div>
</div>
