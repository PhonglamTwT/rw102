package backend;

import entity.Department;
import utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLDepartment implements IQLDepartment {
    private final Scanner sc = new Scanner(System.in);

    @Override
    public void danhSachDepartment() {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM `department`";

        try (Connection conn = JDBCUtils.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("department_id");
                String name = rs.getString("department_name");
                departments.add(new Department(id, name));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi CSDL: " + e.getMessage());
            return;
        }

        inBangDepartment(departments);
    }

    private void inBangDepartment(List<Department> departments) {
        System.out.println("+-----+-------------------------+");
        System.out.printf("|%-5s|%-25s|\n", "ID", "Department Name");
        System.out.println("+-----+-------------------------+");
        for (Department dep : departments) {
            System.out.printf("|%-5d|%-25s|\n", dep.getDepartmentId(), dep.getDepartmentName());
        }
        System.out.println("+-----+-------------------------+");
    }

    @Override
    public void timKiemDepartment() {
        System.out.println("\n==== TÌM KIẾM DEPARTMENT THEO TÊN ====");
        System.out.print("Nhập tên department cần tìm: ");
        String nameInput = sc.nextLine().trim();

        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM `department` WHERE department_name LIKE ?";

        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nameInput + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("department_id");
                    String name = rs.getString("department_name");
                    departments.add(new Department(id, name));
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi CSDL: " + e.getMessage());
            return;
        }

        if (departments.isEmpty()) {
            System.out.println("Không tìm thấy department nào!");
        } else {
            inBangDepartment(departments);
        }
    }

    @Override
    public void themMoiDepartment() {
        System.out.println("\n==== THÊM MỚI DEPARTMENT ====");
        System.out.print("Nhập tên Department mới: ");
        String name = sc.nextLine().trim();

        String sql = "INSERT INTO `department` (department_name) VALUES (?)";

        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Thêm mới Department thành công!");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi thêm department: " + e.getMessage());
        }
    }

    @Override
    public void xoaDepartmentTheoId() {
        System.out.println("\n==== XÓA DEPARTMENT THEO ID ====");
        System.out.print("Nhập ID Department cần xóa: ");
        int id = Integer.parseInt(sc.nextLine().trim());

        String sql = "DELETE FROM `department` WHERE department_id = ?";

        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Xóa Department ID " + id + " thành công!");
            } else {
                System.out.println("Không tìm thấy Department ID tương ứng!");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi xóa department: " + e.getMessage());
        }
    }

    @Override
    public void updateTenDepartmentTheoId() {
        System.out.println("\n==== UPDATE TÊN DEPARTMENT THEO ID ====");
        System.out.print("Nhập ID Department cần update: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Nhập tên Department mới: ");
        String newName = sc.nextLine().trim();

        String sql = "UPDATE `department` SET department_name = ? WHERE department_id = ?";

        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newName);
            stmt.setInt(2, id);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Update tên Department thành công!");
            } else {
                System.out.println("Không tìm thấy Department ID tương ứng!");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi update department: " + e.getMessage());
        }
    }

}