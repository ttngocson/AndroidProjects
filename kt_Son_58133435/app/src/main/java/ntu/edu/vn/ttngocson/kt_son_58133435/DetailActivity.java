package ntu.edu.vn.ttngocson.kt_son_58133435;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ntu.edu.vn.ttngocson.kt_son_58133435.models.MonHoc;
import ntu.edu.vn.ttngocson.kt_son_58133435.models.MonHocManager;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{
    EditText editMonThi, editNgayThi, editHinhThucThi, editTimeBD, editTimeLB, editPhongThi;
    Button btnSave, btnCancal;

    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        position = bundle.getInt("Position",0);

        AddControls();

        AddView();
    }

    private void AddView() {
        editMonThi.setText(MonHocManager.getInstance().getMonHocs().get(position).getMonThi());
        editNgayThi.setText(MonHocManager.getInstance().getMonHocs().get(position).getNgayThi());
        editHinhThucThi.setText(MonHocManager.getInstance().getMonHocs().get(position).getHinhThucThi());
        editTimeBD.setText(MonHocManager.getInstance().getMonHocs().get(position).getTimeBD());
        editTimeLB.setText(MonHocManager.getInstance().getMonHocs().get(position).getTimeLB());
        editPhongThi.setText(MonHocManager.getInstance().getMonHocs().get(position).getPhongThi());
    }

    private void AddControls() {
        editMonThi = findViewById(R.id.editMonThi);
        editNgayThi = findViewById(R.id.editNgayThi);
        editHinhThucThi = findViewById(R.id.editHinhThucThi);
        editTimeBD = findViewById(R.id.editTimeBD);
        editTimeLB = findViewById(R.id.editTimeLB);
        editPhongThi = findViewById(R.id.editPhongThi);

        btnSave = findViewById(R.id.btnSave);
        btnCancal = findViewById(R.id.btnCancel);

        btnSave.setOnClickListener(this);
        btnCancal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnSave){
            MonHoc m = new MonHoc(editMonThi.getText().toString(),
                    editNgayThi.getText().toString(),
                    editHinhThucThi.getText().toString(),
                    editTimeBD.getText().toString(),
                    editTimeLB.getText().toString(),
                    editPhongThi.getText().toString(),
                    MonHocManager.getInstance().getMonHocs().get(position).isThi());
            MonHocManager.getInstance().ModMonHoc(position, m);
            setResult(RESULT_OK);
            finish();
        }
        if(v.getId() == R.id.btnCancel){
            setResult(RESULT_CANCELED);
            finish();
        }
    }
}
