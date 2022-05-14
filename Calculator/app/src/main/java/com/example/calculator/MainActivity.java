package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    private TextView display2;
    private EditText display1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        display1 = findViewById(R.id.display1);
        display2 = findViewById(R.id.display2);

        display1.setShowSoftInputOnFocus(false);
    }

    void updateDisplay1(String addNew){
        String oldStr = display1.getText().toString();
        int cursorPos = display1.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        String newStr = String.format("%s%s%s", leftStr, addNew, rightStr);
        display1.setText(newStr);
        display1.setSelection(cursorPos + addNew.length());
    }

    public void zeroBtn(View view){
        updateDisplay1("0");
    }

    public void oneBtn(View view){
        updateDisplay1("1");
    }

    public void twoBtn(View view){
        updateDisplay1("2");
    }

    public void threeBtn(View view){
        updateDisplay1("3");
    }

    public void fourBtn(View view){
        updateDisplay1("4");
    }

    public void fiveBtn(View view){
        updateDisplay1("5");
    }

    public void sixBtn(View view){
        updateDisplay1("6");
    }

    public void sevenBtn(View view){
        updateDisplay1("7");
    }

    public void eightBtn(View view){
        updateDisplay1("8");
    }

    public void nineBtn(View view){
        updateDisplay1("9");
    }

    public void pointBtn(View view){
        updateDisplay1(".");
    }

    public void plusBtn(View view){
        updateDisplay1("+");
    }

    public void minusBtn(View view){
        updateDisplay1("-");
    }

    public void multiplyBtn(View view){
        updateDisplay1("×");
    }

    public void divideBtn(View view){
        updateDisplay1("÷");
    }

    public void ACBtn(View view){
        display1.setText("");
        display2.setText("");
    }

    public void openingBracketBtn(View view){
        updateDisplay1("(");
    }

    public void closingBracketBtn(View view){
        updateDisplay1(")");
    }

    public void backspaceBtn(View view){
        int cursorPos = display1.getSelectionStart();
        int strLength = display1.getText().length();

        if(cursorPos != 0 && strLength != 0){
            SpannableStringBuilder builder = (SpannableStringBuilder) display1.getText();
            builder.replace(cursorPos - 1, cursorPos, "");
            display1.setText(builder);
            display1.setSelection(cursorPos - 1);
        }
    }

    public void percentageBtn(View view){
        updateDisplay1("%");
    }

    public void sqrtBtn(View view){
        updateDisplay1("√(");
    }

    public void powerBtn(View view){
        updateDisplay1("^(");
    }

    public void piBtn(View view){
        updateDisplay1("π");
    }

    public void exponentBtn(View view){
        updateDisplay1("e");
    }

    public void logBtn(View view){
        updateDisplay1("log(");
    }

    public void lnBtn(View view){
        updateDisplay1("ln(");
    }

    public void sinBtn(View view){
        updateDisplay1("sin(");
    }

    public void cosBtn(View view){
        updateDisplay1("cos(");
    }

    public void tanBtn(View view){
        updateDisplay1("tan(");
    }

    public void equalBtn(View view){
        String userExp = display1.getText().toString();

        if(userExp.equals("")){
            return;
        }

        int openingBracket = 0;
        int closingBracket = 0;

        for(int i = 0; i < userExp.length(); i++){
            if(userExp.charAt(i) == '('){
                openingBracket += 1;
            }
            if(userExp.charAt(i) == ')'){
                closingBracket += 1;
            }
        }
        if(openingBracket > closingBracket){
            userExp = userExp + ")";
        }

        String modifiedExp = userExp;

        modifiedExp = modifiedExp.replaceAll("×", "*");
        modifiedExp = modifiedExp.replaceAll("÷", "/");
        modifiedExp = modifiedExp.replaceAll("√", "sqrt");
        modifiedExp = modifiedExp.replaceAll("π", "pi");
        modifiedExp = modifiedExp.replaceAll("log", "log10");

        Expression expression = new Expression(modifiedExp);
        String result = String.valueOf(expression.calculate());

        if(result.equals("NaN")){
            result = "Bad Expression";
        }
        if(result.charAt(result.length() - 1) == '0' && result.charAt(result.length() - 2) == '.'){
            result = result.substring(0, result.length() - 2);
        }

        display2.setText(userExp);
        display1.setText(result);

        display1.setSelection(result.length());
    }

}