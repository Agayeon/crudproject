package com.shinhan.day17;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Crud : Create Read Update Delete
// Read = select
public class CrudTest {
	public static void main(String[] args) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = """
				select DEPARTMENT_ID, max(salary), min(salary) from employees
				group by DEPARTMENT_ID
				having max(salary) != min(salary)
				""";
		
		conn = DBUtil.getConnection(); // 연결
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int a = rs.getInt(1); //DB는 0부터 아니고 1부터 사용
				int b = rs.getInt(2);
				int c = rs.getInt(3);
				System.out.println(a+"\t"+ b+ "\t" + c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
	}
	public static void f_2() {
		String url= "jdbc:oracle:thin:@localhost:1521:xe", userid="hr", userpass="1234";
		Connection conn = null;
		Statement st =null;
		ResultSet rs = null;
		String sql = """
				select DEPARTMENT_ID, count(*) from employees
				group by DEPARTMENT_ID
				having count(*) >= 5
				order by 2 desc
				""";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, userid, userpass);
			st = conn.createStatement();
			rs = st.executeQuery(sql); //rs 는 table(표)와 비슷하다 = 행과 열이 있다
			while(rs.next()) {
				int depid = rs.getInt(1);
				int cnt = rs.getInt(2);
				System.out.println("부서코드" + depid + "\t인원수" + cnt);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close(); //작은 거 먼저 닫아야 함
				if(st!=null) st.close();
				if(conn!=null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}	
		}
	}

	public static void f_1() throws ClassNotFoundException, SQLException {
		// 1. JDBC DRIVER 준비(class path에 추가해야함)
		// 2. JDBC Driver load
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("2. JDBC Driver load 성공");
		// 3. Connection
		String url= "jdbc:oracle:thin:@localhost:1521:xe", userid="hr", userpass="1234";
		
		Connection conn = DriverManager.getConnection(url, userid, userpass);
		System.out.println("3. Connection 성공");
		// 4. SQL문 보낼 통로 얻기
		Statement st = conn.createStatement(); // 통로 만들어짐
		System.out.println("4. SQL 보낼 통로 얻기 성공");
		//5. SQL문 생성, 실행
		String sql = """
				select * from employees
				where DEPARTMENT_ID = 80
				""";
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()) {
		    int empid = rs.getInt("employee_id"); // 여기 수정!
		    String fname = rs.getString("first_name");
		    Date hdate = rs.getDate("hire_date");
		    double comm = rs.getDouble("commission_pct");
		    System.out.printf("직원 번호는 %d이고 이름은 %s이고 날짜는 %s, 커미션은 %3.1f입니다.\n", empid, fname, hdate, comm);
		}
		rs.close();
		st.close();
		conn.close();
	}
}