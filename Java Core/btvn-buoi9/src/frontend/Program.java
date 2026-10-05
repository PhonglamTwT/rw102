package frontend;

import backend.IQLAccount;
import backend.IQLDepartment;
import backend.QLAccount;
import backend.QLDepartment;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        IQLAccount qlAccount = new QLAccount();
        IQLDepartment qlDepartment = new QLDepartment();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n========= MỜI BẠN CHỌN CHỨC NĂNG =========");
            System.out.println("--- QUẢN LÝ ACCOUNT ---");
            System.out.println("1. Hiển thị toàn bộ Account");
            System.out.println("2. Tìm kiếm Account theo Username");
            System.out.println("3. Thêm mới Account");
            System.out.println("4. Xóa Account theo Username");
            System.out.println("5. Update FullName theo Username");
            System.out.println("--- QUẢN LÝ DEPARTMENT ---");
            System.out.println("6. Hiển thị Department");
            System.out.println("7. Tìm kiếm Department theo tên");
            System.out.println("8. Thêm mới Department");
            System.out.println("9. Xóa Department theo ID");
            System.out.println("10. Update tên phòng ban theo ID");
            System.out.println("11. Thoát");
            System.out.print("Lựa chọn của bạn (1 - 11): ");

            if (!sc.hasNextInt()) {
                System.out.println("Vui lòng nhập số!");
                sc.next();
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> qlAccount.danhSachAccount();
                case 2 -> qlAccount.timKiemTheoUsername();
                case 3 -> qlAccount.themMoiAccount();
                case 4 -> qlAccount.xoaAccountTheoUsername();
                case 5 -> qlAccount.updateFullNameTheoUsername();
                case 6 -> qlDepartment.danhSachDepartment();
                case 7 -> qlDepartment.timKiemDepartment();
                case 8 -> qlDepartment.themMoiDepartment();
                case 9 -> qlDepartment.xoaDepartmentTheoId();
                case 10 -> qlDepartment.updateTenDepartmentTheoId();
                case 11 -> {
                    System.out.println("Thoát chương trình!");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}