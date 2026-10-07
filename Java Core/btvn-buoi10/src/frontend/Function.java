package frontend;

import backend.controller.AccountController;
import backend.controller.DepartmentController;
import entity.Account;
import entity.Department;
import entity.Position;

import java.util.List;
import java.util.Scanner;

public class Function {
    private AccountController accountController;
    private DepartmentController departmentController;
    private Scanner sc;

    public Function() {
        this.accountController = new AccountController();
        this.departmentController = new DepartmentController();
        this.sc = new Scanner(System.in);
    }

    public void hienThiToanBoAccount() {
        List<Account> accounts = accountController.findAll();
        System.out.println("\n==== HIỂN THỊ TOÀN BỘ ACCOUNT ====");
        inBangAccount(accounts);
    }

    public void timKiemAccountTheoUsername() {
        System.out.println("\n==== TÌM KIẾM ACCOUNT THEO USERNAME ====");
        System.out.print("Nhập username cần tìm: ");
        String username = sc.nextLine().trim();

        List<Account> accounts = accountController.findByUsername(username);
        if (accounts.isEmpty()) {
            System.out.println("Không tìm thấy account nào!");
        } else {
            inBangAccount(accounts);
        }
    }

    public void themMoiAccount() {
        System.out.println("\n==== THÊM MỚI ACCOUNT ====");
        System.out.print("Nhập Email: ");
        String email = sc.nextLine().trim();
        System.out.print("Nhập Username: ");
        String username = sc.nextLine().trim();
        System.out.print("Nhập Full Name: ");
        String fullName = sc.nextLine().trim();

        System.out.println("\n--- DANH SÁCH DEPARTMENT HIỆN CÓ ---");
        inBangDepartment(departmentController.findAll());
        System.out.print("Nhập Department ID: ");
        int depId = Integer.parseInt(sc.nextLine().trim());

        System.out.println("\n--- DANH SÁCH POSITION HIỆN CÓ ---");
        List<Position> positions = accountController.findAllPositions();
        System.out.println("+----+--------------------+");
        System.out.printf("|%-4s|%-20s|\n", "ID", "Position Name");
        System.out.println("+----+--------------------+");
        for (Position p : positions) {
            String pName = (p.getPositionName() != null) ? p.getPositionName().name() : "";
            System.out.printf("|%-4d|%-20s|\n", p.getPositionId(), pName);
        }
        System.out.println("+----+--------------------+");
        System.out.print("Nhập Position ID: ");
        int posId = Integer.parseInt(sc.nextLine().trim());

        if (accountController.create(email, username, fullName, depId, posId)) {
            System.out.println("Thêm mới account thành công!");
        } else {
            System.out.println("Thêm mới account thất bại!");
        }
    }

    public void xoaAccountTheoUsername() {
        System.out.println("\n==== XÓA ACCOUNT THEO USERNAME ====");
        System.out.print("Nhập username cần xóa: ");
        String username = sc.nextLine().trim();

        if (accountController.deleteByUsername(username)) {
            System.out.println("Xóa account thành công!");
        } else {
            System.out.println("Xóa không thành công (không tìm thấy username)!");
        }
    }

    public void updateFullNameTheoUsername() {
        System.out.println("\n==== UPDATE FULLNAME THEO USERNAME ====");
        System.out.print("Nhập username cần update: ");
        String username = sc.nextLine().trim();
        System.out.print("Nhập FullName mới: ");
        String newFullName = sc.nextLine().trim();

        if (accountController.updateFullNameByUsername(username, newFullName)) {
            System.out.println("Update FullName thành công!");
        } else {
            System.out.println("Update không thành công!");
        }
    }

    public void hienThiDepartment() {
        List<Department> list = departmentController.findAll();
        System.out.println("\n==== DANH SÁCH DEPARTMENT ====");
        inBangDepartment(list);
    }

    public void timKiemDepartmentTheoTen() {
        System.out.println("\n==== TÌM KIẾM DEPARTMENT THEO TÊN ====");
        System.out.print("Nhập tên department cần tìm: ");
        String name = sc.nextLine().trim();

        List<Department> dep = departmentController.findByName(name);
        if (dep != null && !dep.isEmpty()) {
            inBangDepartment(dep);
        } else {
            System.out.println("Không tìm thấy department nào!");
        }
    }

    public void themMoiDepartment() {
        System.out.println("\n==== THÊM MỚI DEPARTMENT ====");
        System.out.print("Nhập tên Department mới: ");
        String name = sc.nextLine().trim();

        if (departmentController.create(name)) {
            System.out.println("Thêm mới Department thành công!");
        } else {
            System.out.println("Thêm mới Department thất bại!");
        }
    }

    public void xoaDepartmentTheoId() {
        System.out.println("\n==== XÓA DEPARTMENT THEO ID ====");
        System.out.print("Nhập ID Department cần xóa: ");
        int id = Integer.parseInt(sc.nextLine().trim());

        if (departmentController.deleteById(id)) {
            System.out.println("Xóa Department thành công!");
        } else {
            System.out.println("Xóa thất bại (ID không tồn tại hoặc phòng ban đang chứa account)!");
        }
    }

    public void updateTenPhongBanTheoId() {
        System.out.println("\n==== UPDATE TÊN PHÒNG BAN THEO ID ====");
        System.out.print("Nhập ID Department cần update: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Nhập tên mới: ");
        String newName = sc.nextLine().trim();

        if (departmentController.update(id, newName)) {
            System.out.println("Update Department thành công!");
        } else {
            System.out.println("Update Department thất bại!");
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

    private void inBangDepartment(List<Department> departments) {
        System.out.println("+----+-------------------------+");
        System.out.printf("|%-4s|%-25s|\n", "ID", "Department Name");
        System.out.println("+----+-------------------------+");
        for (Department d : departments) {
            System.out.printf("|%-4d|%-25s|\n", d.getDepartmentId(), d.getDepartmentName());
        }
        System.out.println("+----+-------------------------+");
    }

    public void menu() {
        while (true) {
            System.out.println("\n========= MỜI BẠN CHỌN CHỨC NĂNG =========");
            System.out.println("--- QUẢN LÝ ACCOUNT ---");
            System.out.println("1. Hiển thị toàn bộ account");
            System.out.println("2. Tìm kiếm account theo username");
            System.out.println("3. Thêm mới account");
            System.out.println("4. Xóa account theo username");
            System.out.println("5. Update fullname theo username");
            System.out.println("--- QUẢN LÝ DEPARTMENT ---");
            System.out.println("6. Hiển thị department");
            System.out.println("7. Tìm kiếm department theo tên");
            System.out.println("8. Thêm mới department");
            System.out.println("9. Xóa department theo id");
            System.out.println("10. Update tên phòng ban theo id");
            System.out.println("11. Thoát");
            System.out.print("Lựa chọn của bạn (1 - 11): ");

            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1": this.hienThiToanBoAccount(); break;
                case "2": this.timKiemAccountTheoUsername(); break;
                case "3": this.themMoiAccount(); break;
                case "4": this.xoaAccountTheoUsername(); break;
                case "5": this.updateFullNameTheoUsername(); break;
                case "6": this.hienThiDepartment(); break;
                case "7": this.timKiemDepartmentTheoTen(); break;
                case "8": this.themMoiDepartment(); break;
                case "9": this.xoaDepartmentTheoId(); break;
                case "10": this.updateTenPhongBanTheoId(); break;
                case "11":
                    System.out.println("Thoát chương trình!");
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn từ 1 đến 11!");
            }
        }
    }
}