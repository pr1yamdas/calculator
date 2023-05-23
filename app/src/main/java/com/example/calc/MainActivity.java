package com.example.calc;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTv, solutionTv;
    MaterialButton buttonC,buttonBracketOpen,buttonbracketClose;
    MaterialButton buttonDivide,buttonMultiply,buttonPlus,buttonMinus,buttonequals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttondot;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         resultTv=findViewById(R.id.result_tv);
         solutionTv=findViewById(R.id.solution_tv);

         assignId(buttonC,R.id.button_c);
        assignId(buttonBracketOpen,R.id.button_open_bracket);
        assignId(buttonbracketClose,R.id.button_close_bracket);
        assignId(button1,R.id.button_one);
        assignId(button2,R.id.button_two);
        assignId(button3,R.id.button_three);
        assignId(button4,R.id.button_four);
        assignId(button5,R.id.button_five);
        assignId(button6,R.id.button_six);
        assignId(button7,R.id.button_seven);
        assignId(button8,R.id.button_eight);
        assignId(button9,R.id.button_nine);
        assignId(button0,R.id.button_zero);
        assignId(buttonMultiply,R.id.button_multiply);
        assignId(buttonDivide,R.id.button_divide);
        assignId(buttonPlus,R.id.button_add);
        assignId(buttonMinus,R.id.button_subtract);
        assignId(buttonequals,R.id.button_equal);
        assignId(buttondot,R.id.button_decimal);
       assignId(buttonAC,R.id.button_ac);




    }

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        MaterialButton button=(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if (buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if (buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }
        if (buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }


        solutionTv.setText(dataToCalculate);

        String finalresult = getresult(dataToCalculate);

        if(!finalresult.equals("error")){
            resultTv.setText(finalresult);
        }



    }

    String getresult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalresult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if (finalresult.endsWith(".0")){
                finalresult = finalresult.replace(".0","");
            }

            return finalresult;
        }catch(Exception e){
            return "error";
        }
    }






}