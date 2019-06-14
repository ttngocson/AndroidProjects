package ntu.edu.vn.ttngocson.monthi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ntu.edu.vn.ttngocson.monthi.models.MonThi;
import ntu.edu.vn.ttngocson.monthi.models.MonThiManager;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnSave, btnCancel;
    EditText editMonThi, editNgayThi, editHTThi, editTimeBD, editTimeLB, editPhongThi;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        position = bundle.getInt("Position", 0);

        AddControls();
        AddView();
        AddEvents();
    }

    private void AddEvents() {
        btnCancel.setOnClickListener(this);
        btnSave.setOnClickListener(this);
    }

    private void AddView() {
        editMonThi.setText(MonThiManager.getInstance().getList().get(position).getMonThi().toString());
        editNgayThi.setText(MonThiManager.getInstance().getList().get(position).getNgayThi().toString());
        editHTThi.setText(MonThiManager.getInstance().getList().get(position).getHTThi().toString());
        editTimeBD.setText(MonThiManager.getInstance().getList().get(position).getTimBD().toString());
        editTimeLB.setText(MonThiManager.getInstance().getList().get(position).getTimeLB().toString());
        editPhongThi.setText(MonThiManager.getInstance().getList().get(position).getPhongThi().toString());
    }

    private void AddControls() {
        editMonThi = findViewById(R.id.editMonThi);
        editNgayThi = findViewById(R.id.editNgayThi);
        editHTThi = findViewById(R.id.editHTThi);
        editTimeBD = findViewById(R.id.editTimBD);
        editTimeLB = findViewById(R.id.editTimeLB);
        editPhongThi = findViewById(R.id.editPhongThi);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnCancel){
            setResult(RESULT_CANCELED);
            finish();
        }
        if(v.getId() == R.id.btnSave){
            MonThi mt = new MonThi(
                    editMonThi.getText().toString(),
                    editNgayThi.getText().toString(),
                    editHTThi.getText().toString(),
                    editTimeBD.getText().toString(),
                    editTimeLB.getText().toString(),
                    editPhongThi.getText().toString()
            );
            MonThiManager.getInstance().ModMonThi(position, mt);
            setResult(RESULT_OK);
            finish();
        }
    }
}
