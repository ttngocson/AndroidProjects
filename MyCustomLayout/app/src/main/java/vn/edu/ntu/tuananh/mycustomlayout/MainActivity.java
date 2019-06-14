package vn.edu.ntu.tuananh.mycustomlayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vn.edu.ntu.tuananh.fragment.LisfriendFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onAttachFragment();
    }

    private void onAttachFragment()
    {
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragment_container);
        if (fragment == null)
        {
            fragment = new LisfriendFragment();
            manager.beginTransaction().add(R.id.fragment_container,fragment).commit();
        }
    }
}
