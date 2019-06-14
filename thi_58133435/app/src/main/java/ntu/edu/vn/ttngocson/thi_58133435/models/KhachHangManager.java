package ntu.edu.vn.ttngocson.thi_58133435.models;

import java.util.ArrayList;

public class KhachHangManager {
    ArrayList<KhachHang> khachHangs;
    private static KhachHangManager manager;

    private KhachHangManager(){
        khachHangs = new ArrayList<>();
        khachHangs.add(new KhachHang("Nguyễn Văn Cường", "0968 123456", "Hà Nội", false));
        khachHangs.add(new KhachHang("Trần Văn Tám", "0968 39876", "Đà Nẵng", true));
        khachHangs.add(new KhachHang("Nguyễn Thị Hoa", "0654 123456", "Ninh Thuận", false));
        khachHangs.add(new KhachHang("Võ Văn Bảy", "0968 678456", "Khánh Hòa", true));
        khachHangs.add(new KhachHang("Lê Bảo", "0968 98456", "TP. Hồ Chí Minh", true));
        khachHangs.add(new KhachHang("Lê Vân", "037 123456 ", "Bình Phước", false));
    }

    public static KhachHangManager getInstance(){
        if(manager == null){
            manager = new KhachHangManager();
            return manager;
        }
        return manager;
    }

    public ArrayList<KhachHang> getKhachHangs() {
        return khachHangs;
    }

    public void ModKhachHang(int position, KhachHang kh){
        khachHangs.set(position, kh);
    }
}
