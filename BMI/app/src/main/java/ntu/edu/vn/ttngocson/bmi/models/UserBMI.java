package ntu.edu.vn.ttngocson.bmi.models;

public class UserBMI {
    float from, to;
    int BMI_ctg;
    public UserBMI(float from, float to, int BMI_ctg){
        this.from = from;
        this.to = to;
        this.BMI_ctg = BMI_ctg;
    }
    public int getBMI_ctg(){
        return BMI_ctg;
    }
    public boolean isCurrentBMI (float bmi){
        if(bmi >= from && bmi < to)
            return true;
        return false;
    }
}
