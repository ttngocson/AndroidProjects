package ntu.edu.vn.ttngocson.kt2.models;

import java.util.ArrayList;


public class MonHocManager {
    ArrayList<MonHoc> monHocs;
    private static MonHocManager manager;

    private MonHocManager(){

        monHocs = new ArrayList<>();
        monHocs.add(new MonHoc("Lập trình TBDD",3,8f,7f));
        monHocs.add(new MonHoc("Thiết kế WEB",3, 8.5f,7.5f));
        monHocs.add(new MonHoc("Cơ sở dữ liệu",3,8f,7f));
        monHocs.add(new MonHoc("Kỹ thuật lập trình",3,7f,6f));
    }

    public static MonHocManager getInstance(){
        if(manager == null)
            manager = new MonHocManager();
        return manager;
    }

    public ArrayList<MonHoc> getMonHocs(){
        return monHocs;
    }

    public void AddMonHoc(MonHoc m){
        monHocs.add(m);
    }

    public void ModMonHoc(int position, MonHoc m){
        monHocs.add(position, m);
    }
}
