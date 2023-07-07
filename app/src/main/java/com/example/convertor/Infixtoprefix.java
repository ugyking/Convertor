package com.example.convertor;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;

import java.util.Stack;
public class Infixtoprefix extends AppCompatActivity {

    private EditText editTextInfix;
    private TextView textViewPrefix;
    private Button buttonConvert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infixtoprefix);

        editTextInfix = findViewById(R.id.input2);
        textViewPrefix = findViewById(R.id.output2);
        buttonConvert = findViewById(R.id.convert2);

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String infixExpression = editTextInfix.getText().toString();
                String prefixExpression = infixToPrefix(infixExpression);
                textViewPrefix.setText(prefixExpression);
            }
        });
    }
    private String infixToPrefix(String infix) {
        StringBuilder prefix = new StringBuilder();
        StringBuilder reversedInfix = new StringBuilder(infix).reverse();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < reversedInfix.length(); i++) {
            char c = reversedInfix.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                prefix.append(c);
            } else if (c == ')') {
                stack.push(c);
            } else if (c == '(') {
                while (!stack.isEmpty() && stack.peek() != ')') {
                    prefix.append(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() != ')') {
                    return "Invalid Expression"; // if parentheses are not balanced
                } else {
                    stack.pop(); // Pop ')' from the stack
                }
            } else { // Operator encountered
                while (!stack.isEmpty() && precedence(c) < precedence(stack.peek())) {
                    if (stack.peek() == ')') {
                        return "Invalid Expression"; //same as above
                    }
                    prefix.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == ')') {
                return "Invalid Expression"; // same as above
            }
            prefix.append(stack.pop());
        }

        return prefix.reverse().toString();
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