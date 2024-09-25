package com.example.karam;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView movableImage;
    private Button startButton, stopButton, slowButton, fastButton;
    private TextView scoreTextView;

    private int score = 0;
    private boolean isGameRunning = false;
    private int speed = 1000; // milliseconds
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movableImage = findViewById(R.id.movableImage);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);
        slowButton = findViewById(R.id.slowButton);
        fastButton = findViewById(R.id.fastButton);
        scoreTextView = findViewById(R.id.scoreTextView);

        startButton.setOnClickListener(v -> startGame());
        stopButton.setOnClickListener(v -> stopGame());
        slowButton.setOnClickListener(v -> changeSpeed(500));
        fastButton.setOnClickListener(v -> changeSpeed(200));
        movableImage.setOnClickListener(v -> increaseScore());
    }

    private void startGame() {
        score = 0;
        isGameRunning = true;
        scoreTextView.setText("Score: " + score);
        moveImage();
    }

    private void stopGame() {
        isGameRunning = false;
        handler.removeCallbacksAndMessages(null);
        showScoreDialog();
    }

    private void moveImage() {
        if (!isGameRunning) return;

        int randomX = new Random().nextInt(
                getResources().getDisplayMetrics().widthPixels - movableImage.getWidth());
        int randomY = new Random().nextInt(
                getResources().getDisplayMetrics().heightPixels - movableImage.getHeight());

        movableImage.animate().x(randomX).y(randomY).setDuration(500).start();
        handler.postDelayed(this::moveImage, speed);
    }

    private void increaseScore() {
        score++;
        scoreTextView.setText("Score: " + score);
    }

    private void changeSpeed(int newSpeed) {
        speed = newSpeed;
        if (isGameRunning) {
            handler.removeCallbacksAndMessages(null);
            moveImage();
        }
    }

    private void showScoreDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Game Over")
                .setMessage("Your Score: " + score)
                .setPositiveButton("OK", (DialogInterface dialog, int which) -> dialog.dismiss())
                .show();
    }
}
