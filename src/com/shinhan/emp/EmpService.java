package com.shinhan.emp;

import java.util.List;

// Service : business logic 수행
// 1) 이체 업무 : (인출하기, 입금하기)
// 2) 비밀번호 암호화
public class EmpService {
	
	EmpDAO empDAO = new EmpDAO();
	
	public EmpDTO execute_sp(int empid) {
		return empDAO.execute_sp(empid);
	}
	public List<EmpDTO> selectAll() {
		
		return empDAO.selectALL();
	}
	public int empInsert(EmpDTO emp) {
		return empDAO.empInsert(emp);
	}
	public int empDeleteById(int empid) {
		return empDAO.empDeleteById(empid);
	}
	
	public EmpDTO selectById(int empid) {
		return empDAO.selectById(empid);
	}
	
	public List<EmpDTO> selectBYDept(int deptid) {
		return empDAO.selectBYDept(deptid);
	}
	
	public List<EmpDTO> selectByJob(String jobid) {
		return empDAO.selectByJob(jobid);
	}
	public List<EmpDTO> selectByJobAndDept(String jobid, int deptid) {
		return empDAO.selectByJobAndDept(jobid, deptid);
	}

	public List<EmpDTO> selectByCondition(Integer[] deptid, String jobid, int salary, String hdate) {
		
		return empDAO.selectByCondition(deptid, jobid, salary, hdate); 
	}
	public int empUpdate(EmpDTO emp) {
		// TODO Auto-generated method stub
		return 0;
	}
}
