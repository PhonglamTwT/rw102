package frontend;

import backend.QuanLySach;
import entity.Bao;
import entity.Sach;
import entity.TaiLieu;
import entity.TapChi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        List<TaiLieu> listTaiLieu = new ArrayList<>();
        Sach s1 = new Sach("TL101", "NXB Trẻ", 1000, "Nguyễn Nhật Ánh", 250);
        listTaiLieu.addAll(Arrays.asList(
                new Sach("TL101", "NXB Trẻ", 1000, "Nguyễn Nhật Ánh", 250),
                new Sach("TL102", "NXB Kim Đồng", 500, "Tô Hoài", 120),
                new Sach("TL201", "NXB Giáo Dục", 1500, "Nhiều tác giả", 300),
                new Sach("TL202", "NXB Lao Động", 800, "Dale Carnegie", 320),
                new Sach("TL301", "NXB Trẻ", 600, "J.K. Rowling", 450),
                new TapChi("TL101_TC", "NXB Thanh Niên", 200, 15, 5),
                new TapChi("TL102_TC", "NXB Lao Động", 300, 20, 6),
                new TapChi("TL201_TC", "NXB Phụ Nữ", 150, 8, 4),
                new TapChi("TL301_TC", "NXB Văn Học", 400, 12, 10),
                new TapChi("TL401_TC", "NXB Trẻ", 250, 5, 1),
                new Bao("B101", "NXB Tiền Phong", 5000, "15/05/2024"),
                new Bao("B102", "NXB Tuổi Trẻ", 8000, "16/05/2024"),
                new Bao("B201", "NXB Nhân Dân", 3000, "17/05/2024"),
                new Bao("B202", "NXB Tuổi Trẻ", 6000, "18/05/2024"),
                new Bao("B301", "NXB Hà Nội Mới", 4000, "19/05/2024")
        ));

        QuanLySach quanLySach = new QuanLySach(listTaiLieu);
        quanLySach.menu();
    }
}
