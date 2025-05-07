package com.shinhan.dept;

import com.shinhan.common.CommonControllerInterface;

import java.util.List;
import java.util.Scanner;

public class DeptController implements CommonControllerInterface{
	static Scanner sc = new Scanner(System.in);
	static DeptService deptService = new DeptService();
@Override
	public void execute()  {
        boolean isStop = false;
        while (!isStop) {
            DeptView.menuDisplay();
            String job = sc.nextLine();
            switch (job) {
                case "1" -> all();
                case "2" -> deptSelect();
                case "3" -> insert();
                case "4" -> update();
                case "5" -> delete();
                case "9" -> isStop = true;
                default -> System.out.println("잘못된 입력입니다. 다시 선택하세요.");
            }
        }
        DeptView.display("프로그램 종료");
    }

	private static void all() {
		List<DeptDTO> list = deptService.selectALL();
		DeptView.display(list);
	}

	private static void deptSelect() {
		System.out.print("조회할 부서번호 입력>> ");
		int deptid = Integer.parseInt(sc.nextLine());
		DeptDTO dept = deptService.selectById(deptid);
		if (dept != null) {
			DeptView.display(List.of(dept)); // 하나라도 리스트로 넘기자
		} else {
			DeptView.display("해당 부서가 존재하지 않습니다.");
		}
	}

	private static void insert() {
		try {
			System.out.print("부서번호>> ");
			int deptid = Integer.parseInt(sc.nextLine());
			System.out.print("부서이름>> ");
			String deptname = sc.nextLine();
			System.out.print("매니저번호>> ");
			int mgrid = Integer.parseInt(sc.nextLine());
			System.out.print("지역번호>> ");
			int locid = Integer.parseInt(sc.nextLine());

			DeptDTO dept = DeptDTO.builder().department_id(deptid).department_name(deptname).manager_id(mgrid)
					.location_id(locid).build();

			int result = deptService.insertDept(dept);
			if (result > 0) {
				DeptView.display("부서 등록 성공!");
			} else {
				DeptView.display("부서 등록 실패...");
			}
		} catch (Exception e) {
			DeptView.display("입력 중 오류 발생: " + e.getMessage());
		}
	}

	private static void update() {
		try {
			System.out.print("수정할 부서번호 입력>> ");
			int deptid = Integer.parseInt(sc.nextLine());
			DeptDTO existing = deptService.selectById(deptid);

			if (existing == null) {
				DeptView.display("해당 부서가 존재하지 않습니다.");
				return;
			}

			System.out.print("새 부서이름 (현재: " + existing.getDepartment_name() + ")>> ");
			String deptname = sc.nextLine();
			System.out.print("새 매니저번호 (현재: " + existing.getManager_id() + ")>> ");
			int mgrid = Integer.parseInt(sc.nextLine());
			System.out.print("새 지역번호 (현재: " + existing.getLocation_id() + ")>> ");
			int locid = Integer.parseInt(sc.nextLine());

			DeptDTO updatedDept = DeptDTO.builder().department_id(deptid).department_name(deptname).manager_id(mgrid)
					.location_id(locid).build();

			int result = deptService.updateDept(updatedDept);
			if (result > 0) {
				DeptView.display("부서 수정 성공!");
			} else {
				DeptView.display("부서 수정 실패...");
			}
		} catch (Exception e) {
			DeptView.display("입력 중 오류 발생: " + e.getMessage());
		}
	}

	private static void delete() {
		try {
			System.out.print("삭제할 부서번호 입력>> ");
			int deptid = Integer.parseInt(sc.nextLine());

			DeptDTO existing = deptService.selectById(deptid);
			if (existing == null) {
				DeptView.display("해당 부서가 존재하지 않습니다.");
				return;
			}

			int result = deptService.deleteDept(deptid);
			if (result > 0) {
				DeptView.display("부서 삭제 성공!");
			} else {
				DeptView.display("부서 삭제 실패...");
			}
		} catch (Exception e) {
			DeptView.display("입력 중 오류 발생: " + e.getMessage());
		}
	}
}
