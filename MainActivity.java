package com.example.admin.fragmentspract;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

public class MainActivity extends AppCompatActivity implements BasicOperatnFrag.sendDataFromOne,BasicOperatnFrag2.sendDataFromTwo {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_articles);

        TextDisplayFrag textDisplayFrag = new TextDisplayFrag();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container1, textDisplayFrag, "topTextField").commit();

        viewPager = (ViewPager) findViewById(R.id.view_pager);

        // Create an adapter that knows which fragment should be shown on each page
        SwipeAdapter adapter = new SwipeAdapter(getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

    }

    public void onSend(String num) {
        TextDisplayFrag textDisplayFrag = (TextDisplayFrag) getSupportFragmentManager().findFragmentByTag("topTextField");
        textDisplayFrag.setText(num);
    }

    public void onSend2(String num) {
        TextDisplayFrag textDisplayFrag = (TextDisplayFrag) getSupportFragmentManager().findFragmentByTag("topTextField");
        textDisplayFrag.setText(num);
    }

    public void onSendAns1(String num) {
        TextDisplayFrag textDisplayFrag = (TextDisplayFrag) getSupportFragmentManager().findFragmentByTag("topTextField");
        textDisplayFrag.setAns(num);
    }


    public String onrecieve() {
        String s;
        TextDisplayFrag textDisplayFrag = (TextDisplayFrag) getSupportFragmentManager().findFragmentByTag("topTextField");
        s = textDisplayFrag.getString();
        return  s;
    }

    public void onEquals() {
        TextDisplayFrag textDisplayFrag = (TextDisplayFrag) getSupportFragmentManager().findFragmentByTag("topTextField");
        textDisplayFrag.setEquals();
    }

}

