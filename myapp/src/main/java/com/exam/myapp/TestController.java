package com.exam.myapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//http://localhost:8000/myapp/test.do 로 요청을 보내면
//이클립스 콘솔에 "TEST!"라고 출력되도록 구현
//=="테스트 화면"이라고 출력하는 JSP파일을 만들고,
// 컨트롤러 실행후 그 JSP파일이 실행되도록 구현

//http://localhost:8000/myapp/test.do?x=3&y=4로 요청을 보내면
//웹브라우저 화면에 "x + y = 7"이라고 출력되도록 변경

//한글 파라미터 인코딩 설정
//GET 방식 요청의 파라미터 인코딩 설정
//서버(톰캣)의 설정파일(server.xml)한글 파일 설정
//HTTP를 담당하는 <Connector>에 URIEncoding="UTF-8" 추가
//POST방식 요청의 파라미터 인코딩 설정
//web.xml 파일에 톰캣 또는 스프링이 제공하는 인코딩필터를 등록

//웹브라우저 화면에 "테스트 화면"이라고 출력되도록 구현
//@Component
//@Service
//@Repository
//@Configuration
//@ControllerAdvice

@Controller //웹 요청이 왔을때 실행되는 코드를 담고 있는 클래스를 의미
public class TestController {

	//스프링이 컨트롤러 실행 후 뷰에 대한 정보를 받지 못하면,
	//DefaultRequestToViewNameTranslator를 사용하여 뷰 이름을 자동생성
	//요청경로에서 컨텍스트패스까지 삭제하고,
	//맨 앞의 / 와 맨 뒤의 확장자를 제거한 뷰이름으로 사용
	
	// jsp에 데이터를 포함시키고 싶으면 model(Model model, Map map, ModelMap modelMap셋 중 하나)에 포함해야 된다. 
	@RequestMapping(value="/test.do", method = RequestMethod.GET)
//	public String test(/* @RequestParam("x")변수이름이 같으면 생략해도 된다. */ int x, int y, Map map) {
//	파라미터가 많을때는 객체를 이용하여 받는 것이 좋다. TestVo vo, jsp에 활용할때는 첫글자를 소문자로 바꾸고 사용하면 된다. TestVo는 Spring이 등록해준다.
	public String test(/* @ModelAttribute("testVo") 모델이름이 같으면 안써주면 된다. */ TestVo vo, Map map) {
		System.out.print("TEST!");
		
		//List<LicenseVo> lvo = vo.getLicense();
		for (LicenseVo lvo : vo.getLicense()) {
			System.out.println(lvo.getLicenseName());
			System.out.println(lvo.getLicenseOrg());
			System.out.println(lvo.getLicenseDate());
		}
		
		for (String f :  vo.getFood()) {
			System.out.println(f);	
		}

		//int sum = x + y;
		int sum = vo.getX() + vo.getY();	
		
		List<String> food = vo.getFood();
		map.put("result", sum);
		map.put("food", food);		
//		map.put("testVo", vo); testVo는 Spring이 알아서 등록해준다.
//		model.addAttribute("result", sum);
//		modelMap.addAttribute("result", sum);
		return "test";
	}	
	
	@RequestMapping(path = "/param.do")
	public String param(TestVo vo, Model model) {
		ArrayList<CodeVo> list = new ArrayList<CodeVo>();
		list.add( new CodeVo("f001", "피자"));
		list.add( new CodeVo("f002", "햄버거"));
		list.add( new CodeVo("f003", "스파게티"));
		model.addAttribute("codeList", list);
		
//		TestVo vo = new TestVo();
//		vo.setLunch("f002");
//		vo.setDinner("f001");
//		List<String> list2 = new ArrayList<String>();
//		list2.add("f001");
//		list2.add("f003");
//		vo.setFood(list2);
//		model.addAttribute("testVo", vo);				
		
		return "param";
		
		
	}
	
}
