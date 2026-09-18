import entity.Exam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Excercise3 {
    public static void question1(Exam exam) {
        Locale locale = new Locale("vi", "VN");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", locale);
        System.out.println("ExamId: " + exam.getExamId());
        System.out.println("Code: " + exam.getCode());
        System.out.println("Exam: " + exam.getTitle());
        System.out.println("Category: " + exam.getCategory().getCategoryName());
        System.out.println("Duration: " + exam.getDuration());
        System.out.println("Creator: " + exam.getCreator().getFullName());
        System.out.println("Create Date: " + exam.getCreateDate().format(formatter)
        );
    }

    public static void question2(Exam exam) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Exam tạo ngày: " + exam.getCreateDate().format(formatter)
        );
    }

    public static void question3(Exam exam) {
        System.out.println("Năm tạo Exam: " + exam.getCreateDate().getYear());
    }

    public static void question4(Exam exam) {
        System.out.println("Tháng và năm tạo Exam: " + exam.getCreateDate().getMonthValue() + "-" + exam.getCreateDate().getYear()
        );
    }

    public static void question5(Exam exam) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
        System.out.println("Ngày tạo Exam: " + exam.getCreateDate().format(formatter)
        );
    }

}
