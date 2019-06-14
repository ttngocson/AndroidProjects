package vn.edu.vn.trantruongngocson.androidlifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    EditText txtEMyText;
    Button btnFinish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart", Toast.LENGTH_LONG)
                .show();
    }
    @Override
    protected void onResume(){
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_LONG)
                .show();
        SharedPreferences myFile = getSharedPreferences("myFile1",
                Activity.MODE_PRIVATE);
        if ( (myFile != null) && (myFile.contains("mydata")) ) {
            String temp = myFile.getString("mydata", "***");
            txtEMyText.setText(temp);
        }
    }

    private void addControls() {
        txtEMyText = findViewById(R.id.txtEText);
        btnFinish = findViewById(R.id.btnFinish);
    }
    private void addEvents() {
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulynutbam();
            }
        });
    }
    private void xulynutbam(){
        Toast.makeText(this, txtEMyText.getText().toString(), Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause", Toast.LENGTH_LONG)
                .show();
        SharedPreferences myFile1 = getSharedPreferences("myFile1",
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = myFile1.edit();
        String temp = txtEMyText.getText().toString();
        myEditor.putString("mydata", temp);
        myEditor.commit();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop", Toast.LENGTH_LONG)
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy", Toast.LENGTH_LONG)
                .show();
    }
}
