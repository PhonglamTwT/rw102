import entity.Account;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Exercise2 {
    public static void question1(int number) {
        System.out.printf("Số nguyên: %d\n", number);
        System.out.println();
    }

    public static void question2(int number) {
        System.out.printf("Số: %,d\n", number);
        System.out.println();
    }

    public static void question3(double number) {
        System.out.printf("Số thực: %.4f\n", number);
        System.out.println();
    }

    public static void question4(Account account) {
        System.out.printf("Tên tôi là \"%s\" và tôi đang độc thân.%n", account.getFullName());
        System.out.println();
    }

    public static void question5(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH'h':mm'p':ss's'");
        System.out.println(dateTime.format(formatter));
        System.out.println();
    }

    // Question 6:
    // In thông tin account dạng table
    public static void question6(Account[] accounts) {
        System.out.println("+-----+-------------------------+---------------+--------------------+---------------");
        System.out.printf("|%-5s|%-25s|%-15s|%-20s|%-15s%n", "ID", "Email", "Username", "Full Name", "Department");
        for (Account account : accounts) {
            String department;
            if (account.getDepartment() == null) {
                department = "Khong co department";
            } else {
                department = account.getDepartment().getDepartmentName();
            }
            System.out.printf("|%-5d|%-25s|%-15s|%-20s|%-15s%n", account.getAccountId(), account.getEmail(), account.getUsername(), account.getFullName(), department
            );
        }
        System.out.println("+-----+-------------------------+---------------+--------------------+---------------");
    }
}
