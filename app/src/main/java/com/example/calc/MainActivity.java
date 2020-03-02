package com.example.calc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final int MENU_RESET_ID = 1;
    final int MENU_QUIT_ID = 2;

    EditText et1,et2;
    Button plus, minus, multiply, devide;
    TextView tvResult;

    String oper = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);

        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);
        multiply = (Button) findViewById(R.id.multiply);
        devide = (Button) findViewById(R.id.devide);

        tvResult = (TextView) findViewById(R.id.tvResult);

        plus.setOnClickListener(this);
        minus.setOnClickListener(this);
        multiply.setOnClickListener(this);
        devide.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {

        float num1 = 0;
        float num2 = 0;
        float result = 0;

        if (TextUtils.isEmpty(et1.getText().toString())
                ||TextUtils.isEmpty(et2.getText().toString())) {
            return;
        }

        num1 = Float.parseFloat(et1.getText().toString());
        num2 = Float.parseFloat(et2.getText().toString());

        switch (v.getId()) {

            case R.id.plus:
                oper = "+";
                result = num1 + num2;
                break;
            case R.id.minus:
                oper = "-";
                result = num1 - num2;
                break;
            case R.id.multiply:
                oper = "*";
                result = num1 * num2;
                break;
            case R.id.devide:
                oper = "/";
                if (num2 == 0)
                    Toast.makeText(MainActivity.this, "Делить на 0 нельзя",Toast.LENGTH_SHORT).show();
                else   result = num1 / num2;
                break;
            default:
                break;
        }


        if (num2 == 0 && oper == "/")
            tvResult.setText(num1 + " " + oper +" " + num2 + " = " + "ERROR!         Делить на 0 нельзя!");
        else
            tvResult.setText(num1 + " " + oper +" " + num2 + " = " + result);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0,MENU_RESET_ID,0,"Reset");
        menu.add(0,MENU_QUIT_ID,0,"Quit");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case MENU_RESET_ID:
                et1.setText("");
                et2.setText("");
                tvResult.setText("");
                break;
            case MENU_QUIT_ID:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}

