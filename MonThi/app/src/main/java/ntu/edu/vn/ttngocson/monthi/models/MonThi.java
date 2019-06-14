package ntu.edu.vn.ttngocson.monthi.models;

public class MonThi {
    String monThi, ngayThi, HTThi, timBD, timeLB, phongThi;

    public MonThi(String monThi, String ngayThi, String HTThi, String timBD, String timeLB, String phongThi) {
        this.monThi = monThi;
        this.ngayThi = ngayThi;
        this.HTThi = HTThi;
        this.timBD = timBD;
        this.timeLB = timeLB;
        this.phongThi = phongThi;
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

    public String getHTThi() {
        return HTThi;
    }

    public void setHTThi(String HTThi) {
        this.HTThi = HTThi;
    }

    public String getTimBD() {
        return timBD;
    }

    public void setTimBD(String timBD) {
        this.timBD = timBD;
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
}
