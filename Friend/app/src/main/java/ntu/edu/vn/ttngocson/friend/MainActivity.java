package ntu.edu.vn.ttngocson.friend;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import ntu.edu.vn.ttngocson.friend.models.Friend;
import ntu.edu.vn.ttngocson.friend.models.FriendManager;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView lvFriends;
    ArrayAdapter<Friend> adapter;
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

    private void addEvents(){
        lvFriends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                XuLyClick_LVItem(position);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XyLyMoManHinh2();
            }
        });
    }

    private void XyLyMoManHinh2() {
        Intent intent = new Intent(this, FriendDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("Task", "Add");
        intent.putExtras(bundle);
        startActivityForResult(intent, 1);
    }

    private void XuLyClick_LVItem(int position) {
        int size = FriendManager.getInstance().getFriend().size();
        if (position >= 0 && position <size){
            String str = FriendManager.getInstance().getFriend().get(position).toString();
            Toast.makeText(this, str, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, FriendDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("Task", "Mod");
            bundle.putInt("ID", position);
            intent.putExtras(bundle);
            startActivityForResult(intent,1);
        }
    }

    private void addView(){
        fab = findViewById(R.id.fab);
        lvFriends = findViewById(R.id.lvFriend);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                FriendManager.getInstance().getFriend());
        lvFriends.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if( requestCode==1){
            if(resultCode==RESULT_OK)
                adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
