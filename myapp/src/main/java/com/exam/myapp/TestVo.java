package com.exam.myapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter //lombok을 이용하여, 클래스의 모든 필드(멤버변수)의 getXxx(), setXxx() 메서드 자동 생성
public class TestVo {	
    private int x;
    private int y;   
    private String lunch;
    private String dinner;
    private List<String> food = new ArrayList<String>(); //null값일때도 실행하게 하려면 초기값을 준다.   
    private List<LicenseVo> license;
    
	
}
