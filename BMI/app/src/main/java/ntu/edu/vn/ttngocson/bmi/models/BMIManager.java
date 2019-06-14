package ntu.edu.vn.ttngocson.bmi.models;

import android.content.Context;

import java.util.ArrayList;

import ntu.edu.vn.ttngocson.bmi.R;

public class BMIManager {
    ArrayList<UserBMI> arrUserBMI;
    private static BMIManager bmiManager;
    private BMIManager(){
        arrUserBMI = new ArrayList<>();
        arrUserBMI.add(new UserBMI(0,15, R.string.bmi0));
        arrUserBMI.add(new UserBMI(15,16, R.string.bmi15));
        arrUserBMI.add(new UserBMI(16,(float) 18.5, R.string.bmi16));
        arrUserBMI.add(new UserBMI((float) 18.5,25, R.string.bmi18_5));
        arrUserBMI.add(new UserBMI(25,30, R.string.bmi25));
        arrUserBMI.add(new UserBMI(30,35, R.string.bmi30));
        arrUserBMI.add(new UserBMI(35,40, R.string.bmi35));
        arrUserBMI.add(new UserBMI(40, Float.MAX_VALUE, R.string.bmi40));
    }
    public static BMIManager getInstance(Context context){
        if (bmiManager==null)
            bmiManager = new BMIManager();
        return bmiManager;
    }
    public int getBMI(float bmi){
        int resID = -1;
        for(UserBMI userBMI:arrUserBMI){
            if(userBMI.isCurrentBMI(bmi)){
                resID = userBMI.getBMI_ctg();
                break;
            }
        }
        return resID;
    }
}
