package com.example.karam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int i = 0;
    private TextView txtt;
    private Thread counterThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtt = findViewById(R.id.Txt);
    }

    public void startCount(View view) {
        counterThread = new Thread(() -> {
            try {
                while (true) {
                    i++;
                    txtt.setText(i + "");
                    Thread.sleep(1000);
                }
            } catch (Exception e) {}
        });
        counterThread.start();
    }

    public void stopCount(View view) {
        counterThread.interrupt();
    }
}