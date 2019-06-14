package ntu.edu.vn.ttngocson.playmusic;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] permission = {"android.permission.READ_EXTERNAL_STORAGE"};
        if(checkPermissionEnable(Manifest.permission.READ_EXTERNAL_STORAGE))
            setupAdapter();
        else
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 113);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 113)
            if(grantResults.length > 0)
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    setupAdapter();
    }

    private boolean checkPermissionEnable(String permission){
        int res = ContextCompat.checkSelfPermission(this, permission);
        if (res == PackageManager.PERMISSION_GRANTED)
            return true;
        return false;
    }

    private void setupAdapter() {
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragment_container);
        if (fragment == null){
            fragment = new ListAudioFragment();
            manager.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }
}
