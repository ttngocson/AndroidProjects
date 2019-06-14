package ntu.edu.vn.ttngocson.friend;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import ntu.edu.vn.ttngocson.friend.models.Friend;
import ntu.edu.vn.ttngocson.friend.models.FriendManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FriendDetailActivity extends AppCompatActivity implements View.OnClickListener {
    Button BTNSave, BTNRemove, BTNCancel;
    EditText eID, eName, eBD, ePhone, eAddress;
    int position;
    String Task="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_detail);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Task = bundle.getString("Task");
        addControls();
        addEvents();
        if(Task.equals("Mod")) {
            position = bundle.getInt("ID", 0);
            addView();
        }
        else
            BTNRemove.setVisibility(View.INVISIBLE);
    }

    public void addControls(){
        BTNSave = findViewById(R.id.btnSave);
        BTNRemove = findViewById(R.id.btnRemove);
        BTNCancel = findViewById(R.id.btnCancel);
        eID = findViewById(R.id.editEID);
        eName = findViewById(R.id.editEName);
        eBD = findViewById(R.id.editEBD);
        ePhone = findViewById(R.id.editEPhone);
        eAddress = findViewById(R.id.editEAddress);
    }
    public void addEvents(){
        BTNSave.setOnClickListener(this);
        BTNRemove.setOnClickListener(this);
        BTNCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            if(v.getId()==R.id.btnSave)
                FSave();
            else if (v.getId()==R.id.btnRemove)
                FRemove();
            else if (v.getId()==R.id.btnCancel)
                FCancel();
    }

    private void addView(){
        eID.setText(FriendManager.getInstance().getFriend().get(position).getId());
        eID.setEnabled(false);
        eName.setText(FriendManager.getInstance().getFriend().get(position).getName());
        eBD.setText(FriendManager.getInstance().getFriend().get(position).getBirthday());
        ePhone.setText(FriendManager.getInstance().getFriend().get(position).getPhone());
        eAddress.setText(FriendManager.getInstance().getFriend().get(position).getAddress());
    }
    private void FSave(){
        if(Task.equals("Mod"))
            FriendManager.getInstance().ModFriend(position,
                    eID.getText().toString(),
                    eName.getText().toString(),
                    eBD.getText().toString(),
                    ePhone.getText().toString(),
                    eAddress.getText().toString());
        if(Task.equals("Add"))
            FriendManager.getInstance().AddFriend(eID.getText().toString(),
                    eName.getText().toString(),
                    eBD.getText().toString(),
                    ePhone.getText().toString(),
                    eAddress.getText().toString());
        setResult(RESULT_OK);
        finish();
    }
    private void FRemove(){
        FriendManager.getInstance().RemoveFriend(position);
        setResult(RESULT_OK);
        finish();
    }
    private void FCancel(){
        setResult(RESULT_CANCELED);
        finish();
    }
}
