import entity.Account;
import entity.Department;
import entity.GroupAccount;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Excercise5 {
    public static void listDepartment(List<Department> departments) {

        System.out.printf("|%-5s|%-20s|\n", "Id", "Name");
        System.out.printf("+-----+--------------------\n");
        for (Department department : departments) {
            System.out.printf("|%-5d|%-20s|\n",
                    department.getDepartmentId(),
                    department.getDepartmentName());
        }
    }

    public static void question5(List<Department> departments) {
        Scanner sc = new Scanner(System.in);
        Department department1 = null;
        Department department2 = null;

        while (true) {
            System.out.println("Chọn phòng ban thứ 1:");
            listDepartment(departments);

            if (sc.hasNextInt()) {
                int id = sc.nextInt();
                sc.nextLine();

                for (Department department : departments) {
                    if (department.getDepartmentId() == id) {
                        department1 = department;
                        break;
                    }
                }

                if (department1 != null) {
                    break;
                } else {
                    System.out.println("Nhập phòng ban có trong danh sách");
                }
            } else {
                System.out.println("Xin hãy nhập Id");
                sc.nextLine();
            }
        }

        while (true) {
            System.out.println("Chọn phòng ban thứ 2:");
            listDepartment(departments);

            if (sc.hasNextInt()) {
                int id = sc.nextInt();
                sc.nextLine();

                for (Department department : departments) {
                    if (department.getDepartmentId() == id) {
                        department2 = department;
                        break;
                    }
                }

                if (department2 != null) {
                    break;
                }else {
                    System.out.println("Nhập Id có trong danh sách");
                }
            } else {
                System.out.println("Xin hãy nhập Id");
                sc.nextLine();
            }
        }

        if (department1.getDepartmentName().equals(department2.getDepartmentName())) {
            System.out.println("Bằng nhau");
        } else {
            System.out.println("Không bằng nhau");
        }
    }

    public static void question6(List<Department> departments) {
        departments.sort(Comparator.comparing(Department::getDepartmentName));
        System.out.println("Danh sách phòng ban theo alphabet");
        listDepartment(departments);
    }

    public static void question7(List<Account> accounts) {
        if (accounts == null || accounts.isEmpty()) {
            System.out.println("Danh sách account rỗng!");
            return;
        }

        accounts.sort(Comparator.comparing(
                account -> account.getDepartment() != null ? account.getDepartment().getDepartmentName() : "ZZZ_Không có phòng ban",
                String.CASE_INSENSITIVE_ORDER
        ));

        System.out.println("Danh sách account sắp xếp theo tên phòng ban:");
        System.out.printf("+-----+--------------------+---------------+--------------------+\n");
        System.out.printf("|%-5s|%-20s|%-15s|%-20s|\n", "Id", "Name", "Department Id", "Department Name");
        System.out.printf("+-----+--------------------+---------------+--------------------+\n");

        for (Account account : accounts) {
            int deptId = (account.getDepartment() != null) ? account.getDepartment().getDepartmentId() : 0;
            String deptName = (account.getDepartment() != null) ? account.getDepartment().getDepartmentName() : "Không có phòng ban";

            System.out.printf("|%-5d|%-20s|%-15d|%-20s|\n",
                    account.getAccountId(),
                    account.getFullName(),
                    deptId,
                    deptName);
        }
        System.out.printf("+-----+--------------------+---------------+--------------------+\n");
    }
}
