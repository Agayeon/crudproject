package com.shinhan.dept;

import java.util.List;

// View(Web에서 JSP로 변경될 예정이다)
public class DeptView {
	
	public static void display(List<DeptDTO> deptlist) {
		if(deptlist.size() == 0) {
			display("존재하는 data없음");
		}
		System.out.println("-----------부서 목록-----------");
		deptlist.stream().forEach(dept-> {
			System.out.println("부서번호 : " + dept.getDepartment_id());
			System.out.println("부서이름 : " + dept.getDepartment_name());
			System.out.println("매니저번호 : " + dept.getManager_id());
			System.out.println("지역코드 : " + dept.getLocation_id());
			System.out.println("-------------------------------------");
		});
	}
	public static void display(String message) {
		System.out.println("알림 : " + message + "\n");
	    System.out.println("   *****     *****   ");
	    System.out.println(" *******   *******  ");
	    System.out.println("********* ********* ");
	    System.out.println(" ***************** ");
	    System.out.println("  *** Good Bye ***  ");
	    System.out.println("   **************** ");
	    System.out.println("    **************  ");
	    System.out.println("     ************   ");
	    System.out.println("      **********    ");
	    System.out.println("       ********     ");
	    System.out.println("        ****        ");
	    System.out.println("         **         ");
	}
	
	public static void menuDisplay() {
		System.out.println("1. 모든 부서 조회");
		System.out.println("2.부서번호로 상세보기");
		System.out.println("3. 부서 입력");
		System.out.println("4. 부서 수정");
		System.out.println("5. 부서 삭제");
		System.out.println("작업 선택 >>");
	}
	
}
