package ntu.edu.vn.ttngocson.bmi;

import androidx.appcompat.app.AppCompatActivity;
import ntu.edu.vn.ttngocson.bmi.models.BMIManager;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    Locale myLocale;
    EditText weight, height;
    Button btnCal;
    TextView viewBMI, bmi_ctg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }
    private void onChangeLanguage(Locale locale){
        DisplayMetrics displayMetrics = getBaseContext().getResources().getDisplayMetrics();
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(configuration,displayMetrics);
        Intent refresh = new Intent(MainActivity.this, MainActivity.class);
        startActivity(refresh);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.EN:
                myLocale = new Locale("en","US");
                break;
            case R.id.VN:
                myLocale = new Locale("vi","VN");
                break;
        }
        onChangeLanguage(myLocale);
        return super.onOptionsItemSelected(item);
    }

    private void addControls(){
        weight = findViewById(R.id.inputWeight);
        height = findViewById(R.id.inputHeight);
        btnCal = findViewById(R.id.btnCal);
        viewBMI = findViewById(R.id.viewBMI);
        bmi_ctg = findViewById(R.id.bmi_ctg);

    }
    private void addEvents(){
        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tinh_BMI();
            }
        });
    }
    private void tinh_BMI(){
        String strWeight = weight.getText().toString();
        String strHeight = height.getText().toString();
        if(strWeight.length()> 0 && strHeight.length()>0) {
            float w = Float.parseFloat(strWeight);
            float h = Float.parseFloat(strHeight);
            float bmi = w / (h * h);
            BMIManager bmiManager = BMIManager.getInstance(getApplicationContext());
            viewBMI.setText(Float.toString(bmi));
            int resBMI = bmiManager.getBMI(bmi);
            if (resBMI != -1)
                bmi_ctg.setText(resBMI);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
