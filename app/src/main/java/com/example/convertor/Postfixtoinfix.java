package com.example.convertor;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import java.util.Stack;
public class Postfixtoinfix extends AppCompatActivity {

    private EditText editTextPostfix;
    private TextView textViewInfix;
    private Button buttonConvert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postfixtoinfix);

        editTextPostfix = findViewById(R.id.input3);
        textViewInfix = findViewById(R.id.output3);
        buttonConvert = findViewById(R.id.convert3);

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String postfixExpression = editTextPostfix.getText().toString();
                String infixExpression = postfixToInfix(postfixExpression);
                textViewInfix.setText(infixExpression);
            }
        });
    }
    private String postfixToInfix(String postfix) {
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                stack.push(String.valueOf(c));
            } else {
                String operand2 = stack.pop();
                String operand1 = stack.pop();
                String expression = "(" + operand1 + c + operand2 + ")";
                stack.push(expression);
            }
        }

        return stack.pop();
    }
}