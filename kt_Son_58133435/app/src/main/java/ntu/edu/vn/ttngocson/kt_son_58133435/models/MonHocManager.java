package ntu.edu.vn.ttngocson.kt_son_58133435.models;

import java.util.ArrayList;

public class MonHocManager {
    ArrayList<MonHoc> monHocs;
    private static MonHocManager manager;

    private MonHocManager(){
        monHocs = new ArrayList<>();
        monHocs.add(new MonHoc("Lập trình thiết bị DĐ", "11/6/2019",
                "Phòng máy", "7h30","90","G8.201", true));
        monHocs.add(new MonHoc("Cấu trúc dữ liệu", "15/6/2019",
                "Phòng máy", "7h30","90","G8.201", true));
        monHocs.add(new MonHoc("Thiết kế web", "17/6/2019",
                "Phòng máy", "7h30","90","G8.201", true));
        monHocs.add(new MonHoc("Công nghệ phần mềm", "19/6/2019",
                "Phòng máy", "7h30","90","G8.201", false));
        monHocs.add(new MonHoc("Trí tuệ nhân tạo", "21/6/2019",
                "Phòng máy", "7h30","90","G8.201", false));
    }

    public static MonHocManager getInstance(){
        if(manager == null)
            manager = new MonHocManager();
        return manager;
    }

    public ArrayList<MonHoc> getMonHocs(){
        return monHocs;
    }

    public void ModMonHoc(int position, MonHoc m){
        monHocs.set(position, m);
    }
}
