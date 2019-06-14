package vn.edu.vn.ttngocson.compoundbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox CB1,CB2,CB3,CB4,CB5;
    Button bShow;
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
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    private void addControls(){
        CB1 = findViewById(R.id.cb1);
        CB2 = findViewById(R.id.cb2);
        CB3 = findViewById(R.id.cb3);
        CB4 = findViewById(R.id.cb4);
        CB5 = findViewById(R.id.cb5);
        bShow =findViewById(R.id.btnShow);
    }

    private void addEvents() {
        bShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuly();
            }
        });
    }
    private void xuly(){
        String temp = "Toppings: ";
        if(CB1.isChecked())
            temp = temp + CB1.getText() +", ";
        if(CB2.isChecked())
            temp = temp + CB2.getText() +", ";
        if(CB3.isChecked())
            temp = temp + CB3.getText() +", ";
        if(CB4.isChecked())
            temp = temp + CB4.getText() +", ";
        if(CB5.isChecked())
            temp = temp + CB5.getText() +", ";
        Toast.makeText(this,temp, Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
}
