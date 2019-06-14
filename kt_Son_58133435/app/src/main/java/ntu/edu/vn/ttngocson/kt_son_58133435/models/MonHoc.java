package ntu.edu.vn.ttngocson.kt_son_58133435.models;

public class MonHoc {
    String monThi, ngayThi, hinhThucThi, timeBD, timeLB, phongThi;
    boolean thi;

    public MonHoc(String monThi, String ngayThi, String hinhThucThi, String timeBD, String timeLB, String phongThi, boolean thi) {
        this.monThi = monThi;
        this.ngayThi = ngayThi;
        this.hinhThucThi = hinhThucThi;
        this.timeBD = timeBD;
        this.timeLB = timeLB;
        this.phongThi = phongThi;
        this.thi = thi;
    }

    public String getMonThi() {
        return monThi;
    }

    public void setMonThi(String monThi) {
        this.monThi = monThi;
    }

    public String getNgayThi() {
        return ngayThi;
    }

    public void setNgayThi(String ngayThi) {
        this.ngayThi = ngayThi;
    }

    public String getHinhThucThi() {
        return hinhThucThi;
    }

    public void setHinhThucThi(String hinhThucThi) {
        this.hinhThucThi = hinhThucThi;
    }

    public String getTimeBD() {
        return timeBD;
    }

    public void setTimeBD(String timeBD) {
        this.timeBD = timeBD;
    }

    public String getTimeLB() {
        return timeLB;
    }

    public void setTimeLB(String timeLB) {
        this.timeLB = timeLB;
    }

    public String getPhongThi() {
        return phongThi;
    }

    public void setPhongThi(String phongThi) {
        this.phongThi = phongThi;
    }

    public boolean isThi() {
        return thi;
    }

    public void setThi(boolean thi) {
        this.thi = thi;
    }

    @Override
    public String toString() {
        return monThi;
    }
}
