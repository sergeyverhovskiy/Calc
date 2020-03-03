package com.example.calc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.calc.calculator.Calculator;
import com.example.calc.calculator.CalculatorImpl;
import com.example.calc.calculator.Operator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static int MENU_RESET_ID = 1;
    private final static int MENU_QUIT_ID = 2;

    private final Calculator calculator = new CalculatorImpl();

    private EditText leftOperandEditText, rightOperandEditText;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        leftOperandEditText = findViewById(R.id.et1);
        rightOperandEditText = findViewById(R.id.et2);

        Button plusButton = findViewById(R.id.plus);
        Button minusButton = findViewById(R.id.minus);
        Button multiplyButton = findViewById(R.id.multiply);
        Button devideButton = findViewById(R.id.divide);
        Button compareButton = findViewById(R.id.compare);

        resultTextView = findViewById(R.id.tvResult);

        plusButton.setOnClickListener(this);
        minusButton.setOnClickListener(this);
        multiplyButton.setOnClickListener(this);
        devideButton.setOnClickListener(this);
        compareButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        resultTextView.setText(
                calculator.calculateResult(
                        leftOperandEditText.getText().toString(),
                        rightOperandEditText.getText().toString(),
                        getOperatorForButton(v.getId())
                )
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0, MENU_RESET_ID, 0, "Reset");
        menu.add(0, MENU_QUIT_ID, 0, "Quit");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case MENU_RESET_ID:
                clearInput();
                break;
            case MENU_QUIT_ID:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void clearInput() {
        leftOperandEditText.setText("");
        rightOperandEditText.setText("");
        resultTextView.setText("");
    }

    private Operator getOperatorForButton(int buttonId) {
        switch (buttonId) {
            case R.id.plus:
                return Operator.PLUS;
            case R.id.minus:
                return Operator.MINUS;
            case R.id.multiply:
                return Operator.MULTIPLY;
            case R.id.divide:
                return Operator.DIVIDE;
            case R.id.compare:
                return Operator.COMPARE;
        }
        return Operator.UNKNOWN;
    }
}

