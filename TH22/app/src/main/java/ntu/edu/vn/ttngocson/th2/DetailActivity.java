package ntu.edu.vn.ttngocson.th2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ntu.edu.vn.ttngocson.th2.models.Friend;
import ntu.edu.vn.ttngocson.th2.models.FriendManager;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editID, editName, editBD, editPhone, editAdd;
    Button btnSave, btnRemove, btnCancel;
    int position;
    String task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        task = bundle.getString("Task");
        AddControls();
        AddEvents();
        if(task.equals("Mod")){
            position = bundle.getInt("Position",0);
            AddView();
        }
        else{
            btnRemove.setVisibility(View.INVISIBLE);
        }
    }

    private void AddView() {
        editID.setText(FriendManager.getInstance().getFriend().get(position).getId());
        editID.setEnabled(false);
        editName.setText(FriendManager.getInstance().getFriend().get(position).getName());
        editBD.setText(FriendManager.getInstance().getFriend().get(position).getBirthday());
        editPhone.setText(FriendManager.getInstance().getFriend().get(position).getPhone());
        editAdd.setText(FriendManager.getInstance().getFriend().get(position).getAddress());
    }

    public void AddControls() {
        editID = findViewById(R.id.editID);
        editName = findViewById(R.id.editName);
        editBD = findViewById(R.id.editBD);
        editPhone = findViewById(R.id.editPhone);
        editAdd = findViewById(R.id.editAdd);
        btnSave = findViewById(R.id.btnSave);
        btnRemove = findViewById(R.id.btnRemove);
        btnCancel = findViewById(R.id.btnCancel);
    }

    public void AddEvents() {
        btnSave.setOnClickListener(this);
        btnRemove.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnSave)
            FSave();
        if(v.getId() == R.id.btnRemove)
            FRemove();
        if(v.getId() == R.id.btnCancel)
            FCancel();
    }

    private void FCancel() {
        setResult(RESULT_CANCELED);
        finish();
    }

    private void FRemove() {
        FriendManager.getInstance().RemoveFriend(position);
        setResult(RESULT_OK);
        finish();
    }

    private void FSave() {
        Friend friend = new Friend(editID.getText().toString(),
                                   editName.getText().toString(),
                                   editBD.getText().toString(),
                                   editPhone.getText().toString(),
                                   editAdd.getText().toString());
        if(task.equals("Mod")) {
            FriendManager.getInstance().ModFriend(position, friend);
        }
        if(task.equals("Add"))
            FriendManager.getInstance().AddFriend(friend);
        setResult(RESULT_OK);
        finish();
    }
}
