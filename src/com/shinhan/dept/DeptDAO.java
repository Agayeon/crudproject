package com.shinhan.dept;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shinhan.day17.DBUtil;

public class DeptDAO {
    // DB 연결/해제
    Connection conn;
    // SQL문을 DB에 전송
    Statement st;
    PreparedStatement pst;
    ResultSet rs;
    int resultCount;

    static final String SQL_SELECT_ALL = "SELECT * FROM departments";
    static final String SQL_SELECT_DETAIL = "SELECT * FROM departments WHERE department_id = ?";
    static final String INSERT = "INSERT INTO departments VALUES (?, ?, ?, ?)";
    static final String UPDATE = "UPDATE departments SET department_name = ?, manager_id = ?, location_id = ? WHERE department_id = ?";
    static final String DELETE = "DELETE FROM departments WHERE department_id = ?";

    // 1. 모든 부서 조회
    public List<DeptDTO> selectALL() {
        List<DeptDTO> deptlist = new ArrayList<>();
        conn = DBUtil.getConnection();
        try {
            st = conn.createStatement();
            rs = st.executeQuery(SQL_SELECT_ALL);
            while (rs.next()) {
                DeptDTO dept = makeDept(rs);
                deptlist.add(dept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return deptlist;
    }

    // 2. 부서번호로 조회
    public DeptDTO selectById(int deptId) {
        DeptDTO dept = null;
        conn = DBUtil.getConnection();
        try {
            pst = conn.prepareStatement(SQL_SELECT_DETAIL);
            pst.setInt(1, deptId);
            rs = pst.executeQuery();
            if (rs.next()) {
                dept = makeDept(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, pst, rs);
        }
        return dept;
    }

    // 3. 부서 입력
    public int insertDept(DeptDTO dept) {
        conn = DBUtil.getConnection();
        try {
            pst = conn.prepareStatement(INSERT);
            pst.setInt(1, dept.getDepartment_id());
            pst.setString(2, dept.getDepartment_name());
            pst.setInt(3, dept.getManager_id());
            pst.setInt(4, dept.getLocation_id());
            resultCount = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, pst, null);
        }
        return resultCount;
    }

    // 4. 부서 수정
    public int updateDept(DeptDTO dept) {
        conn = DBUtil.getConnection();
        try {
            pst = conn.prepareStatement(UPDATE);
            pst.setString(1, dept.getDepartment_name());
            pst.setInt(2, dept.getManager_id());
            pst.setInt(3, dept.getLocation_id());
            pst.setInt(4, dept.getDepartment_id());
            resultCount = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, pst, null);
        }
        return resultCount;
    }

    // 5. 부서 삭제
    public int deleteDept(int departmentId) {
        conn = DBUtil.getConnection();
        try {
            pst = conn.prepareStatement(DELETE);
            pst.setInt(1, departmentId);
            resultCount = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, pst, null);
        }
        return resultCount;
    }

    // 부서 정보 ResultSet → DeptDTO 변환
    private DeptDTO makeDept(ResultSet rs) throws SQLException {
        return DeptDTO.builder()
                .department_id(rs.getInt("department_id"))
                .department_name(rs.getString("department_name"))
                .manager_id(rs.getInt("manager_id"))
                .location_id(rs.getInt("location_id"))
                .build();
    }
}
