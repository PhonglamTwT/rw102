package backend.repository.impl;

import backend.repository.IDepartmentRepository;
import entity.Department;
import utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRepositoryImpl implements IDepartmentRepository {

    @Override
    public List<Department> findAll() {
        List<Department> list = new ArrayList<>();
        String sql = "SELECT * FROM `department` ORDER BY department_id ASC";
        try {
            Connection conn = JDBCUtils.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(new Department(rs.getInt("department_id"), rs.getString("department_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection();
        }
        return list;
    }

    @Override
    public List<Department> findByName(String name) {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM `department` WHERE department_name LIKE ?";
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                departments.add(new Department(
                        rs.getInt("department_id"),
                        rs.getString("department_name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection();
        }
        return departments;
    }

    @Override
    public boolean create(String name) {
        String sql = "INSERT INTO `department` (department_name) VALUES (?)";
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection();
        }
        return false;
    }

    @Override
    public boolean update(int id, String newName) {
        String sql = "UPDATE `department` SET department_name = ? WHERE department_id = ?";
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, newName);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection();
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        String sql = "DELETE FROM `department` WHERE department_id = ?";
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi: Không thể xóa Department đang được gán cho Account!");
        } finally {
            JDBCUtils.closeConnection();
        }
        return false;
    }
}