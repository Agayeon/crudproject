package com.shinhan.emp;

import java.sql.Date;
import java.util.List;

// data를 display 하려는 목적, 나중에 웹으로 변경되면 JSP로 만들 예정
public class EmpView {
 
	// 여러 건 출력
	public static void display(List<EmpDTO> emplist) {
		//직원 여러 건 출력
        System.out.println("📋 직원 여러 건 조회");
        emplist.stream().forEach(emp->System.out.println(emp));
		
	} 
	
	public static void display(EmpDTO emp) {
		if(emp == null) {
			display("해당하는 직원이 존재하지 않습니다.");
		}
	}
	
	public static void display(String message) {
		System.out.println("🔔 알림: " + message + "\n");
	}
	
	private Date convertToDate(Date convertToDate) {
	    return convertToDate;
	}
}
