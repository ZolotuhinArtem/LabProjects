package com.example.artem.simplecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int OPERATION_NOONE = -1;
    private static final int OPERATION_ADD = 1;
    private static final int OPERATION_SUB = 2;
    private static final int OPERATION_MUL = 3;
    private static final int OPERATION_DIV = 4;


    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private Button b6;
    private Button b7;
    private Button b8;
    private Button b9;
    private Button b0;

    private Button btnAdd;
    private Button btnSub;
    private Button btnMult;
    private Button btnDiv;
    private Button btnDot;
    private Button btnClear;
    private Button btnDelete;
    private Button btnResult;

    private TextView tvResult;
    private TextView tvWork;



    private boolean isStart;
    private boolean isDot;

    private String result;
    private String work;

    private int lastOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isStart = true;
        isDot = false;
        result = "";
        work = "";
        lastOperation = -1;

        b1 = (Button) findViewById(R.id.btn_cal_1);
        b2 = (Button) findViewById(R.id.btn_cal_2);
        b3 = (Button) findViewById(R.id.btn_cal_3);
        b4 = (Button) findViewById(R.id.btn_cal_4);
        b5 = (Button) findViewById(R.id.btn_cal_5);
        b6 = (Button) findViewById(R.id.btn_cal_6);
        b7 = (Button) findViewById(R.id.btn_cal_7);
        b8 = (Button) findViewById(R.id.btn_cal_8);
        b9 = (Button) findViewById(R.id.btn_cal_9);
        b0 = (Button) findViewById(R.id.btn_cal_0);

        btnAdd = (Button) findViewById(R.id.btn_cal_add);
        btnSub = (Button) findViewById(R.id.btn_cal_sub);
        btnMult = (Button) findViewById(R.id.btn_cal_mult);
        btnDiv = (Button) findViewById(R.id.btn_cal_div);
        btnDot = (Button) findViewById(R.id.btn_cal_dot);
        btnClear = (Button) findViewById(R.id.btn_cal_clear);
        btnDelete = (Button) findViewById(R.id.btn_cal_delete);
        btnResult = (Button) findViewById(R.id.btn_cal_result);

        tvResult = (TextView) findViewById(R.id.tv_calc_result);
        tvWork = (TextView) findViewById(R.id.tv_calc_work);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        b0.setOnClickListener(this);

        btnAdd.setOnClickListener(this);
        btnMult.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnDot.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnResult.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        double a, b;
        switch(view.getId()){
            case R.id.btn_cal_0:
                if (!work.equals("0")) {
                    work = work + "0";
                }
                break;
            case R.id.btn_cal_1:
                work = work + "1";
                break;
            case R.id.btn_cal_2:
                work = work + "2";
                break;
            case R.id.btn_cal_3:
                work = work + "3";
                break;
            case R.id.btn_cal_4:
                work = work + "4";
                break;
            case R.id.btn_cal_5:
                work = work + "5";
                break;
            case R.id.btn_cal_6:
                work = work + "6";
                break;
            case R.id.btn_cal_7:
                work = work + "7";
                break;
            case R.id.btn_cal_8:
                work = work + "8";
                break;
            case R.id.btn_cal_9:
                work = work + "9";
                break;

            case R.id.btn_cal_dot:
                if (!work.contains(".")){
                    if (work.equals("")){
                        work = "0.";
                    }
                    else {
                        if (work.equals("-")){
                            work = "-0.";
                        }
                        else{
                            work = work + ".";
                        }

                    }
                }
                break;
            case R.id.btn_cal_add:
                if (!work.equals("")) {
                    doOperation(lastOperation);
                    work = "";
                    lastOperation = OPERATION_ADD;
                }
                break;
            case R.id.btn_cal_sub:
                if (!work.equals("")) {
                    if (!work.equals("-")) {
                        doOperation(lastOperation);
                        work = "";
                        lastOperation = OPERATION_SUB;
                    }
                    else{
                        work = "";
                    }
                }
                else{
                    work += "-";
                }
                break;
            case R.id.btn_cal_mult:
                if (!work.equals("")) {
                    doOperation(lastOperation);
                    work = "";
                    lastOperation = OPERATION_MUL;
                }
                break;
            case R.id.btn_cal_div:
                if (!work.equals("")) {
                    doOperation(lastOperation);
                    work = "";
                    lastOperation = OPERATION_DIV;
                }
                break;
            case R.id.btn_cal_clear:
                work = "";
                result = "";
                lastOperation = OPERATION_NOONE;
                break;
            case R.id.btn_cal_delete:
                if (!work.equals("")){
                    String tempWork;
                    tempWork = "";

                    for(int i = 0; i < work.length() - 1; i++){
                        tempWork += work.charAt(i);
                    }
                    work = tempWork;
                }
                break;
            case R.id.btn_cal_result:
                doOperation(lastOperation);
                work = result;
                result = "";
                lastOperation = OPERATION_NOONE;

                break;
        }
        tvResult.setText(result);
        tvWork.setText(work);
    }
    private String optimizeDoubleString(String s){
        if (!s.equals("")){
            if (s.charAt(s.length() - 1) == '.'){
                s += "0";
            }
            if (s.equals("-")){
                s = "0";
            }
            if (s.charAt(0) == '.'){
                s = "0" + s;
            }
        }
        else{
            s = "0";
        }

        return s;
    }
    private void doOperation(int operation){
        double a, b;
        switch (operation){
            case OPERATION_NOONE:
                result = work;
                work = "";
                break;
            case OPERATION_ADD:
                a = Double.parseDouble(optimizeDoubleString(result));
                b = Double.parseDouble(optimizeDoubleString(work));
                result = Double.toString(a + b);
                work = "";
                break;
            case OPERATION_SUB:
                a = Double.parseDouble(optimizeDoubleString(result));
                b = Double.parseDouble(optimizeDoubleString(work));
                result = Double.toString(a - b);
                work = "";
                break;
            case OPERATION_DIV:
                a = Double.parseDouble(optimizeDoubleString(result));
                b = Double.parseDouble(optimizeDoubleString(work));
                if (b != 0) {
                    result = Double.toString(a / b);
                    work = "";
                }
                else{
                    result = "";
                    work = "";
                    lastOperation = OPERATION_NOONE;
                    Toast.makeText(MainActivity.this, "Error! Division by zero!", Toast.LENGTH_LONG).show();
                }
                break;
            case OPERATION_MUL:
                a = Double.parseDouble(optimizeDoubleString(result));
                b = Double.parseDouble(optimizeDoubleString(work));
                result = Double.toString(a * b);
                work = "";
                break;

        }
    }
}

