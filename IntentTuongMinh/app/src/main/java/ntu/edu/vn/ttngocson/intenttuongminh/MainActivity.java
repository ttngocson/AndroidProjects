package ntu.edu.vn.ttngocson.intenttuongminh;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvRes;
    ArrayList<String> listResult;
    ArrayAdapter<String> adapter;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addView();
        addEvents();
    }

    private void updateUI(){
        listResult = new ArrayList<>();
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listResult);
        lvRes.setAdapter(adapter);
    }
    private void addView(){
        fab = findViewById(R.id.fab);
        lvRes = findViewById(R.id.lvResult);
        updateUI();
    }
    private void addEvents(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XuLyMoActivity();
            }
        });
    }

    private void XuLyMoActivity() {
        Intent intent = new Intent(this,Main2Activity.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
            if(resultCode == RESULT_OK){
                int a  = data.getIntExtra("soA",-1);
                int b  = data.getIntExtra("soB",-1);
                int kq  = data.getIntExtra("kq",-1);
                String strKq = "";
                strKq = new Integer(a).toString() + " + ";
                strKq = strKq + new Integer(b).toString() + " = ";
                strKq = strKq + new  Integer(kq).toString();
                listResult.add(strKq);
                adapter.notifyDataSetChanged();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
