package ntu.edu.vn.ttngocson.intenttuongminh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    EditText ESoA, ESoB;
    Button BTNCal, BTNCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        addView();
        addEvents();
    }

    private void addEvents() {
        BTNCancel.setOnClickListener(this);
        BTNCal.setOnClickListener(this);
    }

    private void addView() {
        ESoA = findViewById(R.id.editA);
        ESoB = findViewById(R.id.editB);
        BTNCal = findViewById(R.id.btnCal);
        BTNCancel = findViewById(R.id.btnCancel);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnCal)
            XuLyTinh();
        else if (v.getId() == R.id.btnCancel)
                XuLyHuy();
    }

    private void XuLyHuy() {
        setResult(RESULT_CANCELED);
        finish();
    }

    private void XuLyTinh() {
        int a = Integer.parseInt(ESoA.getText().toString());
        int b = Integer.parseInt(ESoB.getText().toString());
        int kq = a + b;
        Intent data = new Intent();
        data.putExtra("soA", a);
        data.putExtra("soB", b);
        data.putExtra("kq", kq);
        setResult(RESULT_OK, data);
        finish();
    }
}
