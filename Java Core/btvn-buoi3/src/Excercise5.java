import entity.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Excercise5 {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    public static int inputInt(String message) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                scanner.nextLine();
                return number;
            }
            System.out.println("Sai định dạng! Vui lòng nhập số nguyên.");
            scanner.nextLine();
        }
    }

    public static double inputDouble(String message) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNextDouble()) {
                double number = scanner.nextDouble();
                scanner.nextLine();
                return number;
            }
            System.out.println("Sai định dạng! Vui lòng nhập số thực.");
            scanner.nextLine();
        }
    }

    public static String inputString(String message) {
        while (true) {
            System.out.print(message);
            String value = scanner.nextLine();
            if (!value.trim().isEmpty()) return value;
            System.out.println("Không được để trống!");
        }
    }

    public static LocalDate inputDate(String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        while (true) {
            try {
                return LocalDate.parse(inputString(message), formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Sai format! Vui lòng nhập dd:MM:yyyy");
            }
        }
    }

    public static void question1() {
        int a = inputInt("Nhập số thứ 1: ");
        int b = inputInt("Nhập số thứ 2: ");
        int c = inputInt("Nhập số thứ 3: ");
        System.out.println(a + " " + b + " " + c);
    }

    public static void question2() {
        double a = inputDouble("Nhập số thực 1: ");
        double b = inputDouble("Nhập số thực 2: ");
        System.out.println(a + b);
    }

    public static void question3() {
        System.out.println("Tên: " + inputString("Nhập họ tên: "));
    }

    public static void question4() {
        LocalDate birthday = inputDate("Nhập ngày sinh (dd:MM:yyyy): ");
        System.out.println("Birthday: " + birthday);
    }

    public static Account question5(List<Department> departments, List<Position> positions, List<Account> accounts) {
        int id;
        while (true) {
            id = inputInt("Nhập id: ");

            boolean exists = false;

            for (Account account : accounts) {
                if (account.getAccountId() == id) {
                    exists = true;
                    break;
                }
            }

            if (exists) {
                System.out.println("Account id đã tồn tại, nhập lại!");
            } else {
                break;
            }
        }

        String email = inputString("Nhập email: ");
        String username = inputString("Nhập username: ");
        String fullname = inputString("Nhập fullname: ");

        System.out.println("Chọn Department:");
        for (Department d : departments) {
            System.out.println(d.getDepartmentId() + ". " + d.getDepartmentName());
        }
        Department department = null;
        while (department == null) {
            int choice = inputInt("Nhập Department id: ");
            for (Department d : departments) {
                if (d.getDepartmentId() == choice) {
                    department = d;
                    break;
                }
            }
            if (department == null) System.out.println("Không có Department này, nhập lại!");
        }

        System.out.println("Chọn Position:");
        for (Position p : positions) {
            System.out.println(p.getPositionId() + ". " + p.getPositionName());
        }
        Position position = null;
        while (position == null) {
            int choice = inputInt("Nhập Position id: ");
            for (Position p : positions) {
                if (p.getPositionId() == choice) {
                    position = p;
                    break;
                }
            }
            if (position == null) System.out.println("Không có Position này, nhập lại!");
        }
        return new Account(id, email, username, fullname, department, position, LocalDate.now());
    }

    public static Department question6(List<Department> departments) {
        int id;
        while (true) {
            id = inputInt("Nhập department id: ");
            boolean exists = false;
            for (Department d : departments) {
                if (d.getDepartmentId() == id) {
                    exists = true;
                    break; }
            }
            if (exists) {
                System.out.println("Department id đã tồn tại, nhập lại!");
            }
            else break;
        }

        String name;
        while (true) {
            name = inputString("Nhập department name: ");
            boolean exists = false;
            for (Department d : departments) {
                if (d.getDepartmentName().equalsIgnoreCase(name)) {
                    exists = true;
                    break; }
            }
            if (exists) System.out.println("Department name đã tồn tại, nhập lại!");
            else break;
        }
        return new Department(id, name);
    }

    public static void question7() {
        while (true) {
            int number = inputInt("Nhập số chẵn: ");
            if (number % 2 == 0) {
                System.out.println("Số hợp lệ: " + number);
                break;
            }
            System.out.println("Không phải số chẵn!");
        }
    }

    public static void question8(List<Department> departments, List<Position> positions, List<Account> accounts, List<Group> groups, List<GroupAccount> groupAccounts) {
        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Tạo Account");
            System.out.println("2. Tạo Department");
            System.out.println("3. Thêm Group vào Account");
            System.out.println("4. Thêm Account vào Group ngẫu nhiên");

            int choice = inputInt("Mời bạn nhập vào chức năng muốn sử dụng: ");
            if (choice == 1) {
                System.out.println("\n===== DANH SÁCH ACCOUNT =====");
                printAccounts(accounts);
                Account acc = question5(departments, positions, accounts);
                accounts.add(acc);
                System.out.println("Đã tạo account: " + acc.getUsername());
                System.out.println("\n===== DANH SÁCH ACCOUNT SAU KHI ADD =====");
                printAccounts(accounts);

            } else if (choice == 2) {
                System.out.println("\n===== DANH SÁCH DEPARTMENT =====");
                printDepartments(departments);
                Department d = question6(departments);
                departments.add(d);
                System.out.println("Đã tạo department: " + d.getDepartmentName());
                System.out.println("\n===== DANH SÁCH DEPARTMENT SAU KHI ADD =====");
                printDepartments(departments);

            } else if (choice == 3) {
                question9(accounts, groups, groupAccounts);
                System.out.println("\n===== DANH SÁCH GROUP-ACCOUNT SAU KHI ADD =====");
                printGroupAccounts(groupAccounts);

            } else if (choice == 4) {
                question11(accounts, groups, groupAccounts);
                System.out.println("\n===== DANH SÁCH GROUP-ACCOUNT SAU KHI ADD =====");
                printGroupAccounts(groupAccounts);

            } else {
                System.out.println("Mời bạn nhập lại");
                continue;
            }

            String answer = inputString("Bạn có muốn thực hiện chức năng khác không? (Có/Không): ");
            if (answer.equalsIgnoreCase("Không")) return;
        }
    }

    public static void question9(List<Account> accounts, List<Group> groups, List<GroupAccount> groupAccounts) {
        if (accounts.isEmpty()) {
            System.out.println("Chưa có account nào.");
            return;
        }
        if (groups.isEmpty()) {
            System.out.println("Chưa có group nào.");
            return;
        }

        System.out.println("Danh sách username:");
        for (Account a : accounts) System.out.println("  - " + a.getUsername());

        String username = inputString("Nhập username của account: ");
        Account account = null;
        for (Account a : accounts) {
            if (a.getUsername().equalsIgnoreCase(username)) {
                account = a;
                break; }
        }
        if (account == null) {
            System.out.println("Không tìm thấy account!");
            return;
        }

        System.out.println("Danh sách group:");
        for (Group g : groups) {
            System.out.println("  - " + g.getGroupName());
        }

        String groupName = inputString("Nhập tên group: ");
        Group group = null;
        for (Group g : groups) {
            if (g.getGroupName().equalsIgnoreCase(groupName)) {
                group = g;
                break; }
        }
        if (group == null) {
            System.out.println("Không tìm thấy group!");
            return;
        }

        if (isAccountInGroup(groupAccounts, account, group)) {
            System.out.println("Account " + account.getUsername() + " đã ở trong group " + group.getGroupName() + " rồi!");
            return;
        }

        groupAccounts.add(new GroupAccount(group, account, LocalDate.now()));
        System.out.println("Đã thêm " + account.getUsername() + " vào group " + group.getGroupName());
    }

    public static void question11(List<Account> accounts, List<Group> groups, List<GroupAccount> groupAccounts) {
        if (accounts.isEmpty()) {
            System.out.println("Chưa có account nào.");
            return;
        }
        if (groups.isEmpty()) {
            System.out.println("Chưa có group nào.");
            return;
        }

        System.out.println("Danh sách username:");
        for (Account a : accounts) {
            System.out.println("  - " + a.getUsername());
        }

        String username = inputString("Nhập username của account: ");
        Account account = null;
        for (Account a : accounts) {
            if (a.getUsername().equalsIgnoreCase(username)) {
                account = a;
                break;
            }
        }
        if (account == null) {
            System.out.println("Không tìm thấy account!");
            return;
        }

        List<Group> availableGroups = new ArrayList<>();
        for (Group g : groups) {
            if (!isAccountInGroup(groupAccounts, account, g)) {
                availableGroups.add(g);
            }
        }

        if (availableGroups.isEmpty()) {
            System.out.println("Account " + account.getUsername() + " đã ở trong TẤT CẢ group rồi, không thể thêm!");
            return;
        }

        Group randomGroup = availableGroups.get(random.nextInt(availableGroups.size()));

        groupAccounts.add(new GroupAccount(randomGroup, account, LocalDate.now()));
        System.out.println("Đã thêm " + account.getUsername() + " vào group ngẫu nhiên: " + randomGroup.getGroupName());
    }

    public static void printAccounts(List<Account> accounts) {
        System.out.println("+-----+-------------------------+---------------+--------------------+---------------+");
        System.out.printf("|%-5s|%-25s|%-15s|%-20s|%-15s|%n", "ID", "Email", "Username", "Full Name", "Department"
        );
        System.out.println("+-----+-------------------------+---------------+--------------------+---------------+");

        for (Account account : accounts) {
            String department = (account.getDepartment() == null) ? "Khong co department" : account.getDepartment().getDepartmentName();

            System.out.printf("|%-5d|%-25s|%-15s|%-20s|%-15s|%n", account.getAccountId(), account.getEmail(), account.getUsername(), account.getFullName(), department);
        }
        System.out.println("+-----+-------------------------+---------------+--------------------+---------------+");
    }

    public static void printDepartments(List<Department> departments) {
        if (departments.isEmpty()) {
            System.out.println("(Chưa có department nào)");
            return;
        }
        System.out.println("+-----+--------------------+");
        System.out.printf("|%-5s|%-20s|%n", "ID", "Department Name");
        System.out.println("+-----+--------------------+");

        for (Department d : departments) {
            System.out.printf("|%-5d|%-20s|%n", d.getDepartmentId(), d.getDepartmentName());
        }
        System.out.println("+-----+--------------------+");
    }

    public static void printGroupAccounts(List<GroupAccount> groupAccounts) {
        if (groupAccounts.isEmpty()) {
            System.out.println("(Chưa có GroupAccount nào)");
            return;
        }
        System.out.println("+--------------+----------------+------------+");
        System.out.printf("|%-14s|%-16s|%-12s|%n", "Group", "Username", "JoinDate");
        System.out.println("+--------------+----------------+------------+");

        for (GroupAccount ga : groupAccounts) {
            System.out.printf("|%-14s|%-16s|%-12s|%n", ga.getGroup().getGroupName(), ga.getAccount().getUsername(), ga.getJoinDate());
        }
        System.out.println("+--------------+----------------+------------+");
    }

    private static boolean isAccountInGroup(List<GroupAccount> groupAccounts,
                                            Account account, Group group) {
        for (GroupAccount ga : groupAccounts) {
            if (ga.getAccount().getAccountId() == account.getAccountId()
                    && ga.getGroup().getGroupId() == group.getGroupId()) {
                return true;
            }
        }
        return false;
    }
}