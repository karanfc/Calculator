package com.example.admin.fragmentspract;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.fragmentspract.R;

public class BasicOperatnFrag2 extends Fragment implements View.OnClickListener {
    sendDataFromTwo mSendDataFrom2;
    TextView sine,cos,tan,open_bracket,close_bracket,pi,exponential,raisedTo,factorial,squareRoot,log,reciprocal;

    String result;
    Double resultDouble;

    public interface sendDataFromTwo{
        public void onSend2(String data2);
        public void onSendAns1(String data);
        public String onrecieve();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mSendDataFrom2=(sendDataFromTwo) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.basic_operatn2, container, false);
        sine=(TextView)view.findViewById(R.id.sin);
        cos=(TextView)view.findViewById(R.id.cos);
        tan=(TextView)view.findViewById(R.id.tan);
        log=(TextView)view.findViewById(R.id.log);
        factorial=(TextView)view.findViewById(R.id.factorial);
        pi=(TextView)view.findViewById(R.id.pi);
        raisedTo=(TextView)view.findViewById(R.id.raisedTo);
        exponential=(TextView)view.findViewById(R.id.exponential);
        squareRoot=(TextView)view.findViewById(R.id.squareRoot);
        reciprocal=(TextView)view.findViewById(R.id.reciprocal);
        open_bracket=(TextView)view.findViewById(R.id.open_bracket);
        close_bracket=(TextView)view.findViewById(R.id.close_bracket);
        sine.setOnClickListener(this);
        cos.setOnClickListener(this);
        tan.setOnClickListener(this);
        log.setOnClickListener(this);
        factorial.setOnClickListener(this);
        pi.setOnClickListener(this);
        raisedTo.setOnClickListener(this);
        exponential.setOnClickListener(this);
        squareRoot.setOnClickListener(this);
        reciprocal.setOnClickListener(this);
        open_bracket.setOnClickListener(this);
        close_bracket.setOnClickListener(this);


        /* sine.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        mSendDataFrom2.onSend2("sine");
                    }
                }
        );*/

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.sin:
                    mSendDataFrom2.onSend2("sin(");
                    break;

            case R.id.cos:
                mSendDataFrom2.onSend2("cos(");
                break;

            case R.id.tan:
                mSendDataFrom2.onSend2("tan(");
                break;

            case R.id.log:
                mSendDataFrom2.onSend2("log(");
                break;

            case R.id.factorial:
                mSendDataFrom2.onSend2("!");
                result = mSendDataFrom2.onrecieve();
                resultDouble= eval(result);
                result = resultDouble.toString();
                mSendDataFrom2.onSendAns1(result);
                break;

            case R.id.pi:
                mSendDataFrom2.onSend2("π");
                result = mSendDataFrom2.onrecieve();
                resultDouble= eval(result);
                result = resultDouble.toString();
                mSendDataFrom2.onSendAns1(result);
                break;

            case R.id.raisedTo:
                mSendDataFrom2.onSend2("^");
                break;

            case R.id.exponential:
                mSendDataFrom2.onSend2("e");
                result = mSendDataFrom2.onrecieve();
                resultDouble= eval(result);
                result = resultDouble.toString();
                mSendDataFrom2.onSendAns1(result);
                break;

            case R.id.squareRoot:
                mSendDataFrom2.onSend2("√");
                break;

            case R.id.reciprocal:
                mSendDataFrom2.onSend2("(1÷");
                break;

            case R.id.open_bracket:
                mSendDataFrom2.onSend2("×(");
                break;

            case R.id.close_bracket:
                mSendDataFrom2.onSend2(")");
                result = mSendDataFrom2.onrecieve();
                resultDouble= eval(result);
                result = resultDouble.toString();
                mSendDataFrom2.onSendAns1(result);
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
                if (eat('π')) x = 3.14159;
                if (eat('e')) x = 2.71828;
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