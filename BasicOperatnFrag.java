package com.example.admin.fragmentspract;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.fragmentspract.R;

public class BasicOperatnFrag extends Fragment implements View.OnClickListener {
    sendDataFromOne msentData;
    TextView one, two, three, four, five, six, seven, eight, nine, zero, add, subtract, multiply, divide, equals,cancel,decimal;
    String result;
    Double resultDouble;

    public interface sendDataFromOne{
        public void onSend(String data);
        public void onSendAns1(String data);
        public String onrecieve();
        public void onEquals();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            msentData=(sendDataFromOne) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_basic_operatn, container, false);

        one = (TextView)view.findViewById(R.id.one);
        two = (TextView)view.findViewById(R.id.two);
        three = (TextView)view.findViewById(R.id.three);
        four = (TextView)view.findViewById(R.id.four);
        five = (TextView)view.findViewById(R.id.five);
        six = (TextView)view.findViewById(R.id.six);
        seven = (TextView)view.findViewById(R.id.seven);
        eight = (TextView)view.findViewById(R.id.eight);
        nine = (TextView)view.findViewById(R.id.nine);
        zero = (TextView)view.findViewById(R.id.zero);
        add = (TextView)view.findViewById(R.id.add);
        subtract = (TextView)view.findViewById(R.id.sub);
        multiply = (TextView)view.findViewById(R.id.mul);
        divide = (TextView)view.findViewById(R.id.div);
        cancel = (TextView)view.findViewById(R.id.cancel);
        decimal = (TextView)view.findViewById(R.id.decimal);
        equals = (TextView)view.findViewById(R.id.equals);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        add.setOnClickListener(this);
        subtract.setOnClickListener(this);
        multiply.setOnClickListener(this);
        divide.setOnClickListener(this);
        cancel.setOnClickListener(this);
        decimal.setOnClickListener(this);
        equals.setOnClickListener(this);
       /* one.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        msentData.onSend("1");
                    }
                }
        );*/
        return  view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.one:
                msentData.onSend("1");
                result = msentData.onrecieve();
                resultDouble= eval(result);
                result = resultDouble.toString();
                msentData.onSendAns1(result);
                break;

            case R.id.two:
                msentData.onSend("2");
                result = msentData.onrecieve();
                resultDouble= eval(result);
                result = resultDouble.toString();
                msentData.onSendAns1(result);
                break;

            case R.id.three:
                msentData.onSend("3");
                result = msentData.onrecieve();
                resultDouble= eval(result);
                result = resultDouble.toString();
                msentData.onSendAns1(result);
                break;

            case R.id.four:
                msentData.onSend("4");
                result = msentData.onrecieve();
                resultDouble= eval(result);
                result = resultDouble.toString();
                msentData.onSendAns1(result);
                break;

            case R.id.five:
                msentData.onSend("5");
                result = msentData.onrecieve();
                resultDouble= eval(result);
                result = resultDouble.toString();
                msentData.onSendAns1(result);
                break;

            case R.id.six:
                msentData.onSend("6");
                result = msentData.onrecieve();
                resultDouble= eval(result);
                result = resultDouble.toString();
                msentData.onSendAns1(result);
                break;

            case R.id.seven:
                msentData.onSend("7");
                result = msentData.onrecieve();
                resultDouble= eval(result);
                result = resultDouble.toString();
                msentData.onSendAns1(result);
                break;

            case R.id.eight:
                msentData.onSend("8");
                result = msentData.onrecieve();
                resultDouble= eval(result);
                result = resultDouble.toString();
                msentData.onSendAns1(result);
                break;

            case R.id.nine:
                msentData.onSend("9");
                result = msentData.onrecieve();
                resultDouble= eval(result);
                result = resultDouble.toString();
                msentData.onSendAns1(result);
                break;

            case R.id.zero:
                msentData.onSend("0");
                result = msentData.onrecieve();
                resultDouble= eval(result);
                result = resultDouble.toString();
                msentData.onSendAns1(result);
                break;

            case R.id.add:
                msentData.onSend("+");

                break;

            case R.id.sub:
                msentData.onSend("-");

                break;

            case R.id.mul:
                msentData.onSend("×");

                break;

            case R.id.div:
                msentData.onSend("÷");

                break;

            case R.id.decimal:
                msentData.onSend(".");

                break;

            case R.id.cancel:
                msentData.onSend("CANCEL");
                break;

            case R.id.equals:
               msentData.onEquals();
                break;
        }
    }
    public double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('×')) x *= parseFactor(); // multiplication
                    else if (eat('÷')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else if (func.equals("log")) x = Math.log(Math.toRadians(x));
                    else x = 1;
                }
                    else x=1;
                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation
                if (eat('√')) x = Math.sqrt(parseFactor());
                if (eat('!'))
                {
                    int i;
                    double fact=1;
                    for(i=1;i<=x;i++){
                        fact=fact*i;
                    }
                    return fact;
                }
               return x;
            }
        }.parse();

    }

}