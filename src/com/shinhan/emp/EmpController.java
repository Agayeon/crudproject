package com.shinhan.emp;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.shinhan.common.CommonControllerInterface;

// MVC2 모델
// FrontController -> Controller 선택 -> Service -> DAO -> DB
public class EmpController implements CommonControllerInterface {
		static Scanner sc = new Scanner(System.in);
		static EmpService empService = new EmpService();
	public void execute() { // -> Servlet으로 만듬
		boolean isStop = false;
		while(!isStop) {
			menuDisplay();
			int job = sc.nextInt();
			switch(job) {
			case 1 -> { f_selectAll(); } // 모든 직원 조회
			case 2 -> { f_selectById(); } // 특정 직원 조회 (직원 번호)
			case 3 -> { f_selectByDept(); } // 특정 부서 조회 (부서 ID)
			case 4 -> { f_selectByJob(); } // 특정 직책 조회 (직책 ID)
			case 5 -> { f_selectByJobAndDept(); } // 직책 및 부서로 직원 조회
			case 6 -> { f_selectByCondition(); } // 조건에 맞는 직원 조회 (부서, 직책, 급여, 입사일)
			case 7 -> { f_deleteByEmpId(); } // 직원 삭제 (직원 번호)
			case 8 -> { f_insertEmp(); } // 신규 직원 삽입 (직원 정보 입력)
			case 9 -> { f_UpdateByEmpId(); } // 직원 정보 수정 (직원 번호)
			case 10 -> { f_sp_call(); }
			case 99 -> { isStop = true; } // 종료

			}
		}
		System.out.println("-----Good Bye-----");
	}
	private static void f_sp_call() {
		System.out.print("조회할 직원 ID>>");
		int employee_id = sc.nextInt();
		EmpDTO emp = empService.execute_sp(employee_id);
		String message = "해당직원을 존재하지 않습니다.";
		if(emp!=null) {
			message = emp.getEmail() + "---" + emp.getSalary();
		}
		EmpView.display(message);
		
	}
	private static void f_UpdateByEmpId() {
		System.out.print("수정할 직원ID>>");
		int employee_id = sc.nextInt();
		EmpDTO exist_emp = empService.selectById(employee_id);
		if (exist_emp == null) {
			EmpView.display("존재하지않는 직원입니다.");
			return;
		}
		EmpView.display("=========존재는 직원정보입니다.========");
		EmpView.display(exist_emp);
		int result = empService.empUpdate(makeEmp(employee_id));
		EmpView.display(result + "건수정");
	}
	private static void f_insertEmp() {
		System.out.print("신규직원ID>>");
		int employee_id = sc.nextInt();

		int result = empService.empInsert(makeEmp2(employee_id));
		EmpView.display(result + "건입력");
	}
	static EmpDTO makeEmp(int employee_id) {
		System.out.print("first_name>>");
		String first_name = sc.next();
		
		System.out.print("last_name>>");
		String last_name = sc.next();
		
		System.out.print("email>>");
		String email = sc.next();
		
		System.out.print("phone_number>>");
		String phone_number = sc.next();
		
		System.out.print("hdate(yyyy-MM-dd)>>");
		String hdate = sc.next();
		Date hire_date = null;
		if(!hdate.equals("0"))
		   hire_date = DateUtil.convertToSQLDate(DateUtil.convertToDate(hdate));
		
		System.out.print("job_id(FK:IT_PROG)>>");
		String job_id = sc.next();
		
		System.out.print("salary>>");
		Double salary = sc.nextDouble();
		
		System.out.print("commission_pct(0.2)>>");
		Double commission_pct = sc.nextDouble();
		System.out.print("manager_id(FK:100)>>");
		Integer manager_id = sc.nextInt();
		System.out.print("department_id(FK:60,90)>>");
		Integer department_id = sc.nextInt();
		
		if(first_name.equals("0")) first_name = null;
		if(last_name.equals("0")) last_name = null;
		if(email.equals("0")) email = null;
		if(phone_number.equals("0")) phone_number = null;
		if(job_id.equals("0")) job_id = null;
		if(salary==0) salary = null;
		if(commission_pct==0) commission_pct = null;
		if(manager_id==0) manager_id = null;
		if(department_id==0) department_id = null;
		
		
		
		EmpDTO emp = EmpDTO.builder().commission_pct(commission_pct).department_id(department_id).email(email)
				.employee_id(employee_id).first_name(first_name).hire_date(hire_date).job_id(job_id)
				.last_name(last_name).manager_id(manager_id).phone_number(phone_number).salary(salary).build();
		System.out.println(emp);
		
		return emp;
	}

	static EmpDTO makeEmp2(int employee_id) {
	    
		System.out.println("firstname>>");
	    String first_name = sc.next();
	    
		System.out.println("lastname>>");
	    String last_name = sc.next();
	    
		System.out.println("email>>");
	    String email = sc.next();
	    
		System.out.println("phone_number>>");
	    String phone_number = sc.next();
	    
		System.out.println("hdate(yyyy-mm-dd)>>");
		String hdate = sc.next();
	    Date hire_date = DateUtil.convertToDate(hdate);
		
	    System.out.println("job_id>>");
	    String job_id = sc.next();
	    
		System.out.println("salary>>");
		double salary = sc.nextDouble();
	    
		System.out.println("commission_pct>>");
	    double commission_pct = sc.nextDouble();
	    
		System.out.println("manager_id>>");
	    int manager_id = sc.nextInt();
	    
		System.out.println("department_id>>");
	    int department_id = sc.nextInt();
	    EmpDTO emp = EmpDTO.builder()
	    		.commission_pct(commission_pct)
	    		.department_id(department_id)
	    		.email(email)
	    		.employee_id(employee_id)
	    		.first_name(first_name)
	    		.hire_date(hire_date)
	    		.job_id(job_id)
	    		.last_name(last_name)
	    		.manager_id(manager_id)
	    		.phone_number(phone_number)
	    		.salary(salary)
	    		.build();
	    System.out.println(emp);
	    int result = empService.empInsert(emp);
	    EmpView.display(result+ "건 입력");
	    return emp;
	    
	}

	private static void f_deleteByEmpId() {
		System.out.println("삭제할 직원ID>>");
		int empid = sc.nextInt();
		int result = empService.empDeleteById(empid);
		EmpView.display(result+"건 삭제");
		
	}

	private static void f_selectByCondition() {
		  // =부서, like 직책 >= 급여, =입사일
	    System.out.println("조회할 부서(쉼표로 구분, 예: 10,20,30)>>");
	    String str_deptid = sc.next(); // 예: "10,20,30"

	    // 문자열을 정수 배열로 변환
	    Integer[] deptArr = Arrays.stream(str_deptid.split(","))
	                            .map(String::trim)
	                            .map(Integer::parseInt)
	                            .toArray(Integer[]::new);

	    System.out.println("조회할 직책 ID >>");
	    String jobid = sc.next();

	    System.out.println("조회할 Salary(이상) >>");
	    int salary = sc.nextInt();

	    System.out.println("조회할 입사일(yyyy-MM-dd) >>");
	    String hdate = sc.next();

	    // 서비스 호출
	    List<EmpDTO> emplist = empService.selectByCondition(deptArr, jobid, salary, hdate);
	    EmpView.display(emplist);
	}
//	EmpDTO(employee_id=206, first_name=William, last_name=Gietz, email=WGIETZ, phone_number=515.123.8181, hire_date=2002-06-07, job_id=AC_ACCOUNT, salary=8300.0, commission_pct=0.0, manager_id=205, department_id=110)

	private static void f_selectByJobAndDept() {
		System.out.println("조회할 직책ID, 부서ID>> "); //IT_PROG,60
		String data = sc.next();
		String[] arr = data.split(",");
		String jobid = arr[0];
		int deptid = Integer.parseInt(arr[1]);
		 List<EmpDTO> emplist = empService.selectByJobAndDept(jobid, deptid);
		
	}

	private static void f_selectByJob() {
		System.out.println("조회할 직책 ID >>");
		 String job = sc.next();
		 List<EmpDTO> emplist = empService.selectByJob(job);
		    EmpView.display(emplist);
		}

	private static void f_selectByDept() {
		System.out.println("조회할 부서 ID >>");
		int deptid = sc.nextInt();
		List<EmpDTO> emplist = empService.selectBYDept(deptid);
		EmpView.display(emplist);
		
	}


	private static void f_selectById() {
	    System.out.print("조회할 직원번호 입력> ");
	    int empid = sc.nextInt();
	    EmpDTO emp = empService.selectById(empid);
	    EmpView.display(emp);
	}

	private static void f_selectAll() {
		List<EmpDTO> emplist = empService.selectAll();
		EmpView.display(emplist);
	}


	private static void menuDisplay() {
		System.out.println("----------------------");
		System.out.println("1. 모두조회 2. 조회(직원번호) 3. 조회(부서코드) 4. 조회(직책) ");
		System.out.println("5. 조회(직책 및 부서) 6. 조건 검색(부서, 직책, 급여, 입사일) 7. 삭제 8. 삽입 ");
		System.out.println("9. 수정 10.sp호출 99. 종료");
		System.out.println("----------------------");
		System.out.print("작업 선택> ");
		
	}

}
