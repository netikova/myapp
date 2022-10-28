package com.exam.myapp.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*이 자체도 spring에 등록되어야 하니깐 service 역할을 하니까 @service를 등록한다.*/
@Service
public class MemberServiceImpl implements MemberService {
	/* 스프링에 등록된 Dao객체를 받기위해 @Autowired등록 */
    @Autowired
	private MemberDao memberDao;
	@Override
	public List<MemberVo> selectList() {
		// TODO Auto-generated method stub
		return memberDao.selectList();
		/* Dao에서 값을 받아서 Controller에 전달 */
	}
	@Override
	public int insert(MemberVo vo) {
		
		return memberDao.insert(vo); /* vo객체를 memberDao의 insert에 전달 */
	}
	@Override
	public MemberVo select(MemberVo vo) {	
		
		return memberDao.select(vo);
	}
	@Override
	public int update(MemberVo vo) {
		
		return memberDao.update(vo);
	}
	@Override
	public int delete(MemberVo vo) {		
		return memberDao.delete(vo);
	}
	@Override
	public MemberVo selectLogin(MemberVo vo) {		
		return memberDao.selectLogin(vo);
	}
	

}
