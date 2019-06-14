package ntu.edu.vn.ttngocson.thi_58133435;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import ntu.edu.vn.ttngocson.thi_58133435.models.KhachHang;
import ntu.edu.vn.ttngocson.thi_58133435.models.KhachHangManager;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTenKH, editSDT, editDiaChi;
    CheckBox cb;
    Button btnSave, btnCancel;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        position = bundle.getInt("Position",0);

        AddControls();
        AddViews();
    }

    private void AddControls() {
        editTenKH = findViewById(R.id.editTenKH);
        editSDT = findViewById(R.id.editSDT);
        editDiaChi = findViewById(R.id.editDiaChi);

        cb = findViewById(R.id.cbKHTT);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    private void AddViews() {
        editTenKH.setText(KhachHangManager.getInstance().getKhachHangs().get(position).getTenKH());
        editSDT.setText(KhachHangManager.getInstance().getKhachHangs().get(position).getSoDT());
        editDiaChi.setText(KhachHangManager.getInstance().getKhachHangs().get(position).getDiaChi());
        boolean check = KhachHangManager.getInstance().getKhachHangs().get(position).isKHTT();
        cb.setChecked(check);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnCancel){
            setResult(RESULT_CANCELED);
            finish();
        }
        if(v.getId() == R.id.btnSave){
            boolean temp = false;
            if (cb.isChecked())
                temp = true;

            KhachHang kh = new KhachHang(editTenKH.getText().toString(),
                    editSDT.getText().toString(),
                    editDiaChi.getText().toString(),
                    temp);
            KhachHangManager.getInstance().ModKhachHang(position, kh);

            setResult(RESULT_OK);
            finish();
        }


    }
}
