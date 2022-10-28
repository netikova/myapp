package com.exam.myapp.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.JstlView;

// 브라우저에서
// http://localhost:9090/myapp/member/list.do 로 요청을 보내면,
// MemberControllder 클래스의 list() 메서드가 실행되고,
// 브라우저 화면에 "회원목록" 이라고 출력되도록 구현
/*controller -> service -> dao -> db*/
/*dao가 값을 받아서 service에 주고 controller에 전달된다.*/
/*model에 포함시켜야 jsp에서 쓸 수 있다.*/
@Controller
@RequestMapping("/member/")
public class MemberController {
    @Autowired
    private MemberService memberService;   
    
	@RequestMapping(path = "list.do", method =  RequestMethod.GET)
	public String list(Model model) {
		List<MemberVo> list = memberService.selectList();
		model.addAttribute("memList", list);
		                  /* jsp=${} */		
		return "member/list";
	}
	
	@RequestMapping(path = "add.do", method =  RequestMethod.GET)
	public String addform(MemberVo vo) {			
		return "member/add";
	}
	
	@RequestMapping(path = "add.do", method =  RequestMethod.POST)
	//@RequestMapping메서드의 매개변수에 @Valid 를 적용하면,
	//해당 객체의 클래스에 지정한 검증 조건에 맞는지 검증 수행
	//@Valid 매개변수 뒤에 BindingResult (또는 Errors) 타입의 매개변수를 추가하면,
	//검증결과를 받아서 사용 가능
	public String add(@Valid MemberVo vo, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) { //검증결과 에러가 있다면
//		    System.out.println("검증 실패!");
			return "member/add"; //회원추가 jsp 화면을 다시 출력
		}
		int num = memberService.insert(vo); /* 반환받은 레코드수를 num에 넣는다. */
		
		return "redirect:/member/list.do";
	}
	
	@RequestMapping(path = "edit.do", method =  RequestMethod.GET)
	public String editform(MemberVo vo, Model model) { /* jsp의 파라미터 값을 vo로 받는다. */	
		
		MemberVo mvo = memberService.select(vo);
		/* memberService를 실행한 후 반환 값을 mvo에 저장한다. */
		model.addAttribute("memVo", mvo); /*jsp에서 이런 형식으로 써준다. ${memVo} */		
		return "member/edit";
	}

	@RequestMapping(path = "edit.do", method = RequestMethod.POST) /* jsp에서 모델이름을 memVo로 맞춰줬기때문에 memVo로 설정 */
	public String edit(@ModelAttribute("memVo") @Valid MemberVo vo, BindingResult bindingResult) { /* 파라미터 모델 이름을 memVo로 사용 */
		if(bindingResult.hasFieldErrors("memName") || bindingResult.hasFieldErrors("memPoint")) {
			//회원정보 변경시에는 이름과 포인트만 검증
			return "member/edit";
		}
		
		int num = memberService.update(vo); /* 반환받은 레코드수를 num에 넣는다. */
		
		return "redirect:/member/list.do";
	}
	@RequestMapping(path = "del.do", method =  RequestMethod.GET)
	public String del(MemberVo vo) { /* jsp의 파라미터 값을 vo로 받는다. */	
		
		int num = memberService.delete(vo);	
		
		return "redirect:/member/list.do";
	}
//	@ResponseBody 리턴값이 그대로 응답으로 가야하니까 이 어노테이션을 붙혀준다.
	@RequestMapping(path = "check.do", method =  RequestMethod.POST)
	@ResponseBody 
	public Map<String, Object> checkId(MemberVo vo) { 
		
		MemberVo mvo = memberService.select(vo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", mvo==null); //사용가능한 경우 result:true,불가능한 경우 result:false
		return map;
	}
	
	@RequestMapping(path = "login.do",method = RequestMethod.GET)
	public String loginForm() {
		return "member/login";		
	}
	
	@RequestMapping(path = "login.do",method = RequestMethod.POST)
	public String login(MemberVo vo, HttpSession session) {
		
		MemberVo memberVo = memberService.selectLogin(vo);
		
		if(memberVo==null) { //로그인 실패
			
			return "member/login";	
		}		
		//로그인 성공		
		session.setAttribute("loginUser", memberVo); /* 세션에 로그인에 성공한 사람의 로그인 정보를 담는다. */
		
		return "redirect:/member/list.do";		
	}
	
	@RequestMapping(path = "logout.do",method = RequestMethod.GET)
	public String logout(HttpSession session) {		
			
			
		// session.setAttribute("loginUser", null);  /* 로그인에 저장된 정보를 null로 만든다. */
		// session.removeAttribute("loginUser"); /* session에 대한 정보를 지운다. */
		session.invalidate(); /* session자체를 초기화 시킨다. */		
		return "redirect:/member/login.do";		
	}
	
}







