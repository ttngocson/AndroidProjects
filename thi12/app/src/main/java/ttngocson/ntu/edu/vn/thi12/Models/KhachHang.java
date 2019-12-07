package ttngocson.ntu.edu.vn.thi12.Models;

public class KhachHang {
    String TenKH, SoDT, DiaChi;
    boolean KHTT;

    public KhachHang(String tenKH, String soDT, String diaChi, boolean KHTT) {
        this.TenKH = tenKH;
        this.SoDT = soDT;
        this.DiaChi = diaChi;
        this.KHTT = KHTT;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String tenKH) {
        TenKH = tenKH;
    }

    public String getSoDT() {
        return SoDT;
    }

    public void setSoDT(String soDT) {
        SoDT = soDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public boolean isKHTT() {
        return KHTT;
    }

    public void setKHTT(boolean KHTT) {
        this.KHTT = KHTT;
    }
}
