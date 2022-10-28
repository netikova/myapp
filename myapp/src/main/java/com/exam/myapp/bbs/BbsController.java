package com.exam.myapp.bbs;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.JstlView;

import com.exam.myapp.member.MemberVo;

// 브라우저에서
// http://localhost:9090/myapp/bbs/list.do 로 요청을 보내면,
// MemberControllder 클래스의 list() 메서드가 실행되고,
// 브라우저 화면에 "회원목록" 이라고 출력되도록 구현
/*controller -> service -> dao -> db*/
/*dao가 값을 받아서 service에 주고 controller에 전달된다.*/
/*model에 포함시켜야 jsp에서 쓸 수 있다.*/
@Controller
@RequestMapping("/bbs/")
public class BbsController {
    @Autowired
    private BbsService bbsService;   
    
	@RequestMapping(path = "list.do", method =  RequestMethod.GET)
	public String list(Model model) {
		List<BbsVo> list = bbsService.selectList();
		model.addAttribute("bbsList", list);
		                  /* jsp=${} */		
		return "bbs/list";
	}
	
	@RequestMapping(path = "add.do", method =  RequestMethod.GET)
	public String addform(BbsVo vo) {			
		return "bbs/add";
	}
	
	@RequestMapping(path = "add.do", method =  RequestMethod.POST)
	//@RequestMapping메서드의 매개변수에 @Valid 를 적용하면,
	//해당 객체의 클래스에 지정한 검증 조건에 맞는지 검증 수행
	//@Valid 매개변수 뒤에 BindingResult (또는 Errors) 타입의 매개변수를 추가하면,
	//검증결과를 받아서 사용 가능                                          @SessionAttribute("loginUser") MemberVo(mvo) loginuser의 id값을 mvo라는 변수에 저장해라.      
	public String add(@Valid BbsVo vo, BindingResult bindingResult, HttpSession session) {  
//		if(bindingResult.hasErrors()) { //검증결과 에러가 있다면
////		    System.out.println("검증 실패!");
//			return "bbs/add"; //회원추가 jsp 화면을 다시 출력
//		}
		MemberVo mvo = (MemberVo) session.getAttribute("loginUser");
		vo.setBbsWriter(mvo.getMemId());
		
		
		
		int num = bbsService.insert(vo); /* 반환받은 레코드수를 num에 넣는다. */
		
		return "redirect:/bbs/list.do";
	}
	
	@RequestMapping(path = "edit.do", method =  RequestMethod.GET)
	public String editform(BbsVo vo, Model model) { /* jsp의 파라미터 값을 vo로 받는다. */	
		
		BbsVo mvo = bbsService.select(vo);
		/* bbsService를 실행한 후 반환 값을 mvo에 저장한다. */
		model.addAttribute("bbsVo", mvo); /*jsp에서 이런 형식으로 써준다. ${bbsVo} */		
		return "bbs/edit";
	}

	@RequestMapping(path = "edit.do", method = RequestMethod.POST) /* jsp에서 모델이름을 bbsVo로 맞춰줬기때문에 bbsVo로 설정 */
	public String edit(@ModelAttribute("bbsVo") @Valid BbsVo vo, BindingResult bindingResult) { /* 파라미터 모델 이름을 bbsVo로 사용 */
//		if(bindingResult.hasFieldErrors("bbsName") || bindingResult.hasFieldErrors("bbsPoint")) {
//			//회원정보 변경시에는 이름과 포인트만 검증
//			return "bbs/edit";
//		}
		
		int num = bbsService.update(vo); /* 반환받은 레코드수를 num에 넣는다. */
		
		return "redirect:/bbs/list.do";
	}
	@RequestMapping(path = "del.do", method =  RequestMethod.GET)
	public String del(BbsVo vo) { /* jsp의 파라미터 값을 vo로 받는다. */	
		
		int num = bbsService.delete(vo);	
		
		return "redirect:/bbs/list.do";
	}
//	@ResponseBody 리턴값이 그대로 응답으로 가야하니까 이 어노테이션을 붙혀준다.
	@RequestMapping(path = "check.do", method =  RequestMethod.POST)
	@ResponseBody 
	public Map<String, Object> checkId(BbsVo vo) { 
		
		BbsVo mvo = bbsService.select(vo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", mvo==null); //사용가능한 경우 result:true,불가능한 경우 result:false
		return map;
	}
	
}







