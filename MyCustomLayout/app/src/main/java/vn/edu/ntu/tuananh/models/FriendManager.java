package vn.edu.ntu.tuananh.models;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class FriendManager {
    ArrayList<Friend> friends;
    private static FriendManager manager;
    private FriendManager(){
        friends = new ArrayList<>();
        friends.add(new Friend("01","Ngọc Sơn","1/1/1998","013465","Dak Lak"));
        friends.add(new Friend("02","Tuấn Đạt","2/1/1998","013365","Ninh Thuận"));
        friends.add(new Friend("03","Việt Minh","3/1/1998","165161","Ninh Thuận"));
        friends.add(new Friend("04","Thu Hà","4/1/1998","195615","Quảng Ngãi"));
        friends.add(new Friend("05","Minh Chiến","5/1/1998","985151","Cam Lâm"));
        friends.add(new Friend("06","Tiến Đạt","6/1/1998","164861","Phú Yên"));
    }
    public static FriendManager getInstance(){
        if(manager == null)
            manager = new FriendManager();
        return manager;
    }
    public ArrayList<Friend> getFriend(){
        return friends;
    }
}
