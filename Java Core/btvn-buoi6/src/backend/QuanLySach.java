package backend;

import entity.Bao;
import entity.Sach;
import entity.TaiLieu;
import entity.TapChi;

import javax.naming.directory.SearchResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuanLySach {
    private List<TaiLieu> listTaiLieu = new ArrayList<>();

    public QuanLySach(List<TaiLieu> listTaiLieu) {
        this.listTaiLieu = listTaiLieu;
    }

    public void danhSachTaiLieu(List<? extends TaiLieu> listTaiLieu) {
        if (listTaiLieu.size() == 0) {
            System.out.println("Danh sách rỗng");
            return;
        }
        System.out.println("+----------+--------------------+--------------------+");
        System.out.printf("|%-10s|%-20s|%-20s|\n", "MA", "NXB", "SL PHAT HANH");
        System.out.println("+----------+--------------------+--------------------+");
        for (TaiLieu taiLieu : listTaiLieu) {
            System.out.printf("|%-10s|%-20s|%-20d|\n", taiLieu.getMaTaiLieu(), taiLieu.getTenNhaXuatBan(), taiLieu.getSoBanPhatHanh());
        }
        System.out.println("+----------+--------------------+--------------------+");
    }

    public void danhSachSach(List<TaiLieu> listSach) {
        if (listSach == null || listSach.isEmpty()) {
            System.out.println("Danh sách Sách rỗng");
            return;
        }
        System.out.println("+----------+--------------------+--------------------+--------------------+----------+");
        System.out.printf("|%-10s|%-20s|%-20s|%-20s|%-10s|\n", "MA", "NXB", "SL PHAT HANH", "TAC GIA", "SO TRANG");
        System.out.println("+----------+--------------------+--------------------+--------------------+----------+");
        for (TaiLieu item : listSach) {
            if (item instanceof Sach s) {
                System.out.printf("|%-10s|%-20s|%-20d|%-20s|%-10d|\n",
                        s.getMaTaiLieu(), s.getTenNhaXuatBan(), s.getSoBanPhatHanh(), s.getTacGia(), s.getSoTrang());
            }
        }
        System.out.println("+----------+--------------------+--------------------+--------------------+----------+");
    }

    public void danhSachTapChi(List<TaiLieu> listTapChi) {
        if (listTapChi == null || listTapChi.isEmpty()) {
            System.out.println("Danh sách Tạp chí rỗng");
            return;
        }
        System.out.println("+----------+--------------------+--------------------+----------+----------+");
        System.out.printf("|%-10s|%-20s|%-20s|%-10s|%-10s|\n", "MA", "NXB", "SL PHAT HANH", "SO PH", "THANG PH");
        System.out.println("+----------+--------------------+--------------------+----------+----------+");
        for (TaiLieu item : listTapChi) {
            if (item instanceof TapChi tc) {
                System.out.printf("|%-10s|%-20s|%-20d|%-10d|%-10d|\n",
                        tc.getMaTaiLieu(), tc.getTenNhaXuatBan(), tc.getSoBanPhatHanh(), tc.getSoPhatHanh(), tc.getThangPhatHanh());
            }
        }
        System.out.println("+----------+--------------------+--------------------+----------+----------+");
    }

    public void danhSachBao(List<TaiLieu> listBao) {
        if (listBao == null || listBao.isEmpty()) {
            System.out.println("Danh sách Báo rỗng");
            return;
        }
        System.out.println("+----------+--------------------+--------------------+--------------------+");
        System.out.printf("|%-10s|%-20s|%-20s|%-20s|\n", "MA", "NXB", "SL PHAT HANH", "NGAY PHAT HANH");
        System.out.println("+----------+--------------------+--------------------+--------------------+");
        for (TaiLieu item : listBao) {
            if (item instanceof Bao b) {
                System.out.printf("|%-10s|%-20s|%-20d|%-20s|\n",
                        b.getMaTaiLieu(), b.getTenNhaXuatBan(), b.getSoBanPhatHanh(), b.getNgayPhatHanh());
            }
        }
        System.out.println("+----------+--------------------+--------------------+--------------------+");
    }

    public void searchTaiLieu() {
        if (listTaiLieu.size() == 0) {
            System.out.println("Không có tài liệu nào trong danh sách");
            return;
        }
        Scanner sc = new Scanner(System.in);
        List<Integer> options = List.of(1, 2, 3);
        int option;

        while (true) {
            System.out.println("\nMời nhập loại tài liệu cần tìm:");
            System.out.println("1. Báo");
            System.out.println("2. Sách");
            System.out.println("3. Tạp Chí");
            System.out.print("Chọn: ");

            if (sc.hasNextInt()) {
                option = sc.nextInt();
                if (options.contains(option)) {
                    sc.nextLine();
                    break;
                }
            } else {
                sc.next();
            }
            System.out.println("Nhập sai Option, vui lòng nhập lại (1 - 3)!");
        }

        Class<?> targetClass = switch (option) {
            case 1 -> Bao.class;
            case 2 -> Sach.class;
            case 3 -> TapChi.class;
            default -> null;
        };

        List<TaiLieu> listTheoLoai = new ArrayList<>();
        for (TaiLieu taiLieu : listTaiLieu) {
            if (targetClass.isInstance(taiLieu)) {
                listTheoLoai.add(taiLieu);
            }
        }

        System.out.println("\n==== DANH SÁCH TÀI LIỆU THUỘC LOẠI ĐÃ CHỌN ====");
        switch (option) {
            case 1:
                danhSachBao(listTheoLoai);
                break;
            case 2:
                danhSachSach(listTheoLoai);
                break;
            case 3:
                danhSachTapChi(listTheoLoai);
                break;
        };

        System.out.print("Mời nhập mã cần tìm: ");
        String ma = sc.nextLine().trim();

        List<TaiLieu> foundList = new ArrayList<>();
        for (TaiLieu taiLieu : listTheoLoai) {
            if (taiLieu.getMaTaiLieu().toLowerCase().contains(ma.toLowerCase())) {
                foundList.add(taiLieu);
            }
        }

        if (foundList.isEmpty()) {
            System.out.println("Không tìm thấy dữ liệu phù hợp!");
        } else {
            System.out.println("\n==== KẾT QUẢ TÌM KIẾM ====");
            switch (option) {
                case 1:
                    danhSachBao(foundList);
                    break;
                case 2:
                    danhSachSach(foundList);
                    break;
                case 3:
                    danhSachTapChi(foundList);
                    break;
            }
        }

    }

    public void themMoi() {
        Scanner sc = new Scanner(System.in);
        System.out.println("==== THÊM MỚI TÀI LIỆU ====");
        System.out.println("Nhập mã tài liệu: ");
        String maTaiLieu = sc.nextLine();

        System.out.println("Nhập tên nhà xuất bản: ");
        String tenNhaXuatBan = sc.nextLine();

        System.out.println("Nhập số bản phát hành: ");
        int soBanPhatHanh = 0;
        while (true) {
            if (!sc.hasNextInt()) {
                sc.nextLine();
                System.out.println("Vui lòng nhập số!");
            } else {
                soBanPhatHanh = sc.nextInt();
                sc.nextLine();
                if (soBanPhatHanh <= 0) {
                    System.out.println("Vui lòng nhập số dương!");
                } else {
                    break;
                }
            }
        }

        System.out.println("Nhập loại tài liệu: 1. Sách  2. Tạp chí  3. Báo");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                System.out.println("Nhập tên tác giả: ");
                String tacGia = sc.nextLine();
                System.out.println("Nhập số trang: ");
                int soTrang = 0;

                while (true) {
                    if (!sc.hasNextInt()) {
                        sc.nextLine();
                        System.out.println("Vui lòng nhập số!");
                    } else {
                        soTrang = sc.nextInt();
                        sc.nextLine();
                        if (soTrang <= 0) {
                            System.out.println("Vui lòng nhập số dương!");
                        } else {
                            break;
                        }
                    }
                }
                TaiLieu sach = new Sach(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, tacGia, soTrang);
                listTaiLieu.add(sach);
                System.out.println("Thêm sách thành công");
                break;
            case "2":
                System.out.println("Nhập số phát hành: ");
                int soPhatHanh = 0;

                while (true) {
                    if (!sc.hasNextInt()) {
                        sc.nextLine();
                        System.out.println("Vui lòng nhập số!");
                    } else {
                        soPhatHanh = sc.nextInt();
                        sc.nextLine();
                        if (soPhatHanh <= 0) {
                            System.out.println("Vui lòng nhập số dương!");
                        } else {
                            break;
                        }
                    }
                }

                System.out.println("Nhập tháng phát hành: ");
                int thangPhatHanh = 0;

                while (true) {
                    if (!sc.hasNextInt()) {
                        sc.nextLine();
                        System.out.println("Vui lòng nhập số!");
                    } else {
                        thangPhatHanh = sc.nextInt();
                        sc.nextLine();
                        if (thangPhatHanh < 1 || thangPhatHanh > 12) {
                            System.out.println("Vui lòng nhập tháng từ 1 đến 12!");
                        } else {
                            break;
                        }
                    }
                }
                TaiLieu tapChi = new TapChi(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, soPhatHanh, thangPhatHanh);
                listTaiLieu.add(tapChi);
                System.out.println("Thêm tạp chí thành công");
                break;

            case "3":
                System.out.println("Nhập ngày phát hành (dd/MM/yyyy): ");
                String ngayPhatHanh = sc.nextLine();
                TaiLieu bao = new Bao(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, ngayPhatHanh);
                listTaiLieu.add(bao);
                System.out.println("Thêm báo thành công");
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ, vui lòng chỉ chọn (1, 2 hoặc 3)!");
        }
    }

    public void xoaTheoMa() {
        Scanner sc = new Scanner(System.in);
        System.out.println("==== XÓA TÀI LIỆU ====");
        System.out.println("Nhập mã tài liệu cần xóa: ");
        String ma = sc.nextLine().trim();

        List<TaiLieu> removes = new ArrayList<>();
        for (TaiLieu tl : listTaiLieu) {
            if (tl.getMaTaiLieu().equalsIgnoreCase(ma)) {
                removes.add(tl);
            }
        }

        if (removes.isEmpty()) {
            System.out.println("Mã tài liệu này không có trong hệ thống");
        } else {
            listTaiLieu.removeAll(removes);
            System.out.println("Xóa thành công");
        }
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("==== QUẢN LÝ TÀI LIỆU ====");
            System.out.println("1. Thêm tài liệu");
            System.out.println("2. Xóa tài liệu");
            System.out.println("3. Hiển thị tất cả tài liệu");
            System.out.println("4. Tìm kiếm tài liệu theo loại và mã");
            System.out.println("5. Thoát");
            System.out.println("Mời nhập option: ");

            if (!sc.hasNextInt()) {
                System.out.println("Vui lòng nhập số!");
                sc.next();
                continue;
            }

            int option = sc.nextInt();
            sc.nextLine();
            switch (option){
                case 1:
                    themMoi();
                    break;
                case 2:
                    System.out.println("==== DANH SÁCH TÀI LIỆU ====");
                    danhSachTaiLieu(listTaiLieu);
                    xoaTheoMa();
                    break;
                case 3:
                    System.out.println("==== DANH SÁCH TÀI LIỆU ====");
                    danhSachTaiLieu(listTaiLieu);
                    break;
                case 4:
                    searchTaiLieu();
                    break;
                case 5:
                    System.exit(0);
                default:
            }
        }
    }
}
