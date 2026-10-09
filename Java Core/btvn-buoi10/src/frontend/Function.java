package frontend;

import backend.controller.AccountController;
import backend.controller.DepartmentController;
import backend.controller.PositionController;
import entity.Account;
import entity.Department;
import entity.Position;

import java.util.List;
import java.util.Scanner;

public class Function {
    private AccountController accountController;
    private DepartmentController departmentController;
    private PositionController positionController;
    private Scanner sc;

    public Function() {
        this.accountController = new AccountController();
        this.departmentController = new DepartmentController();
        this.positionController = new PositionController();
        this.sc = new Scanner(System.in);
    }

    private int inputInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Dữ liệu nhập vào phải là số nguyên!");
            }
        }
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
        String username;
        while (true) {
            System.out.print("Nhập Username (5 - 50 kí tự): ");
            username = sc.nextLine().trim();
            if (username.length() < 5 || username.length() > 50) {
                System.out.println("Lỗi: Username phải từ 5 đến 50 kí tự!");
            } else if (accountController.existsByUsername(username)) {
                System.out.println("Lỗi: Username đã tồn tại trong hệ thống!");
            } else {
                break;
            }
        }

        String email;
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        while (true) {
            System.out.print("Nhập Email (5 - 50 kí tự, đúng định dạng): ");
            email = sc.nextLine().trim();
            if (email.length() < 5 || email.length() > 50) {
                System.out.println("Lỗi: Email phải từ 5 đến 50 kí tự!");
            } else if (!email.matches(emailRegex)) {
                System.out.println("Lỗi: Email không đúng định dạng!");
            } else if (accountController.existsByEmail(email)) {
                System.out.println("Lỗi: Email đã tồn tại trong hệ thống!");
            } else {
                break;
            }
        }

        String fullName;
        while (true) {
            System.out.print("Nhập Full Name (5 - 50 kí tự): ");
            fullName = sc.nextLine().trim();
            if (fullName.length() < 5 || fullName.length() > 50) {
                System.out.println("Lỗi: Full Name phải từ 5 đến 50 kí tự!");
            } else {
                break;
            }
        }

        System.out.println("\n--- DANH SÁCH DEPARTMENT HIỆN CÓ ---");
        List<Department> departments = departmentController.findAll();
        inBangDepartment(departments);
        int depId;
        while (true) {
            depId = inputInt("Nhập Department ID: ");
            int targetDepId = depId;
            boolean isExist = departments.stream().anyMatch(d -> d.getDepartmentId() == targetDepId);
            if (!isExist) {
                System.out.println("Lỗi: Department ID không tồn tại! Vui lòng chọn lại.");
            } else {
                break;
            }
        }

        System.out.println("\n--- DANH SÁCH POSITION HIỆN CÓ ---");
        List<Position> positions = positionController.findAll();
        System.out.println("+----+--------------------+");
        System.out.printf("|%-4s|%-20s|\n", "ID", "Position Name");
        System.out.println("+----+--------------------+");
        for (Position p : positions) {
            String pName = (p.getPositionName() != null) ? p.getPositionName().name() : "";
            System.out.printf("|%-4d|%-20s|\n", p.getPositionId(), pName);
        }
        System.out.println("+----+--------------------+");

        int posId;
        while (true) {
            posId = inputInt("Nhập Position ID: ");
            int targetPosId = posId;
            boolean isExist = positions.stream().anyMatch(p -> p.getPositionId() == targetPosId);
            if (!isExist) {
                System.out.println("Lỗi: Position ID không tồn tại! Vui lòng chọn lại.");
            } else {
                break;
            }
        }

        if (accountController.create(email, username, fullName, depId, posId)) {
            System.out.println("Thêm mới account thành công!");
        } else {
            System.out.println("Thêm mới account thất bại!");
        }
    }

    public void xoaAccountTheoId() {
        System.out.println("\n==== XÓA ACCOUNT THEO ID ====");
        int id;
        while (true) {
            id = inputInt("Nhập ID Account cần xóa: ");
            if (!accountController.existsById(id)) {
                System.out.println("Lỗi: Account ID không tồn tại!");
            } else {
                break;
            }
        }

        if (accountController.deleteById(id)) {
            System.out.println("Xóa account thành công!");
        } else {
            System.out.println("Xóa account thất bại!");
        }
    }

    public void updateUsernameTheoId() {
        System.out.println("\n==== UPDATE USERNAME THEO ID ====");

        int id;
        while (true) {
            id = inputInt("Nhập ID Account cần update: ");
            if (!accountController.existsById(id)) {
                System.out.println("Lỗi: Account ID không tồn tại!");
            } else {
                break;
            }
        }

        String newUsername;
        while (true) {
            System.out.print("Nhập Username mới (5 - 50 kí tự): ");
            newUsername = sc.nextLine().trim();
            if (newUsername.length() < 5 || newUsername.length() > 50) {
                System.out.println("Lỗi: Username phải từ 5 đến 50 kí tự!");
            } else if (accountController.existsByUsername(newUsername)) {
                System.out.println("Lỗi: Username đã tồn tại trong DB!");
            } else {
                break;
            }
        }

        if (accountController.updateUsernameById(id, newUsername)) {
            System.out.println("Update Username thành công!");
        } else {
            System.out.println("Update Username thất bại!");
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
        int id = inputInt("Nhập ID Department cần xóa: ");

        if (departmentController.deleteById(id)) {
            System.out.println("Xóa Department thành công!");
        } else {
            System.out.println("Xóa thất bại (ID không tồn tại hoặc phòng ban đang chứa account)!");
        }
    }

    public void updateTenPhongBanTheoId() {
        System.out.println("\n==== UPDATE TÊN PHÒNG BAN THEO ID ====");
        int id = inputInt("Nhập ID Department cần update: ");
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
            System.out.println("4. Xóa account theo ID");
            System.out.println("5. Update username theo ID");
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
                case "4": this.xoaAccountTheoId(); break;
                case "5": this.updateUsernameTheoId(); break;
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