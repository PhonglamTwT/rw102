import jdk.swing.interop.SwingInterOpUtils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class Excercise4 {
    public static void question1() {
        Random random = new Random();
        int number = random.nextInt();
        System.out.println("Số nguyên random: " + number);
    }

    public static void question2() {
        Random random = new Random();
        double number = random.nextDouble();
        System.out.println("Số thực random: " + number);
    }

    public static void question3() {
        String[] names = {"A", "B", "C", "D", "E"};
        Random random = new Random();
        int index = random.nextInt(names.length);
        System.out.println("Tên random: " + names[index]);
    }

    public static void question4() {
        LocalDate startDate = LocalDate.of(1995, 7, 24);
        LocalDate endDate = LocalDate.of(1995, 12, 20);

        Random random = new Random();
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        LocalDate randomDate = startDate.plusDays(random.nextInt((int) days + 1));
        System.out.println("Ngày random: " + randomDate);
    }

    public static void question5() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusYears(1);

        Random random = new Random();
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        LocalDate randomDate = startDate.plusDays(random.nextInt((int) days + 1));
        System.out.println("Ngày random trong 1 năm: " + randomDate);
    }

    public static void question6() {
        Random random = new Random();
        LocalDate today = LocalDate.now();
        long randomDay = random.nextLong(today.toEpochDay());
        LocalDate result = LocalDate.ofEpochDay(randomDay);
        System.out.println("Ngày ngẫu nhiên trong quá khứ: " + result);
    }

    public static void question7() {
        Random random = new Random();
        int number = random.nextInt(900) + 100;
        System.out.println("Số có 3 chữ số: " + number);
    }
}
