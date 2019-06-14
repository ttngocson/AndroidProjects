package ntu.edu.vn.ttngocson.monthi.models;

import java.util.ArrayList;

public class MonThiManager {

    ArrayList<MonThi> list;
    private static MonThiManager instance;

    private MonThiManager(){
        list = new ArrayList<>();
        list.add(new MonThi("Lập trình TBDD", "11/6/2019", "Phòng máy", "7h30", "90", "G8.201"));
        list.add(new MonThi("Cấu trúc dữ liệu", "15/6/2019", "Phòng máy", "7h30", "90", "G8.201"));
        list.add(new MonThi("Thiết kế web", "17/6/2019", "Phòng máy", "7h30", "90", "G8.201"));
        list.add(new MonThi("Công nghệ phần mềm", "19/6/2019", "Phòng máy", "7h30", "90", "G8.201"));
        list.add(new MonThi("Trí tuệ nhân tạo", "21/6/2019", "Phòng máy", "7h30", "90", "G8.201"));
    }

    public static MonThiManager getInstance(){
        if(instance == null){
            instance = new MonThiManager();
            return instance;
        }
        return instance;
    }

    public ArrayList<MonThi> getList() {
        return list;
    }
    public void ModMonThi(int position, MonThi mt){
        list.set(position, mt);
    }
}
