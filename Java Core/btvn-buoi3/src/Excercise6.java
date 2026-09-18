import entity.Account;

public class Excercise6 {
    public static void question1() {
        for (int i = 2; i < 10; i++) {
            if (i % 2 == 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    public static void question2(Account[] accounts) {
        for (Account a : accounts) {
            String dep = a.getDepartment() == null ? "null" : a.getDepartment().getDepartmentName();
            String pos = a.getPosition() == null ? "null" : a.getPosition().getPositionName().toString();

            System.out.printf("%d | %s | %s | %s | %s | %s%n",
                    a.getAccountId(),
                    a.getEmail(),
                    a.getUsername(),
                    a.getFullName(),
                    dep,
                    pos);
        }
    }

    public static void question3() {
        for (int i = 1; i < 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
