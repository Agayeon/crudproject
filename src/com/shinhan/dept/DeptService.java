package com.shinhan.dept;

import java.util.List;

import lombok.extern.java.Log;


@Log
//1. getDepartmentById(): 부서 ID로 부서 정보를 조회.
//2. getAllDepartments(): 모든 부서 정보를 조회.
//3. insertDepartment(): 부서 정보를 추가.
//4. updateDepartment(): 부서 정보를 수정.
//5. deleteDepartment(): 부서 정보를 삭제.
public class DeptService {
	DeptDAO deptDAO = new DeptDAO();

	// 모든 정보 출력
	public List<DeptDTO> selectALL() {
		List<DeptDTO> deptlist = deptDAO.selectALL();
		log.info("DeptService에서 로그출력:" + deptlist.size() + "건");
		// System.out.println("DeptService에서 로그출력:" + deptlist.size() + "건");
		return deptlist;
	}	
		
		// 2.Select(Read)..상세보기
	public DeptDTO selectById(int deptid) {
		DeptDTO dept = deptDAO.selectById(deptid);
		log.info("DeptService에서 로그출력:" + dept.toString());
		return dept;
	}

	// 3.Inert
	public int insertDept(DeptDTO dept) {
		int result = deptDAO.insertDept(dept);
		log.info("DeptService에서 로그출력:" + result + "건 insert");
		return result;
	}

	// 4.Update
	public int updateDept(DeptDTO dept) {
		int result = deptDAO.updateDept(dept);
		log.info("DeptService에서 로그출력:" + result + "건 update");
		return result;
	}

	// 5.Delete
	public int deleteDept(int deptid) {
		int result = deptDAO.deleteDept(deptid);
		log.info("DeptService에서 로그출력:" + result + "건 delete");
		return result;
	}
}