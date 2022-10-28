<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- jstl이용해서 jsp에서 for문을 쓴다. jstl쓴다고 등록 -->

<!--  (1)회원등록 폼을 제출(submit)할 때,
	     비밀번호와 비밀번호확인 값이 다르면 안내메시지를 출력하고 제출하지 않도록 구현
	  (2)회원등록 폼을 제출(submit)할 때, 중복확인을 성공한 적이 없으면 안내메시지를 출력하고 제출하지 않도록 구현
	  (3)중복확인 버튼을 클릭하면 
	       입력한 아이디가 데이터베이스에 없는 경우, 저장 버튼을 활성화
	       입력한 아이디가 데이터베이스에 이미 있는 경우, 저장 버튼을 비활성화(disabled)
	  (4)회원아이디 값을 변경한 경우에는 다시 중복확인을 하도록 구현
	      (중복확인 버튼을 활성화) -->
<script type="text/javascript">
  		 
	    $(function(){ //문서 로드가 완료된후 실행
	         var idChecked = false; //아이디중복확인여부

	         $('#memForm').on('submit', function(e){
			     if(!idChecked) {
			    	 alert('아이디중복확인이 필요합니다.');
			    	 return false;
			     }	 
				 if($('#memPass').val() != $('#memPassCheck').val()){
					 alert('비밀번호가 일치하지 않습니다.')
					 return false; //e.preventDefault();
				 }			 
			 });
	         $('#dupBtn').on('click', function(){
	        	 $.ajax({
	        		 url: "${pageContext.request.contextPath}/member/check.do",
	        		 method: "post",
	        		 data: { memId: $('#memId').val() },
	        		 dataTypes: 'json'
	        	 }).done(function(resp){
	        		 console.log(resp);
	        		 idChecked = resp.result;
	        		 if(resp.result){ //사용가능한 아이디
	        			 alert('사용가능한 아이디입니다.')
	        			 $('#saveBtn').prop('disabled', false);
	        			 $('#dupBtn').prop('disabled', true);
	        		 }else{ //이미 존재하는 아이디
	        			 alert('이미 존재하는 아이디입니다.')
	        			 $('#saveBtn').prop('disabled', true);
	        			 $('#dupBtn').prop('disabled', false);	        			 
	        		 }
	        		 
//	        			 $('#saveBtn').prop('disabled', !resp.result);
//                       $('#dupBtn').prop('disabled', resp.result);
	        	 }).fail(function(jqXHR,textStatus){
	        		 alert('아이디 중복 확인 요청 실패!');
	        	 });
	      	 }); 
	         
	         $('#memId').on('change', function(){
	        	 idChecked = false;
//	        	 $('#saveBtn').prop('disabled', true);
//    			 $('#dupBtn').prop('disabled', false);	        
	         });
		 });

	 
 
</script>


<!-- css framework

	Bootstrap
	Foundation
	Pure, Bulma, tailwindcss -->


<div class="row">
    <div class="col">
    <h1>회원등록</h1>
    <form:form modelAttribute="memberVo" id="memForm" action="${pageContext.request.contextPath}/member/add.do" method="post">
	  <div class="mb-3">
	    <form:label path="memId" class="form-label">아이디</form:label>
	    <div class="input-group">
		    <form:input path="memId" class="form-control" cssErrorClass="form-control is-invalid"/>	
		    <button type="button" id="dupBtn" class="btn btn-primary">중복확인</button> 
		    <!-- 컨트롤러에서 검증 결과, 모델 객체의 memId 속성값과 관련된 오류가 있는 경우, 오류 메시지 출력 -->
		    <form:errors path="memId" cssClass="invalid-feedback"/>   	    
	    </div>
	  </div>
	  <div class="mb-3">
	    <form:label path="memPass" class="form-label">비밀번호</form:label>
	    <form:password path="memPass" class="form-control" cssErrorClass="form-control is-invalid"/>
	    <form:errors path="memPass" cssClass="invalid-feedback"/> 
	  </div>
	  <div class="mb-3">
	    <label for="memPassCheck" class="form-label">비밀번호확인</label>
	    <input type="password" class="form-control" id="memPassCheck"> <!-- 파라미터로 보낼게 아니기 때문에 name은 지운다. -->
	  </div>
	  <div class="mb-3">
	    <form:label path="memName" class="form-label">이름</form:label>
	    <form:input path="memName" class="form-control" cssErrorClass="form-control is-invalid"/>	  
	    <form:errors path="memName" cssClass="invalid-feedback"/> 
	  </div>
	  <div class="mb-3">
	    <form:label path="memPoint" class="form-label">포인트</form:label>
	    <form:input type="number" path="memPoint"  class="form-control" cssErrorClass="form-control is-invalid"/>	    
	    <form:errors path="memPoint" cssClass="invalid-feedback"/>
	  </div>	  
	  <button type="submit" id="saveBtn"  class="btn btn-primary"><i class="bi-file-arrow-up"></i> 저장</button>
    <a href="${pageContext.request.contextPath}/member/list.do">
         <button type="button" class="btn btn-primary"><i class="bi-plus-circle"></i> 회원목록</button>
    </a>    
	</form:form> 
	
	
    </div>
</div>

