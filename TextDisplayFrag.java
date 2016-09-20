package com.example.admin.fragmentspract;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.fragmentspract.R;
public class TextDisplayFrag extends Fragment {

    TextView topText;
    TextView botText;
    Double result;
    int result1;
    String Result;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_text_display, container, false);
        topText = (TextView)view.findViewById(R.id.ed2);
        botText = (TextView)view.findViewById(R.id.ed3);
        topText.setText("");
        botText.setText("");
        return view;
    }

    public void setText(String num){
       topText.append(num);
        if(num.equals("CANCEL")){
            topText.setText("");
            botText.setText("");
        }
    }

    public void setAns(String num){
        result = Double.parseDouble(num);
        if (result % 1 == 0) {
            result1 = result.intValue();
            Result = Integer.toString(result1);
            botText.setText(Result);

        } else {
            Result = Double.toString(result);
            botText.setText(Result);
        }
    }

    public String getString(){
        String s;
        s = topText.getText().toString();
        return s;
    }

    public void setEquals(){
        topText.setText(botText.getText().toString());
        botText.setText("");
    }


}