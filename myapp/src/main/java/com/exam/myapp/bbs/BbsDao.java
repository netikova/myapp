package com.exam.myapp.bbs;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BbsDao {
    public List<BbsVo> selectList();

	public int insert(BbsVo vo);

	public BbsVo select(BbsVo vo);
	/* vo객체 안에 있는 memId를 실행해서 MemberVo에 반환해준다. */

	public int update(BbsVo vo);
	/* 몇명의 회원이 업데이트 됫는지 반환값int으로 돌려준다. */
	/* vo에 있는 변수들이 xml 속성값으로 값을 변경해준다. */

	public int delete(BbsVo vo);
	

}
