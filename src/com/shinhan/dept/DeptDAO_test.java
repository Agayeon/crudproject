package com.shinhan.dept;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeptDAO_test {
    private Connection conn;

    public DeptDAO_test(Connection connection) {
        this.conn = connection;
    }

    // 1. 부서 ID로 부서 조회
    public DeptDTO getDepartmentById(int departmentId) throws SQLException {
        String query = "SELECT * FROM departments WHERE department_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, departmentId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapRowToDeptDTO(rs);
            }
        }
        return null;
    }

    // 2. 모든 부서 조회
    public List<DeptDTO> getAllDepartments() throws SQLException {
        String query = "SELECT * FROM departments";
        List<DeptDTO> departments = new ArrayList<>();

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                departments.add(mapRowToDeptDTO(rs));
            }
        }
        return departments;
    }

    // 3. 부서 추가
    public int insertDepartment(DeptDTO dept) throws SQLException {
        String query = "INSERT INTO departments (department_id, department_name, manager_id, location_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, dept.getDepartment_id());
            stmt.setString(2, dept.getDepartment_name());
            stmt.setInt(3, dept.getManager_id());
            stmt.setInt(4, dept.getLocation_id());
            return stmt.executeUpdate();
        }
    }

    // 4. 부서 수정
    public int updateDepartment(DeptDTO dept) throws SQLException {
        String query = "UPDATE departments SET department_name = ?, manager_id = ?, location_id = ? WHERE department_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, dept.getDepartment_name());
            stmt.setInt(2, dept.getManager_id());
            stmt.setInt(3, dept.getLocation_id());
            stmt.setInt(4, dept.getDepartment_id());
            return stmt.executeUpdate();
        }
    }

    // 5. 부서 삭제
    public int deleteDepartment(int departmentId) throws SQLException {
        String query = "DELETE FROM departments WHERE department_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, departmentId);
            return stmt.executeUpdate();
        }
    }

    // 결과를 DeptDTO 객체로 매핑
    private DeptDTO mapRowToDeptDTO(ResultSet rs) throws SQLException {
        return DeptDTO.builder()
                .department_id(rs.getInt("department_id"))
                .department_name(rs.getString("department_name"))
                .manager_id(rs.getInt("manager_id"))
                .location_id(rs.getInt("location_id"))
                .build();
    }
}
