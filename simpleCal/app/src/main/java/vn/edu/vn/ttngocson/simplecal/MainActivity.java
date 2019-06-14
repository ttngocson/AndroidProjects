package vn.edu.vn.ttngocson.simplecal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button BTN1, BTN2, BTN3, BTN4, BTN5, BTN6, BTN7, BTN8, BTN9, BTN0;
    Button BTNCong, BTNTru, BTNNhan, BTNChia, BTNBang, BTNCE, BTNC, BTNBSP, BTNCongTru, BTNPhay;
    TextView dongTren, dongDuoi;

    String dlTren = "", dlDuoi = "";
    private float process = 0;
    float[] temp = new float[2];
    int operator = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
        printDongTren();
        printDongDuoi();
        bang();
        temp[0]=0;
    }
    private void printDongTren(){
        dongTren.setText(dlTren);
    }
    private void printDongDuoi(){
        dongDuoi.setText(dlDuoi);
    }
    private void bang(){
        if(operator==1){
            process = temp[0] + temp[1];
            temp[0] = process;
            dongDuoi.setText(String.valueOf(process));
        }
        else if(operator==2){
            process = temp[0] - temp[1];
            temp[0] = process;
            dongDuoi.setText(String.valueOf(process));
        }
        else if(operator==3){
            process = temp[0] * temp[1];
            temp[0] = process;
            dongDuoi.setText(String.valueOf(process));
        }
        else if(operator==4){
            process = temp[0] / temp[1];
            if(temp[1]==0){
                dlTren="";
                dlDuoi="";
                temp[0]=0;
                temp[1]=0;
                dongDuoi.setText("Loi chia cho 0");
            }
            else{
                temp[0] = process;
                dongDuoi.setText(String.valueOf(process));
            }
        }
    }

    private void addControls(){
        BTN0 = findViewById(R.id.btn0);
        BTN1 = findViewById(R.id.btn1);
        BTN2 = findViewById(R.id.btn2);
        BTN3 = findViewById(R.id.btn3);
        BTN4 = findViewById(R.id.btn4);
        BTN5 = findViewById(R.id.btn5);
        BTN6 = findViewById(R.id.btn6);
        BTN7 = findViewById(R.id.btn7);
        BTN8 = findViewById(R.id.btn8);
        BTN9 = findViewById(R.id.btn9);
        BTNCong = findViewById(R.id.btnCong);
        BTNTru = findViewById(R.id.btnTru);
        BTNNhan = findViewById(R.id.btnNhan);
        BTNChia = findViewById(R.id.btnChia);
        BTNBang = findViewById(R.id.btnBang);
        BTNCE = findViewById(R.id.btnCE);
        BTNC = findViewById(R.id.btnC);
        BTNBSP = findViewById(R.id.btnBSP);
        BTNCongTru = findViewById(R.id.btnCongTru);
        BTNPhay = findViewById(R.id.btnPhay);
        dongTren = findViewById(R.id.dongTren);
        dongDuoi = findViewById(R.id.dongDuoi);
    }

    private void addEvents(){
        BTN0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlTren = dlTren + BTN0.getText();
                dlDuoi = dlDuoi + BTN0.getText();
                printDongDuoi();
            }
        });
        BTN1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlTren = dlTren + BTN1.getText();
                dlDuoi = dlDuoi + BTN1.getText();
                printDongDuoi();
            }
        });
        BTN2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlTren = dlTren + BTN2.getText();
                dlDuoi = dlDuoi + BTN2.getText();
                printDongDuoi();
            }
        });
        BTN3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlTren = dlTren + BTN3.getText();
                dlDuoi = dlDuoi + BTN3.getText();
                printDongDuoi();
            }
        });
        BTN4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlTren = dlTren + BTN4.getText();
                dlDuoi = dlDuoi + BTN4.getText();
                printDongDuoi();
            }
        });
        BTN5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlTren = dlTren + BTN5.getText();
                dlDuoi = dlDuoi + BTN5.getText();
                printDongDuoi();
            }
        });
        BTN6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlTren = dlTren + BTN6.getText();
                dlDuoi = dlDuoi + BTN6.getText();
                printDongDuoi();
            }
        });
        BTN7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlTren = dlTren + BTN7.getText();
                dlDuoi = dlDuoi + BTN7.getText();
                printDongDuoi();
            }
        });
        BTN8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlTren = dlTren + BTN8.getText();
                dlDuoi = dlDuoi + BTN8.getText();
                printDongDuoi();
            }
        });
        BTN9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlTren = dlTren + BTN9.getText();
                dlDuoi = dlDuoi + BTN9.getText();
                printDongDuoi();
            }
        });
        BTNCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dlDuoi=="")
                    dlDuoi="0";
                if(operator==0){
                    operator = 1;
                }
                temp[1] = Float.valueOf(dlDuoi);
                bang();
                operator = 1;
                dlTren = dlTren + " " +BTNCong.getText() + " ";
                printDongTren();
                dlDuoi="";
            }
        });
        BTNTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dlDuoi=="")
                    dlDuoi="0";
                if(operator==0){
                    operator = 1;
                }
                temp[1] = Float.valueOf(dlDuoi);
                bang();
                operator = 2;
                dlTren = dlTren + " " +BTNTru.getText() + " ";
                printDongTren();
                dlDuoi="";
            }
        });
        BTNNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dlDuoi=="")
                    dlDuoi="0";
                if(operator==0){
                    operator = 1;
                }
                temp[1] = Float.valueOf(dlDuoi);
                bang();
                operator = 3;
                dlTren = dlTren + " " +BTNNhan.getText() + " ";
                printDongTren();
                dlDuoi="";
            }
        });
        BTNChia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dlDuoi=="")
                    dlDuoi="0";
                if(operator==0){
                    operator = 1;
                }
                temp[1] = Float.valueOf(dlDuoi);
                bang();
                operator = 4;
                dlTren = dlTren + " " +BTNChia.getText() + " ";
                printDongTren();
                dlDuoi="";
            }
        });
        BTNBang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dlDuoi=="")
                    dlDuoi="0";
                temp[1] = Float.valueOf(dlDuoi);
                bang();
                dlDuoi="";
                dlTren="";
                temp[1]=0;
                printDongTren();
                operator=0;
            }
        });
        BTNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlTren="";
                dlDuoi="";
                temp[0]=0;
                temp[1]=0;
                process=0;
                operator=1;
                printDongTren();
                printDongDuoi();
            }
        });

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
