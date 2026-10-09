package backend.repository.impl;

import backend.repository.IAccountRepository;
import entity.*;
import utils.JDBCUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryImpl implements IAccountRepository {

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT a.account_id, a.email, a.username, a.full_name, a.create_date, " +
                "d.department_id, d.department_name, " +
                "p.position_id, p.position_name " +
                "FROM `account` a " +
                "LEFT JOIN `department` d ON a.department_id = d.department_id " +
                "LEFT JOIN `position` p ON a.position_id = p.position_id";

        try {
            Connection conn = JDBCUtils.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int accId = rs.getInt("account_id");
                String email = rs.getString("email");
                String uname = rs.getString("username");
                String fullName = rs.getString("full_name");
                Date dateSql = rs.getDate("create_date");
                LocalDate createDate = (dateSql != null) ? dateSql.toLocalDate() : null;

                int depId = rs.getInt("department_id");
                String depName = rs.getString("department_name");
                Department department = new Department(depId, depName);

                int posId = rs.getInt("position_id");
                String posNameStr = rs.getString("position_name");
                PositionName posName = (posNameStr != null) ? PositionName.valueOf(posNameStr) : null;
                Position position = new Position(posId, posName);

                accounts.add(new Account(accId, email, uname, fullName, department, position, createDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection();
        }
        return accounts;
    }

    @Override
    public List<Account> findByUsername(String username) {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT a.account_id, a.email, a.username, a.full_name, a.create_date, " +
                "d.department_id, d.department_name, " +
                "p.position_id, p.position_name " +
                "FROM `account` a " +
                "LEFT JOIN `department` d ON a.department_id = d.department_id " +
                "LEFT JOIN `position` p ON a.position_id = p.position_id " +
                "WHERE a.username LIKE ?";

        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + username + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int accId = rs.getInt("account_id");
                String email = rs.getString("email");
                String uname = rs.getString("username");
                String fullName = rs.getString("full_name");
                Date dateSql = rs.getDate("create_date");
                LocalDate createDate = (dateSql != null) ? dateSql.toLocalDate() : null;

                int depId = rs.getInt("department_id");
                String depName = rs.getString("department_name");
                Department department = new Department(depId, depName);

                int posId = rs.getInt("position_id");
                String posNameStr = rs.getString("position_name");
                PositionName posName = (posNameStr != null) ? PositionName.valueOf(posNameStr) : null;
                Position position = new Position(posId, posName);

                accounts.add(new Account(accId, email, uname, fullName, department, position, createDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection();
        }
        return accounts;
    }

    @Override
    public boolean create(String email, String username, String fullName, int depId, int posId) {
        String sql = "INSERT INTO `account` (email, username, full_name, department_id, position_id, create_date) " +
                "VALUES (?, ?, ?, ?, ?, NOW())";
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, username);
            stmt.setString(3, fullName);
            stmt.setInt(4, depId);
            stmt.setInt(5, posId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi thêm account: " + e.getMessage());
        } finally {
            JDBCUtils.closeConnection();
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        String sql = "DELETE FROM `account` WHERE account_id = ?";
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection();
        }
        return false;
    }

    @Override
    public boolean updateUsernameById(int id, String newUsername) {
        String sql = "UPDATE `account` SET username = ? WHERE account_id = ?";
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, newUsername);
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
    public boolean existsById(int id) {
        String sql = "SELECT 1 FROM `account` WHERE account_id = ?";
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection();
        }
        return false;
    }

    @Override
    public boolean existsByUsername(String username) {
        String sql = "SELECT 1 FROM `account` WHERE username = ?";
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection();
        }
        return false;
    }

    @Override
    public boolean existsByEmail(String email) {
        String sql = "SELECT 1 FROM `account` WHERE email = ?";
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection();
        }
        return false;
    }
}