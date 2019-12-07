package ttngocson.ntu.edu.vn.thi12;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import ttngocson.ntu.edu.vn.thi12.Models.KhachHang;
import ttngocson.ntu.edu.vn.thi12.Models.KhachHangManager;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    EditText editName, editSDT, editDiaChi;
    CheckBox cbKHTT;
    Button btnAdd, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        AddControls();
        AddEvents();
    }

    private void AddEvents() {
        btnAdd.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    private void AddControls() {
        editName = findViewById(R.id.editName);
        editSDT = findViewById(R.id.editSDT);
        editDiaChi = findViewById(R.id.editDiaChi);

        cbKHTT = findViewById(R.id.cbKHTT);

        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btnCancel.getId()){
            setResult(RESULT_OK);
            finish();
        }
        if(v.getId() == btnAdd.getId()){
            String name, sdt, address;
            boolean khtt;
            name = editName.getText().toString();
            sdt = editSDT.getText().toString();
            address = editDiaChi.getText().toString();
            khtt = cbKHTT.isChecked();
            KhachHang kh = new KhachHang(name, sdt, address, khtt);
            KhachHangManager.getInstance().AddKhachHang(kh);
            String text = "Họ và tên: " + name +
                          "\nSố điện thoại: " + sdt +
                          "\nĐịa chỉ: " +
                          "\nKHTT: " + khtt;
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }
    }
}
