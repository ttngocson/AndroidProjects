package ntu.edu.vn.ttngocson.ui_helper;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.GridLayoutManager;

public class UiHelper {
    public static GridLayoutManager setupLayoutManager(Context context){
        GridLayoutManager layoutManager = null;
        int orientation =  context.getResources().getConfiguration().orientation;
        int col = 1;
        if(orientation == Configuration.ORIENTATION_LANDSCAPE)
            col = 2;
        layoutManager = new GridLayoutManager(context,col);
        return layoutManager;
    }
}
