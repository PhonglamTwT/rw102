import entity.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        Department d1 = new Department(1, "Sale");
        Department d2 = new Department(2, "Marketing");
        Department d3 = new Department(3, "IT");
        Department[] departments = {d1, d2, d3};

        Position p1 = new Position(1, PositionName.DEV);
        Position p2 = new Position(2, PositionName.TEST);
        Position p3 = new Position(3, PositionName.PM);

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

//        IF
//        Question 1:
//        Kiểm tra account thứ 2
//        Nếu không có phòng ban (tức là department == null) thì sẽ in ra text
//        "Nhân viên này chưa có phòng ban"
//        Nếu không thì sẽ in ra text "Phòng ban của nhân viên này là …"
        Account tempAccount = accounts[1];
        if (tempAccount.getDepartment()==null) {
            System.out.println("Q1. Nhan vien nay chua co phong ban");
        }else {
            System.out.println("Q1. Phong ban cua nhan vien nay la "+tempAccount.getDepartment().getDepartmentName());
        }

//        Question 2:
//        Kiểm tra account thứ 2
//        Nếu không có group thì sẽ in ra text "Nhân viên này chưa có group"
//        Nếu có mặt trong 1 hoặc 2 group thì sẽ in ra text "Group của nhân viên này là Java Fresher, C# Fresher"
//        Nếu có mặt trong 3 Group thì sẽ in ra text "Nhân viên này là người quan trọng, tham gia nhiều group"
//        Nếu có mặt trong 4 group trở lên thì sẽ in ra text "Nhân viên này là người hóng chuyện, tham gia tất cả các group"
        List<Group> employeeGroups = new ArrayList<>();
        for ( GroupAccount groupAccount : groupAccounts ) {
            if (groupAccount.getAccount().getAccountId()==tempAccount.getAccountId()) {
                boolean exist = false;
                for (Group group : employeeGroups) {
                    if (group.getGroupId() == groupAccount.getGroup().getGroupId()) {
                        exist = true;
                        break;
                    }
                }
                if (!exist) {
                    employeeGroups.add(groupAccount.getGroup());
                }
            } 
        }
        System.out.printf("Q2. ");
        if (employeeGroups.size()==0) {
            System.out.println("Nhân viên này chưa có group");
        } else if (employeeGroups.size()<=2) {
            System.out.println("Group của nhân viên này là:");
            for (Group group : employeeGroups) {
                System.out.println(group.getGroupName());
            }
        } else if (employeeGroups.size()==3) {
            System.out.println("Nhân viên này là người quan trọng, tham gia nhiều group");
        } else if (employeeGroups.size()>=4) {
            System.out.println("Nhân viên này là người hóng chuyện, tham gia tất cả các group");
        }

//        Question 3:
//        Sử dụng toán tử ternary để làm Question 1
        System.out.println(tempAccount.getDepartment()==null
                ? "Q3. Nhan vien nay chua co phong ban"
                : "Q3. Phong ban cua nhan vien nay la " + tempAccount.getDepartment().getDepartmentName());

//        Question 4:
//        Sử dụng toán tử ternary để làm yêu cầu sau:
//        Kiểm tra Position của account thứ 1
//        Nếu Position = Dev thì in ra text "Đây là Developer"
//        Nếu không phải thì in ra text "Người này không phải là Developer"
        Account firstAccount = accounts[0];
        System.out.println(firstAccount.getPosition().getPositionName()==PositionName.DEV
                ? "Q4. Đây là Developer"
                : "Q4. Người này không phải là Developer ");

//        SWITCH CASE
//        Question 5:
//        Lấy ra số lượng account trong nhóm thứ 1 và in ra theo format sau: Nếu số lượng account = 1 thì in ra "Nhóm có một thành viên"
//        Nếu số lượng account = 2 thì in ra "Nhóm có hai thành viên"
//        Nếu số lượng account = 3 thì in ra "Nhóm có ba thành viên"
//        Còn lại in ra "Nhóm có nhiều thành viên"
        Group tempGroup =  groups[0];
        int countAccounts = 0;
        for ( GroupAccount groupAccount: groupAccounts ) {
            if (tempGroup.getGroupId()==groupAccount.getGroup().getGroupId()) {
                countAccounts++;
            }
        }
        System.out.printf("Q5. ");
        switch (countAccounts) {
            case 1:
                System.out.println("Nhóm có một thành viên");
                break;
            case 2:
                System.out.println("Nhóm có hai thành viên");
                break;
            case 3:
                System.out.println("Nhóm có ba thành viên");
                break;
            default:
                System.out.println("Nhóm có nhiều thành viên");
        }
//        Question 6:
//        Sử dụng switch case để làm lại Question 2
        System.out.printf("Q6. ");
        switch (employeeGroups.size()) {
            case 0:
                System.out.println("Nhân viên này chưa có group");
                break;
            case 1:
            case 2:
                System.out.println("Group của nhân viên này là:");
                for (Group group : employeeGroups) {
                    System.out.println(group.getGroupName());
                }
                break;
            case 3:
                System.out.println("Nhân viên này là người quan trọng, tham gia nhiều group");
                break;
            default:
                System.out.println("Nhân viên này là người hóng chuyện, tham gia tất cả các group");
        }
//        Question 7:
//        Sử dụng switch case để làm lại Question 4
        System.out.print("Q7. ");
        switch (firstAccount.getPosition().getPositionName()) {
            case DEV:
                System.out.println("Đây là Developer");
                break;
            default:
                System.out.println("Người này không phải là Developer");
        }

//        FOREACH
//        Question 8:
//        In ra thông tin các account bao gồm: Email, FullName và tên phòng ban của họ
        System.out.println("Q8. Danh sách account: ");
        int i = 0;
        for (Account account : accounts) {
            System.out.println("Nhan vien " + (i+1));
            System.out.println("Email: " + account.getEmail());
            System.out.println("FullName: " + account.getFullName());
            System.out.println("Phong ban: " + (account.getDepartment()==null? "Chua co phong ban" :  account.getDepartment().getDepartmentName()));
            System.out.println();
            i++;
        }

//        Question 9:
//        In ra thông tin các phòng ban bao gồm: id và name
        System.out.println("Q9. Danh sách phong ban: ");
        i=0;
        for (Department department : departments) {
            System.out.println("Department " + (i+1));
            System.out.println("Id: " + department.getDepartmentId());
            System.out.println("Name: " + department.getDepartmentName());
            System.out.println();
            i++;
        }

//        FOR
//        Question 10:
//        In ra thông tin các account bao gồm: Email, FullName và tên phòng ban của họ theo định dạng như sau:
//        Thông tin account thứ 1 là:
//        Email: NguyenVanA@gmail.com
//        Full name: Nguyễn Văn A
//        Phòng ban: Sale
        System.out.println("Q10. Danh sách account: ");
        for (int j = 0; j < accounts.length; j++) {
            System.out.println("Thông tin account thứ "+(j+1)+" là:");
            System.out.println("Id: " + accounts[j].getAccountId());
            System.out.println("Email: " + accounts[j].getEmail());
            System.out.println("FullName: " + accounts[j].getFullName());
            System.out.println("Phong ban: " + (accounts[j].getDepartment()==null? "Chua co phong ban" :  accounts[j].getDepartment().getDepartmentName()));
            System.out.println();
        }

//        Question 11:
//        In ra thông tin các phòng ban bao gồm: id và name theo định dạng sau:
//        Thông tin department thứ 1 là:
//        Id: 1
//        Name: Sale
        System.out.println("Q11. Danh sách phong ban: ");
        for (int k = 0; k < departments.length; k++) {
            System.out.println("Thông tin phong ban thứ "+(k+1)+" là:");
            System.out.println("Id: " + departments[k].getDepartmentId());
            System.out.println("Name: " + departments[k].getDepartmentName());
            System.out.println();
        }

//        Question 12:
//        Chỉ in ra thông tin 2 department đầu tiên theo định dạng như Question 10
        System.out.println("Q12. Danh sách phong ban: ");
        for (int k = 0; k<2 && k< departments.length; k++) {
            System.out.println("Thông tin phong ban thứ "+(k+1)+" là:");
            System.out.println("Id: " + departments[k].getDepartmentId());
            System.out.println("Name: " + departments[k].getDepartmentName());
            System.out.println();
        }

//        Question 13:
//        In ra thông tin tất cả các account ngoại trừ account thứ 2
        System.out.println("Q13. Danh sách account: ");
        for (int j = 0; j < accounts.length; j++) {
            if (j==1) {
                continue;
            }
            System.out.println("Thông tin account thứ "+(j+1)+" là:");
            System.out.println("Id: " + accounts[j].getAccountId());
            System.out.println("Email: " + accounts[j].getEmail());
            System.out.println("FullName: " + accounts[j].getFullName());
            System.out.println("Phong ban: " + (accounts[j].getDepartment()==null? "Chua co phong ban" :  accounts[j].getDepartment().getDepartmentName()));
            System.out.println();
        }

//        Question 14:
//        In ra thông tin tất cả các account có id < 4
        System.out.println("Q14. Danh sách account co id < 4: ");
        for (int j = 0; j < accounts.length; j++) {
            if (accounts[j].getAccountId()<4) {
                System.out.println("Thông tin account thứ "+(j+1)+" là:");
                System.out.println("Id: " + accounts[j].getAccountId());
                System.out.println("Email: " + accounts[j].getEmail());
                System.out.println("FullName: " + accounts[j].getFullName());
                System.out.println("Phong ban: " + (accounts[j].getDepartment()==null? "Chua co phong ban" :  accounts[j].getDepartment().getDepartmentName()));
                System.out.println();
            }
        }

//        Question 15:
//        In ra các số chẵn nhỏ hơn hoặc bằng 20
        System.out.print("Q15. Các số chẵn nhỏ hơn hoặc bằng 20 là: ");
        for (int j = 0; j <=20; j++) {
            if (j%2==0) {
                System.out.print(j+" ");
            }
        }
    }
}
