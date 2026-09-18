import entity.*;
import jdk.swing.interop.SwingInterOpUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Program {
    public static void main(String[] args) {
        Department d1 = new Department(1, "Sale");
        Department d2 = new Department(2, "Marketing");
        Department d3 = new Department(3, "IT");
        Department[] departments = {d1, d2, d3};

        Position p1 = new Position(1, PositionName.DEV);
        Position p2 = new Position(2, PositionName.TEST);
        Position p3 = new Position(3, PositionName.PM);
        Position p4 = new Position(4, PositionName.SCRUM_MASTER);

        Position[] positions = {p1, p2, p3, p4};

        Group g1 = new Group(1, "Java Fresher", null, LocalDate.now());
        Group g2 = new Group(2, "C# Fresher", null, LocalDate.now());
        Group g3 = new Group(3, "Python", null, LocalDate.now());
        Group g4 = new Group(4, "AI", null, LocalDate.now());
        Group g5 = new Group(5, "Game", null, LocalDate.now());

        Group[] groups = {g1, g2, g3, g4, g5};

        Account a1 = new Account(1, "NguyenVanA@gmail.com", "a.nguyen", "Nguyễn Văn A", d1, p1, LocalDate.now());
        Account a2 = new Account(2, "NguyenVanB@gmail.com", "b.nguyen", "Nguyễn Văn B", null, p2, LocalDate.now());
        Account a3 = new Account(3, "NguyenVanC@gmail.com", "c.nguyen", "Nguyễn Văn C", d2, p3, LocalDate.now());
        Account a4 = new Account(4, "NguyenVanD@gmail.com", "d.nguyen", "Nguyễn Văn D", d3, p2, LocalDate.now());
        Account a5 = new Account(5, "NguyenVanE@gmail.com", "e.nguyen", "Nguyễn Văn E", d1, p1, LocalDate.now());

        Account[] accounts = {a1, a2, a3, a4, a5};

        GroupAccount ga1 = new GroupAccount(g1, a1, LocalDate.now());
        GroupAccount ga2 = new GroupAccount(g2, a1, LocalDate.now());
        GroupAccount ga3 = new GroupAccount(g3, a1, LocalDate.now());
        GroupAccount ga4 = new GroupAccount(g1, a2, LocalDate.now());
        GroupAccount ga5 = new GroupAccount(g2, a2, LocalDate.now());
        GroupAccount ga6 = new GroupAccount(g1, a3, LocalDate.now());
        GroupAccount ga7 = new GroupAccount(g2, a3, LocalDate.now());
        GroupAccount ga8 = new GroupAccount(g3, a3, LocalDate.now());
        GroupAccount ga9 = new GroupAccount(g4, a3, LocalDate.now());
        GroupAccount ga10 = new GroupAccount(g1, a4, LocalDate.now());

        GroupAccount[] groupAccounts = {ga1, ga2, ga3, ga4, ga5, ga6, ga7, ga8, ga9, ga10};

        CategoryQuestion ca1 = new CategoryQuestion(1, "JAVA");
        Exam exam1 = new Exam(1, "EX001", "Java Basic", ca1, 60, a1, LocalDateTime.now());

        List<Department> deptList = new ArrayList<>(Arrays.asList(departments));
        List<Position> posList = new ArrayList<>(Arrays.asList(positions));
        List<Account> accList = new ArrayList<>(Arrays.asList(accounts));
        List<Group> grpList = new ArrayList<>(Arrays.asList(groups));
        List<GroupAccount> gaList = new ArrayList<>(Arrays.asList(groupAccounts));


        //Exercise 1 (WHILE)
        Excercise1.question16_10(accounts);
        Excercise1.question16_11(departments);
        Excercise1.question16_12(departments);
        Excercise1.question16_13(accounts);
        Excercise1.question16_14(accounts);
        Excercise1.question16_15();

        //Exercise 1 (DO-WHILE)
        Excercise1.question17_10(accounts);
        Excercise1.question17_11(departments);
        Excercise1.question17_12(departments);
        Excercise1.question17_13(accounts);
        Excercise1.question17_14(accounts);
        Excercise1.question17_15();

        //Exercise 2
        Exercise2.question1(5);
        Exercise2.question2(100000000);
        Exercise2.question3(5.567098);
        Exercise2.question4(a1);
        Exercise2.question5(LocalDateTime.now());
        Exercise2.question6(accounts);

        //Exercise 3
        Excercise3.question1(exam1);
        Excercise3.question2(exam1);
        Excercise3.question3(exam1);
        Excercise3.question4(exam1);
        Excercise3.question5(exam1);

        //Exercise 4
        Excercise4.question1();
        Excercise4.question2();
        Excercise4.question3();
        Excercise4.question4();
        Excercise4.question5();
        Excercise4.question6();
        Excercise4.question7();

        //Exercise 5
        Excercise5.question1();
        Excercise5.question2();
        Excercise5.question3();
        Excercise5.question4();
        Excercise5.question5(deptList, posList, accList);
        Excercise5.question6(deptList);
        Excercise5.question7();
        Excercise5.question8(deptList, posList, accList, grpList, gaList);
        Excercise5.question9(accList, grpList, gaList);

        //Exercise 6
        Excercise6.question1();
        Excercise6.question2(accounts);
        Excercise6.question3();
    }
}
