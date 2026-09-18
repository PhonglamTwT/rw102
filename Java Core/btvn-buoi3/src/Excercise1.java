import entity.Account;
import entity.Department;

public class Excercise1 {
    public static void question16_10(Account[] accounts) {
        System.out.println("Question 16.10");
        int i = 0;
        while (i < accounts.length) {
            System.out.println("Thông tin account thứ " + (i + 1));
            System.out.println("Id: " + accounts[i].getAccountId());
            System.out.println("Email: " + accounts[i].getEmail());
            System.out.println("FullName: " + accounts[i].getFullName());
            if (accounts[i].getDepartment() == null) {
                System.out.println("Phòng ban: Chưa có");
            } else {
                System.out.println("Phòng ban: " + accounts[i].getDepartment().getDepartmentName());
            }
            System.out.println();
            i++;
        }
    }

    public static void question16_11(Department[] departments) {
        System.out.println("Question 16.11");
        int i = 0;
        while (i < departments.length) {
            System.out.println("Department thứ " + (i + 1));
            System.out.println("Id: " + departments[i].getDepartmentId());
            System.out.println("Name: " + departments[i].getDepartmentName());
            System.out.println();
            i++;
        }
    }

    public static void question16_12(Department[] departments) {
        System.out.println("Question 16.12");
        int i = 0;
        while (i < departments.length) {
            if (i == 2) {
                System.out.println();
                break;
            }
            System.out.println(departments[i].getDepartmentName());
            i++;
        }
    }

    public static void question16_13(Account[] accounts) {
        System.out.println("Question 16.13");
        int i = 0;
        while (i < accounts.length) {
            if (i == 1) {
                i++;
                continue;
            }
            System.out.println("Thông tin account thứ " + (i + 1));
            System.out.println("Id: " + accounts[i].getAccountId());
            System.out.println("Email: " + accounts[i].getEmail());
            System.out.println("FullName: " + accounts[i].getFullName());
            if (accounts[i].getDepartment() == null) {
                System.out.println("Phòng ban: Chưa có");
            } else {
                System.out.println("Phòng ban: " + accounts[i].getDepartment().getDepartmentName());
            }
            System.out.println();
            i++;
        }
    }

    public static void question16_14(Account[] accounts) {
        System.out.println("Question 16.14");
        int i = 0;
        while (i < accounts.length) {
            if (accounts[i].getAccountId() < 4) {
                System.out.println("Thông tin account thứ " + (i + 1));
                System.out.println("Id: " + accounts[i].getAccountId());
                System.out.println("Email: " + accounts[i].getEmail());
                System.out.println("FullName: " + accounts[i].getFullName());
                if (accounts[i].getDepartment() == null) {
                    System.out.println("Phòng ban: Chưa có");
                } else {
                    System.out.println("Phòng ban: " + accounts[i].getDepartment().getDepartmentName());
                }
                System.out.println();
            }
            i++;
        }
    }

    public static void question16_15() {
        System.out.println("Question 16.15");
        int i = 0;
        while (i <= 20) {
            if (i % 2 == 0) {
                System.out.print(i + " ");
            }
            i++;
        }
        System.out.println();
    }

    public static void question17_10(Account[] accounts) {
        System.out.println("Question 17.10");
        int i = 0;
        do {
            System.out.println("Thông tin account thứ " + (i + 1));
            System.out.println("Id: " + accounts[i].getAccountId());
            System.out.println("Email: " + accounts[i].getEmail());
            System.out.println("FullName: " + accounts[i].getFullName());
            if (accounts[i].getDepartment() == null) {
                System.out.println("Phòng ban: Chưa có");
            } else {
                System.out.println("Phòng ban: " + accounts[i].getDepartment().getDepartmentName());
            }
            System.out.println();
            i++;
        } while (i < accounts.length);
    }

    public static void question17_11(Department[] departments) {
        System.out.println("Question 17.11");
        int i = 0;
        do {
            System.out.println("Department thứ " + (i + 1));
            System.out.println("Id: " + departments[i].getDepartmentId());
            System.out.println("Name: " + departments[i].getDepartmentName());
            System.out.println();
            i++;
        } while (i < departments.length);
    }

    public static void question17_12(Department[] departments) {
        System.out.println("Question 17.12");
        int i = 0;
        do {
            if (i == 2) {
                break;
            }
            System.out.println("Department thứ " + (i + 1));
            System.out.println("Id: " + departments[i].getDepartmentId());
            System.out.println("Name: " + departments[i].getDepartmentName());
            System.out.println();
            i++;
        } while (i < departments.length);
    }

    public static void question17_13(Account[] accounts) {
        System.out.println("Question 17.13");
        int i = 0;
        do {
            if (i == 1) {
                i++;
                continue;
            }
            System.out.println("Thông tin account thứ " + (i + 1));
            System.out.println("Id: " + accounts[i].getAccountId());
            System.out.println("Email: " + accounts[i].getEmail());
            System.out.println("FullName: " + accounts[i].getFullName());
            if (accounts[i].getDepartment() == null) {
                System.out.println("Phòng ban: Chưa có");
            } else {
                System.out.println("Phòng ban: " + accounts[i].getDepartment().getDepartmentName());
            }
            System.out.println();
            i++;
        } while (i < accounts.length);
    }

    public static void question17_14(Account[] accounts) {
        System.out.println("Question 17.14");
        int i = 0;
        do {
            if (accounts[i].getAccountId() < 4) {
                System.out.println("Thông tin account thứ " + (i + 1));
                System.out.println("Id: " + accounts[i].getAccountId());
                System.out.println("Email: " + accounts[i].getEmail());
                System.out.println("FullName: " + accounts[i].getFullName());
                if (accounts[i].getDepartment() == null) {
                    System.out.println("Phòng ban: Chưa có");
                } else {
                    System.out.println("Phòng ban: " + accounts[i].getDepartment().getDepartmentName());
                }
                System.out.println();
            }
            i++;
        } while (i < accounts.length);
    }

    public static void question17_15() {
        System.out.println("Question 17.15");
        int i = 0;
        do {
            if (i % 2 == 0) {
                System.out.print(i + " ");
            }
            i++;
        } while (i <= 20);
        System.out.println();
    }
}
