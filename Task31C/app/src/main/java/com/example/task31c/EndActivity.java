package com.example.task31c;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class EndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        String userName = getIntent().getStringExtra("PLAYER_NAME");

        int score = getIntent().getIntExtra("SCORE", 0);

        TextView congratsTextView = findViewById(R.id.congratsTextView);
        congratsTextView.setText(getString(R.string.congrats_message, userName));

        TextView scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText(getString(R.string.score_message, score));

        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndActivity.this, StartActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button closeButton = findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }
}
