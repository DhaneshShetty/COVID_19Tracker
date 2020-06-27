package com.example.covid_19tracker;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.example.covid_19tracker.Fragments.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

public class Launcher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        final View view=findViewById(R.id.launch);
        final int x=view.getRight();
        final int y=view.getBottom();
        final int endRadius = (int) Math.hypot(view.getWidth(), view.getHeight());
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                        startActivity(new Intent(Launcher.this, MainActivity.class));


            }
        },4000);
    }
}
