import entity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        Department d1 = new Department(1, "Sale");
        Department d2 = new Department(2, "Marketing");
        Department d3 = new Department(3, "IT");
        Department d4 = new Department(4, "A");
        Department d5 = new Department(5, "B");
        Department[] departments = {d1, d2, d3,  d4, d5};

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
        Group g6 = new Group(6, "Java", null, LocalDate.now());
        Group g7 = new Group(7, "Java Middle", null, LocalDate.now());

        Group[] groups = {g1, g2, g3, g4, g5,  g6, g7};

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


//        Exercise 4: String
//        Question 1:
//        Nhập một xâu kí tự, đếm số lượng các từ trong xâu kí tự đó (các từ có thể cách nhau bằng nhiều khoảng trắng );
//        Excercise4.question1();
//
//        Question 2:
//        Nhập hai xâu kí tự s1, s2 nối xâu kí tự s2 vào sau xâu s1;
//        Excercise4.question2();

//        Question 3:
//        Viết chương trình để người dùng nhập vào tên và kiểm tra, nếu tên chữ viết hoa chữ cái đầu thì viết hoa lên.
//        Excercise4.question3();

//        Question 4:
//        Viết chương trình để người dùng nhập vào tên in từng ký tự trong tên của người dùng ra
//        VD:
//        Người dùng nhập vào "Nam", hệ thống sẽ in ra
//        "Ký tự thứ 1 là: N"
//        "Ký tự thứ 1 là: A"
//        "Ký tự thứ 1 là: M"
//        Excercise4.question4();
//
//        Question 5:
//        Viết chương trình để người dùng nhập vào họ, sau đó yêu cầu người dùng nhập vào tên và hệ thống sẽ in ra họ và tên đầy đủ.
//        Excercise4.question5();
//
//        Question 6:
//        Viết chương trình yêu cầu người dùng nhập vào họ và tên đầy đủ và sau đó hệ thống sẽ tách ra họ, tên , tên đệm
//        VD:
//        Người dùng nhập vào "Nguyễn Văn Nam"
//        Hệ thống sẽ in ra
//        "Họ là: Nguyễn"
//        "Tên đệm là: Văn"
//        "Tên là: Nam"
//        Excercise4.question6();
//
//        Question 7:
//        Viết chương trình yêu cầu người dùng nhập vào họ và tên đầy đủ và chuẩn hóa họ và tên của họ như sau:
//        a) Xóa dấu cách ở đầu và cuối và giữa của chuỗi người dùng nhập vào
//        VD: Nếu người dùng nhập vào " nguyễn văn nam " thì sẽ chuẩn hóa thành "nguyễn văn   nam"
//        b) Viết hoa chữ cái mỗi từ của người dùng
//        VD: Nếu người dùng nhập vào " nguyễn văn nam " thì sẽ chuẩn hóa thành "Nguyễn Văn Nam"
//        Excercise4.question7();

//        Question 8:
//        In ra tất cả các group có chứa chữ "Java"
//        Excercise4.question8(grpList);
//        Question 9:
//        In ra tất cả các group "Java"
//        Excercise4.question9(grpList);

//        Question 10:
//        Kiểm tra 2 chuỗi có là đảo ngược của nhau hay không.
//        Nếu có xuất ra “OK” ngược lại “KO”.
//        Ví dụ “word” và “drow” là 2 chuỗi đảo ngược nhau.
//        Excercise4.question10();

//        Question 11: Count special Character
//        Tìm số lần xuất hiện ký tự "a" trong chuỗi
//        Excercise4.question11();

//        Question 12: Reverse String
//        Đảo ngược chuỗi sử dụng vòng lặp
//        Excercise4.question12();

//        Question 13:
//        String not contains digit
//        Kiểm tra một chuỗi có chứa chữ số hay không, nếu có in ra false ngược lại true.
//        Ví dụ:
//        "abc" => true
//        "1abc", "abc1", "123", "a1bc", null => false
//        Excercise4.question13();

//        Question 14: Replace character
//        Cho một chuỗi str, chuyển các ký tự được chỉ định sang một ký tự khác cho trước.
//        Ví dụ:
//        "VTI Academy" chuyển ký tự 'e' sang '*' kết quả " VTI Acad*my"
//        Excercise4.question14();

//        Question 15: Revert string by word
//        Đảo ngược các ký tự của chuỗi cách nhau bởi dấu cách mà không dùng thư viện.
//        Ví dụ: " I am developer " => "developer am I".
//        Các ký tự bên trong chỉ cách nhau đúng một dấu khoảng cách.
//        Gợi ý: Các bạn cần loại bỏ dấu cách ở đầu và cuối câu, thao tác cắt chuỗi theo dấu cách
//        Excercise4.question15();

//        Question 16:
//        Cho một chuỗi str và số nguyên n >= 0. Chia chuỗi str ra làm các phần bằng nhau với n ký tự. Nếu chuỗi không chia được thì xuất ra màn hình “KO”.
//        Excercise4.question16();

//        Exercise 5: Object’s Method
//        Question 5:
//        So sánh 2 phòng ban thứ 1 và phòng ban thứ 2 xem có bằng nhau không (bằng nhau khi tên của 2 phòng ban đó bằng nhau)
//        Excercise5.question5(deptList);
//
//        Question 6:
//        Khởi tạo 1 array phòng ban gồm 5 phòng ban, sau đó in ra danh sách phòng ban theo thứ tự tăng dần theo tên (sắp xếp theo vần ABCD)
//        VD:
//        Accounting
//        Boss of director
//        Marketing
//        Sale
//        Waiting room
//        Excercise5.question6(deptList);

//        Question 7:
//        Khởi tạo 1 array học sinh gồm 5 Phòng ban, sau đó in ra dan sách phòng ban được sắp xếp theo tên
//        VD:
//        Accounting
//        Boss of director
//        Marketing
//        waiting room
//        Sale
//        Excercise5.question7(accList);

    }
}
