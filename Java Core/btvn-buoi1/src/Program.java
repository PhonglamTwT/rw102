import entity.*;

import java.time.LocalDate;

public class Program {
    public static void main(String[] args) {
//        Question 2:
//        Tạo file Program.java có chứa main() method và khởi tạo ít nhất 3 đối tượng đối với mỗi table trong java
        Department department1 = new Department(1, "Development");
        Department department2 = new Department(2, "Testing");
        Department department3 = new Department(3, "Marketing");

        Position position1 = new Position(1, PositionName.DEV);
        Position position2 = new Position(2, PositionName.TEST);
        Position position3 = new Position(3, PositionName.PM);

        Account account1 = new Account(1, "dev1@gmail.com", "dev1", "Nguyen Van A", department1, position1, LocalDate.of(2026, 1, 1));
        Account account2 = new Account(2, "test1@gmail.com", "test1", "Tran Van B", department2, position2, LocalDate.of(2026, 2, 1));
        Account account3 = new Account(3, "pm1@gmail.com", "pm1", "Le Van C", department3, position3, LocalDate.of(2026, 3, 1));

        Group group1 = new Group(1, "Java Backend", account1, LocalDate.now());
        Group group2 = new Group(2, "Testing Team", account2, LocalDate.now());
        Group group3 = new Group(3, "Project Team", account3, LocalDate.now());

        GroupAccount groupAccount1 = new GroupAccount(group1, account1, LocalDate.now());
        GroupAccount groupAccount2 = new GroupAccount(group2, account2, LocalDate.now());
        GroupAccount groupAccount3 = new GroupAccount(group3, account3, LocalDate.now());

        TypeQuestion typeQuestion1 = new TypeQuestion(1, TypeName.ESSAY);
        TypeQuestion typeQuestion2 = new TypeQuestion(2, TypeName.MULTIPLE_CHOICE);
        TypeQuestion typeQuestion3 = new TypeQuestion(3, TypeName.ESSAY);

        CategoryQuestion category1 = new CategoryQuestion(1, "Java");
        CategoryQuestion category2 = new CategoryQuestion(2, "SQL");
        CategoryQuestion category3 = new CategoryQuestion(3, "Spring Boot");

        Question question1 = new Question(1, "Java la gi?", category1, typeQuestion1, account1, LocalDate.now());
        Question question2 = new Question(2, "SQL JOIN la gi?", category2, typeQuestion2, account2, LocalDate.now());
        Question question3 = new Question(3, "Spring Boot la gi?", category3, typeQuestion3, account3, LocalDate.now());

        Answer answer1 = new Answer(1, "Java la ngon ngu lap trinh", question1, true);
        Answer answer2 = new Answer(2, "JOIN dung de ket noi bang", question2, true);
        Answer answer3 = new Answer(3, "Spring Boot la framework Java", question3, true);

        Exam exam1 = new Exam(1, "EX001", "Java Basic", category1, 60, account1, LocalDate.now());
        Exam exam2 = new Exam(2, "EX002", "SQL Basic", category2, 90, account2, LocalDate.now());
        Exam exam3 = new Exam(3, "EX003", "Spring Boot", category3, 120, account3, LocalDate.now());

        ExamQuestion examQuestion1 = new ExamQuestion(exam1, question1);
        ExamQuestion examQuestion2 = new ExamQuestion(exam2, question2);
        ExamQuestion examQuestion3 = new ExamQuestion(exam3, question3);

//        Question 3:
//        Trong file Program.java, hãy in ít nhất 1 giá trị của mỗi đối tượng ra
        System.out.println(department1);
        System.out.println();

        System.out.println(position1);
        System.out.println();

        System.out.println(account1);
        System.out.println();

        System.out.println(group1);
        System.out.println();

        System.out.println(groupAccount1);
        System.out.println();

        System.out.println(typeQuestion1);
        System.out.println();

        System.out.println(category1);
        System.out.println();

        System.out.println(question1);
        System.out.println();

        System.out.println(answer1);
        System.out.println();

        System.out.println(exam1);
        System.out.println();

        System.out.println(examQuestion1);
        System.out.println();
    }
}
