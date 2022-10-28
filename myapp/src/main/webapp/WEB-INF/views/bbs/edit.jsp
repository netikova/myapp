<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- jstl이용해서 jsp에서 for문을 쓴다. jstl쓴다고 등록 -->

<script type="text/javascript">
//   삭제링크 클릭시, 삭제여부를 묻는 창을 출력하고,
//   삭제하겠다고 선택하는 경우에만 삭제하도록 구현
/* function removeCheck() {
 if (confirm("정말 삭제하시겠습니까??") == true){    //확인
     document.removefrm.submit();
 }else{   //취소
     return false;
 }
} */

   $(function(){ /* 문서를 다 읽은 후 객체를 완성한 다음 함수를 실행하기 위한 틀 id=dellink인 엘리먼트 클릭시 실행*/
	   $('#delLink').on('click', function(e){
		   var ok = confirm("진짜 삭제?");	  
		   if (ok===false){
			   e.preventDefault(); //이벤트에 대한 브라우저 기본동작 취소
//			   return false; //이벤트리스너함수에서 false를 반환하면 이벤트에 대한 브라우저 기본동작 취소
		   }
	   }); 
   });
   
/*    window.addEventListener('DOMContentLoaded', function(){	   
       document.querySelect('#delLink').onclick = function(e){
    	   if(!confrim('진짜 삭제?')){
    		   e.preventDefault(); //또는 return false;    		   
    	   }
       };
   }); */
/* alert('')출력
confrim('')예,아니오
prompt문자열 입력 */

</script>
</head>
<!-- css framework

	Bootstrap
	Foundation
	Pure, Bulma, tailwindcss -->


<div class="row">
    <div class="col">
    <h1>게시글변경</h1>
    <form:form modelAttribute="bbsVo"  action="${pageContext.request.contextPath}/bbs/edit.do" method="post">
	  <div class="mb-3">
	    <form:label path="bbsNo" class="form-label">글번호</form:label> <!-- value에 아이디값을 출력 -->
	    <form:input  path="bbsNo" readonly="true" class="form-control"/>	    
	  </div>	 
	  <div class="mb-3">
	    <form:label path="bbsTitle" class="form-label">제목</form:label>
	    <form:input path="bbsTitle" class="form-control" cssErrorClass="form-control is-invalid"/>	  
	    <form:errors path="bbsTitle" cssClass="invalid-feedback"/> 
	  </div>
	  <div class="mb-3">
	    <form:label path="bbsContent" class="form-label">내용</form:label>
	    <form:textarea path="bbsContent" rows="5" class="form-control" cssErrorClass="form-control is-invalid"/>	    
	    <form:errors path="bbsContent" cssClass="invalid-feedback"/>
	  </div>	  
	  <button type="submit" class="btn btn-primary"><i class="bi-file-arrow-up"></i> 저장</button>
    <a href="${pageContext.request.contextPath}/bbs/list.do">
         <button type="button" class="btn btn-primary"><i class="bi-plus-circle"></i> 목록</button>
    </a>    
    <a id="delLink" href="${pageContext.request.contextPath}/bbs/del.do?bbsNo=${bbsVo.bbsNo}">
         <button type="button" class="btn btn-primary" ><i class="bi-plus-circle"></i> 삭제</button>
    </a>
	</form:form> 
	
	
    </div>
</div>

