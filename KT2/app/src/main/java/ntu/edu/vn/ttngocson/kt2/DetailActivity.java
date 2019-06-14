package ntu.edu.vn.ttngocson.kt2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import ntu.edu.vn.ttngocson.kt2.models.MonHoc;
import ntu.edu.vn.ttngocson.kt2.models.MonHocManager;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton btnBack;
    TextView textView10;
    Button btnSave, btnCancel;
    EditText editTenMH, editSoTC, editDiemKT, editDiemT, editDiemTB;

    int position;
    String task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        task = bundle.getString("Task");
        AddControls();
        AddEvents();
        if(task.equals("Mod")){
            position = bundle.getInt("Position",0);
            AddView();
        }
        if(task.equals("Add")){
            textView10.setVisibility(View.INVISIBLE);
            editDiemTB.setVisibility(View.INVISIBLE);
        }
    }

    private void AddView() {
        editTenMH.setText(MonHocManager.getInstance().getMonHocs().get(position).getTenMH());
        editSoTC.setText(MonHocManager.getInstance().getMonHocs().get(position).getSoTC());
        editDiemKT.setText(String.valueOf(MonHocManager.getInstance().getMonHocs().get(position).getDiemKT()));
        editDiemT.setText(String.valueOf(MonHocManager.getInstance().getMonHocs().get(position).getDiemT()));
        float diemTB = (MonHocManager.getInstance().getMonHocs().get(position).getDiemKT() +
                        MonHocManager.getInstance().getMonHocs().get(position).getDiemT())/2;
        editDiemTB.setText(String.valueOf(diemTB));
        editDiemTB.setEnabled(false);
    }

    private void AddEvents() {
        btnCancel.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        //btnBack.setOnClickListener(this);
    }

    private void AddControls() {
        textView10 = findViewById(R.id.textView10);

        //btnBack = findViewById(R.id.btnBack);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        editTenMH = findViewById(R.id.editTenMH);
        editSoTC = findViewById(R.id.editSoTC);
        editDiemKT = findViewById(R.id.editDiemKT);
        editDiemT = findViewById(R.id.editDiemT);
        editDiemTB = findViewById(R.id.editDiemTB);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnCancel){
            setResult(RESULT_CANCELED);
            finish();
        }
        if(v.getId() == R.id.btnSave) {
            MonHoc m = new MonHoc(editTenMH.getText().toString(),
                                Integer.valueOf(editSoTC.getText().toString()),
                                Float.valueOf(editDiemKT.getText().toString()),
                                Float.valueOf(editDiemT.getText().toString()));
            if(task.equals("Mod")){
                MonHocManager.getInstance().ModMonHoc(position, m);
            }
            if(task.equals("Add")){
                MonHocManager.getInstance().AddMonHoc(m);
            }
            setResult(RESULT_OK);
            finish();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
            //finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
