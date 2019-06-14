package ntu.edu.vn.ttngocson.kt2.models;

public class MonHoc {
    String TenMH;
    int SoTC;
    float diemKT, diemT;

    public MonHoc(String tenMH, int soTC, float diemKT, float diemT) {
        TenMH = tenMH;
        SoTC = soTC;
        this.diemKT = diemKT;
        this.diemT = diemT;
    }

    public String getTenMH() {
        return TenMH;
    }

    public void setTenMH(String tenMH) {
        TenMH = tenMH;
    }

    public int getSoTC() {
        return SoTC;
    }

    public void setSoTC(int soTC) {
        SoTC = soTC;
    }

    public float getDiemKT() {
        return diemKT;
    }

    public void setDiemKT(float diemKT) {
        this.diemKT = diemKT;
    }

    public float getDiemT() {
        return diemT;
    }

    public void setDiemT(float diemT) {
        this.diemT = diemT;
    }
}
