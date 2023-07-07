package com.example.convertor;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import java.util.Stack;
public class Prefixtoinfix extends AppCompatActivity {

    private EditText editTextPrefix;
    private TextView textViewInfix;
    private Button buttonConvert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefixtoinfix);
        editTextPrefix = findViewById(R.id.input4);
        textViewInfix = findViewById(R.id.output4);
        buttonConvert = findViewById(R.id.convert4);

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prefixExpression = editTextPrefix.getText().toString();
                String infixExpression = prefixToInfix(prefixExpression);
                textViewInfix.setText(infixExpression);
            }
        });
    }
    private String prefixToInfix(String prefix) {
        Stack<String> stack = new Stack<>();

        for (int i = prefix.length() - 1; i >= 0; i--) {
            char c = prefix.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                stack.push(String.valueOf(c));
            } else {
                String operand1 = stack.pop();
                String operand2 = stack.pop();
                String expression = "(" + operand1 + c + operand2 + ")";
                stack.push(expression);
            }
        }

        return stack.pop();
    }
}