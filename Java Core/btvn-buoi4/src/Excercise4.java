import entity.Group;

import java.util.List;
import java.util.Scanner;

public class Excercise4 {
    public static void question1() {
        Scanner scanner = new Scanner(System.in);
        String str = null;
        while (true) {
            System.out.print("Nhập chuỗi: ");
            str = scanner.nextLine().trim();
            if (!str.isEmpty()) {
                break;
            }
            System.out.println("Hãy nhập một chuỗi");
        }

        String[] words = str.split("\\s+");

        System.out.println("Số từ: " + words.length);
    }

    public static void question2() {
        Scanner scanner = new Scanner(System.in);
        String s1 = null;
        String s2 = null;

        while (true) {
            System.out.print("Nhập chuỗi 1: ");
            s1 = scanner.nextLine().trim();
            if (!s1.isEmpty()) {
                break;
            }
            System.out.println("Hãy nhập một chuỗi");
        }

        while (true) {
            System.out.print("Nhập chuỗi 2: ");
            s2 = scanner.nextLine().trim();
            if (!s2.isEmpty()) {
                break;
            }
            System.out.println("Hãy nhập một chuỗi");
        }

        System.out.println("Gộp 2 chuỗi: " + (s1 + " " + s2));
    }

    public static void question3() {
        Scanner scanner = new Scanner(System.in);
        String name = null;
        while (true) {
            System.out.print("Nhập tên: ");
            name = scanner.nextLine().trim();
            if (!name.isEmpty()) {
                break;
            }
            System.out.println("Hãy nhập tên của mình");
        }

        String[] words = name.split("\\s+");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                String capitalizedWord = word.substring(0, 1).toUpperCase()
                        + word.substring(1).toLowerCase();
                result.append(capitalizedWord).append(" ");
            }
        }

        System.out.println(result.toString().trim());
    }

    public static void question4() {
        Scanner scanner = new Scanner(System.in);
        String name = null;

        while (true) {
            System.out.print("Nhập tên: ");
            name = scanner.nextLine().trim();
            if (!name.isEmpty()) {
                break;
            }
            System.out.println("Hãy nhập tên của mình");
        }

        for (int i = 0; i < name.length(); i++) {
            System.out.println("Ký tự thứ " + (i + 1) + " là: " + name.charAt(i));
        }
    }

    public static void question5() {
        Scanner scanner = new Scanner(System.in);
        String lastName  = null;
        String firstName = null;
        while (true) {
            System.out.print("Nhập họ: ");
            firstName = scanner.nextLine().trim();
            if (!firstName.isEmpty()) {
                break;
            }
            System.out.println("Hãy nhập họ của mình");
        }

        while (true) {
            System.out.print("Nhập tên: ");
            lastName = scanner.nextLine().trim();
            if (!lastName.isEmpty()) {
                break;
            }
            System.out.println("Hãy nhập tên của mình");
        }

        System.out.println(firstName + " " + lastName);
    }

    public static void question6() {
        Scanner scanner = new Scanner(System.in);
        String fullName = null;

        while (true) {
            System.out.print("Nhập họ tên: ");
            fullName = scanner.nextLine().trim();
            if (!fullName.isEmpty()) {
                break;
            }
            System.out.println("Hãy nhập họ tên của mình");
        }

        String[] words = fullName.split("\\s+");

        if (words.length == 1) {
            System.out.println("Tên: " + words[0]);
        } else {
            String middle = "";
            for (int i = 1; i < words.length - 1; i++) {
                middle += words[i] + " ";
            }
            System.out.println("Họ: " + words[0]);
            System.out.println("Tên đệm: " + middle.trim());
            System.out.println("Tên: " + words[words.length - 1]);
        }
    }

    public static void question7() {
        Scanner scanner = new Scanner(System.in);
        String fullName = null;

        while (true) {
            System.out.print("Nhập họ tên: ");
            fullName = scanner.nextLine().trim();
            if (!fullName.isEmpty()) {
                break;
            }
            System.out.println("Hãy nhập họ tên của mình");
        }

        fullName = fullName.trim().replaceAll("\\s+", " ");

        String[] words = fullName.split(" ");

        String result = "";

        for (String word : words) {
            result += word.substring(0,1).toUpperCase()
                    + word.substring(1).toLowerCase() + " ";
        }

        System.out.println(result.trim());
    }

    public static void question8(List<Group> groups) {

        System.out.printf("|%-5s|%-20s|%-15s|\n", "ID", "Group Name", "Create Date");
        System.out.println("+-----+--------------------+---------------+");

        for (Group group : groups) {
            if (group.getGroupName().contains("Java")) {
                System.out.printf("|%-5d|%-20s|%-15s|\n",
                        group.getGroupId(),
                        group.getGroupName(),
                        group.getCreateDate());
            }
        }
    }

    public static void question9(List<Group> groups) {

        System.out.printf("|%-5s|%-20s|%-15s|\n", "ID", "Group Name", "Create Date");
        System.out.println("+-----+--------------------+---------------+");

        for (Group group : groups) {
            if (group.getGroupName().equals("Java")) {
                System.out.printf("|%-5d|%-20s|%-15s|\n",
                        group.getGroupId(),
                        group.getGroupName(),
                        group.getCreateDate());
            }
        }
    }

    public static void question10() {
        Scanner scanner = new Scanner(System.in);
        String s1 = null;
        String s2 = null;

        while (true) {
            System.out.print("Chuỗi 1: ");
            s1 = scanner.nextLine().trim();
            if (!s1.isEmpty()) {
                break;
            }
            System.out.println("Xin hãy nhập chuỗi 1!");
        }

        while (true) {
            System.out.print("Chuỗi 2: ");
            s2 = scanner.nextLine().trim();
            if (!s2.isEmpty()) {
                break;
            }
            System.out.println("Xin hãy nhập chuỗi 2!");
        }

        String reverse = "";

        for (int i = s1.length() - 1; i >= 0; i--) {
            reverse += s1.charAt(i);
        }

        if (reverse.equals(s2)) {
            System.out.println("OK");
        } else {
            System.out.println("KO");
        }
    }

    public static void question11() {

        Scanner scanner = new Scanner(System.in);
        String str = null;

        while (true) {
            System.out.print("Nhập chuỗi: ");
            str = scanner.nextLine().trim();
            if (!str.isEmpty()) {
                break;
            }
            System.out.println("Xin hãy nhập chuỗi!");
        }

        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            if (Character.toLowerCase(str.charAt(i)) == 'a') {
                count++;
            }
        }

        System.out.println("Có " +count+ " chữ a trong chuỗi");
    }

    public static void question12() {

        Scanner scanner = new Scanner(System.in);
        String str = null;

        while (true) {
            System.out.print("Nhập chuỗi: ");
            str = scanner.nextLine().trim();
            if (!str.isEmpty()) {
                break;
            }
            System.out.println("Xin hãy nhập chuỗi!");
        }

        System.out.println("Chuỗi đảo ngược là: ");
        for (int i = str.length() - 1; i >= 0; i--) {
            System.out.print(str.charAt(i));
        }
    }

    public static void question13() {
        Scanner scanner = new Scanner(System.in);
        String str = null;

        while (true) {
            System.out.print("Nhập chuỗi: ");
            str = scanner.nextLine().trim();
            if (!str.isEmpty()) {
                break;
            }
            System.out.println("Xin hãy nhập chuỗi!");
        }

        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                System.out.println(false);
                return;
            }

        }

        System.out.println(true);
    }

    public static void question14() {
        Scanner scanner = new Scanner(System.in);
        String str = "";
        String replaceChar = "";
        String replaceToChar = "";

        while (true) {
            System.out.print("Nhập chuỗi: ");
            str = scanner.nextLine().trim();
            if (!str.isEmpty()) {
                break;
            }
            System.out.println("Xin hãy nhập chuỗi!");
        }

        while (true) {
            System.out.print("Nhập từ muốn đổi: ");
            replaceChar = scanner.nextLine().trim();

            if (replaceChar.isEmpty()) {
                System.out.println("Xin hãy nhập từ muốn đổi!");
            } else if (!str.contains(replaceChar)) {
                System.out.println("Không có từ đó trong chuỗi, vui lòng nhập lại!");
            } else {
                break;
            }
        }

        while (true) {
            System.out.print("Nhập từ sẽ thay thế: ");
            replaceToChar = scanner.nextLine().trim();

            if (!replaceToChar.isEmpty()) {
                break;
            }
            System.out.println("Xin hãy nhập từ sẽ thay!");
        }

        str = str.replace(replaceChar, replaceToChar);
        System.out.println("Chuỗi sau khi đổi: " + str);
    }

    public static void question15() {
        Scanner scanner = new Scanner(System.in);
        String str = "";

        while (true) {
            System.out.print("Nhập chuỗi: ");
            str = scanner.nextLine().trim();
            if (!str.isEmpty()) {
                break;
            }
            System.out.println("Xin hãy nhập chuỗi!");
        }

        String[] words = str.split("\\s+");

        for (int i = words.length - 1; i >= 0; i--) {
            System.out.print(words[i] + " ");
        }
    }

    public static void question16() {
        Scanner scanner = new Scanner(System.in);
        String str = "";

        while (true) {
            System.out.print("Nhập chuỗi: ");
            str = scanner.nextLine().trim();
            if (!str.isEmpty()) {
                break;
            }
            System.out.println("Xin hãy nhập chuỗi!");
        }

        int n = 0;
        while (true) {
            System.out.print("Nhập n: ");
            if (scanner.hasNextInt()) {
                n = scanner.nextInt();
                if (n > 0) {
                    break;
                } else {
                    System.out.println("n phải lớn hơn 0!");
                }
            } else {
                System.out.println("Xin hãy nhập một số nguyên!");
                scanner.next();
            }
        }

        if (str.length() % n != 0) {
            System.out.println("KO");
            return;
        }

        for (int i = 0; i < str.length(); i += n) {
            System.out.println(str.substring(i, i + n));
        }
    }
}
