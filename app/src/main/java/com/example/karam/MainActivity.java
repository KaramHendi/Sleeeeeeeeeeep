package com.example.karam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btn1,btn2;
    private int i=0;
    private TextView txtt;
    private boolean is;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtt = findViewById(R.id.Txt);
        btn1=findViewById(R.id.start);
        btn2=findViewById(R.id.stop);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                is=true;
                while(is){
                    try {
                        Thread.sleep(1000);
                        i+=5;
                    }
                    catch (Exception e){}
                    txtt.setText(i+"");
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            is=false;
                        }
                    });
                }
                }
        });

    }
}