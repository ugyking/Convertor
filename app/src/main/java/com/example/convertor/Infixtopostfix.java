package com.example.convertor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Stack;


public class Infixtopostfix extends AppCompatActivity {

    private EditText editTextInfix;
    private TextView textViewPostfix;
    private Button buttonConvert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infixtopostfix);

        editTextInfix = findViewById(R.id.input1);
        textViewPostfix = findViewById(R.id.output1);
        buttonConvert = findViewById(R.id.convert1);

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertToPostfix();
            }
        });
    }
    private void convertToPostfix() {
        String infixExpression = editTextInfix.getText().toString().trim();

        if (TextUtils.isEmpty(infixExpression)) {
            editTextInfix.setError("Enter an infix expression");
            return;
        }

        String postfixExpression = infixToPostfix(infixExpression);
        textViewPostfix.setText(postfixExpression);
    }
    private String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                postfix.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() != '(') {
                    return "Invalid Expression"; // Invalid expression if parentheses are not balanced
                } else {
                    stack.pop(); // Pop '(' from the stack
                }
            } else { // Operator encountered
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    if (stack.peek() == '(') {
                        return "Invalid Expression"; //same as above
                    }
                    postfix.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                return "Invalid Expression"; //same as above
            }
            postfix.append(stack.pop());
        }

        return postfix.toString();
    }
    private int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }
}