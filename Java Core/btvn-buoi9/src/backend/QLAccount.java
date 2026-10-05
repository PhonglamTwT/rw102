package backend;

import entity.*;
import utils.JDBCUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLAccount implements IQLAccount {
    private final Scanner sc = new Scanner(System.in);
    private final IQLDepartment iqlDepartment = new QLDepartment();

    @Override
    public void danhSachAccount() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT a.account_id, a.email, a.username, a.full_name, a.create_date, " +
                "d.department_id, d.department_name, " +
                "p.position_id, p.position_name " +
                "FROM `account` a " +
                "LEFT JOIN `department` d ON a.department_id = d.department_id " +
                "LEFT JOIN `position` p ON a.position_id = p.position_id";

        try (Connection conn = JDBCUtils.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

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
            System.err.println("Lỗi CSDL: " + e.getMessage());
            return;
        }

        inBangAccount(accounts);
    }

    @Override
    public void timKiemTheoUsername() {
        System.out.println("\n==== TÌM KIẾM ACCOUNT THEO USERNAME ====");
        System.out.print("Nhập username cần tìm: ");
        String unameInput = sc.nextLine().trim();

        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT a.account_id, a.email, a.username, a.full_name, a.create_date, " +
                "d.department_id, d.department_name, " +
                "p.position_id, p.position_name " +
                "FROM `account` a " +
                "LEFT JOIN `department` d ON a.department_id = d.department_id " +
                "LEFT JOIN `position` p ON a.position_id = p.position_id " +
                "WHERE a.username LIKE ?";

        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + unameInput + "%");
            try (ResultSet rs = stmt.executeQuery()) {
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
            }
        } catch (SQLException e) {
            System.err.println("Lỗi CSDL: " + e.getMessage());
            return;
        }

        if (accounts.isEmpty()) {
            System.out.println("Không tìm thấy account nào!");
        } else {
            inBangAccount(accounts);
        }
    }

    @Override
    public void themMoiAccount() {
        System.out.println("\n==== THÊM MỚI ACCOUNT ====");
        System.out.print("Nhập Email: ");
        String email = sc.nextLine().trim();
        System.out.print("Nhập Username: ");
        String username = sc.nextLine().trim();
        System.out.print("Nhập Full Name: ");
        String fullName = sc.nextLine().trim();
        System.out.println("\n--- DANH SÁCH DEPARTMENT HIỆN CÓ ---");
        iqlDepartment.danhSachDepartment();
        System.out.print("Nhập Department ID: ");
        int depId = Integer.parseInt(sc.nextLine().trim());
        System.out.println("\n--- DANH SÁCH POSITION HIỆN CÓ ---");
        hienThiDanhSachPosition();
        System.out.print("Nhập Position ID: ");
        int posId = Integer.parseInt(sc.nextLine().trim());

        String sql = "INSERT INTO `account` (email, username, full_name, department_id, position_id, create_date) " +
                "VALUES (?, ?, ?, ?, ?, NOW())";

        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, username);
            stmt.setString(3, fullName);
            stmt.setInt(4, depId);
            stmt.setInt(5, posId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Thêm mới account thành công!");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi thêm account: " + e.getMessage());
        }
    }

    private void hienThiDanhSachPosition() {
        String sql = "SELECT * FROM `position`";
        try (Connection conn = JDBCUtils.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("+----+--------------------+");
            System.out.printf("|%-4s|%-20s|\n", "ID", "Position Name");
            System.out.println("+----+--------------------+");
            while (rs.next()) {
                System.out.printf("|%-4d|%-20s|\n", rs.getInt("position_id"), rs.getString("position_name"));
            }
            System.out.println("+----+--------------------+");
        } catch (SQLException e) {
            System.err.println("Lỗi lấy danh sách Position: " + e.getMessage());
        }
    }

    @Override
    public void xoaAccountTheoUsername() {
        System.out.println("\n==== XÓA ACCOUNT THEO USERNAME ====");
        System.out.print("Nhập Username cần xóa: ");
        String username = sc.nextLine().trim();

        String sql = "DELETE FROM `account` WHERE username = ?";

        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Đã xóa thành công account có username: " + username);
            } else {
                System.out.println("Không tìm thấy account để xóa!");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi xóa account: " + e.getMessage());
        }
    }

    @Override
    public void updateFullNameTheoUsername() {
        System.out.println("\n==== UPDATE FULLNAME THEO USERNAME ====");
        System.out.print("Nhập Username cần update: ");
        String username = sc.nextLine().trim();
        System.out.print("Nhập FullName mới: ");
        String newFullName = sc.nextLine().trim();

        String sql = "UPDATE `account` SET full_name = ? WHERE username = ?";

        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newFullName);
            stmt.setString(2, username);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Update Full Name thành công!");
            } else {
                System.out.println("Không tìm thấy username tương ứng!");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi update: " + e.getMessage());
        }
    }

    private void inBangAccount(List<Account> accounts) {
        System.out.println("+----+----------------------+-----------------+---------------------+---------------------+------------------+------------+");
        System.out.printf("|%-4s|%-22s|%-17s|%-21s|%-21s|%-18s|%-12s|\n",
                "ID", "Email", "Username", "Full Name", "Department", "Position", "Create Date");
        System.out.println("+----+----------------------+-----------------+---------------------+---------------------+------------------+------------+");
        for (Account acc : accounts) {
            String depName = (acc.getDepartment() != null && acc.getDepartment().getDepartmentName() != null)
                    ? acc.getDepartment().getDepartmentName() : "";
            String posName = (acc.getPosition() != null && acc.getPosition().getPositionName() != null)
                    ? acc.getPosition().getPositionName().name() : "";

            System.out.printf("|%-4d|%-22s|%-17s|%-21s|%-21s|%-18s|%-12s|\n",
                    acc.getAccountId(), acc.getEmail(), acc.getUsername(), acc.getFullName(), depName, posName, acc.getCreateDate());
        }
        System.out.println("+----+----------------------+-----------------+---------------------+---------------------+------------------+------------+");
    }
}