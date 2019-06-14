package ntu.edu.vn.ttngocson.thi_58133435;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import ntu.edu.vn.ttngocson.thi_58133435.fragments.ListKhachHangFragment;

public class MainActivity extends AppCompatActivity {


    FragmentManager manager = getSupportFragmentManager();
    Fragment fragment = manager.findFragmentById(R.id.fragment_container);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        onAttachFragment();
    }

    private void onAttachFragment() {
        if (fragment == null){
            fragment = new ListKhachHangFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("Task", 1);
            fragment.setArguments(bundle);
            manager.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.TCKH) {
            Bundle bundle = new Bundle();
            bundle.putInt("Task", 1);
            fragment.setArguments(bundle);
            fragment.getFragmentManager().beginTransaction().detach(fragment).commit();
            fragment.getFragmentManager().beginTransaction().attach(fragment).commit();
            return true;
        }
        if (id == R.id.KHTT) {
            Bundle bundle = new Bundle();
            bundle.putInt("Task", 0);
            fragment.setArguments(bundle);
            fragment.getFragmentManager().beginTransaction().detach(fragment).commit();
            fragment.getFragmentManager().beginTransaction().attach(fragment).commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
