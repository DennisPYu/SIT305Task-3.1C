package com.example.task31c;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton option1RadioButton, option2RadioButton, option3RadioButton;
    private Button submitButton;
    private TextView scoreTextView;
    private ProgressBar progressBar;
    int currentQuestionIndex = 0;
    private int score = 0;
    private List<Question> questionList = new ArrayList<>();
    private Question currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionTextView = findViewById(R.id.questionTextView);
        option1RadioButton = findViewById(R.id.option1RadioButton);
        option2RadioButton = findViewById(R.id.option2RadioButton);
        option3RadioButton = findViewById(R.id.option3RadioButton);
        submitButton = findViewById(R.id.submitButton);
        progressBar = findViewById(R.id.progressBar);
        scoreTextView = findViewById(R.id.scoreTextView);

        initializeQuestionList();

        updateProgressText();

        showNextQuestion();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void initializeQuestionList() {
        questionList.add(new Question("Question: Who is Kobe Bryant ", "Type of Snake", "Basketball Player", "A type of Vehicle", 2));
        questionList.add(new Question("Question: What does BBC stand for?", "British Broadcasting Corporation", "Big Black Cooker", "Boy Be Careful", 1));
        questionList.add(new Question("Question: How many bones are in the human body?", "23", "299", "206", 3));
        questionList.add(new Question("Question: What galaxy do we live in", "Magellanic Clouds", "The Milky Way", " Andromeda Galaxy", 2));
        questionList.add(new Question("Question: Who gave the U.S. The Statue of Liberty?", "France", "England", "Australia", 1));
        questionList.add(new Question("Question: What is the capital of Australia?", "Melbourne", "Sydney", "Canberra", 3));
        questionList.add(new Question("Question: What is the chemical symbol for gold?", "Eu", "Au", "Su", 2));

        Collections.shuffle(questionList);

        questionList = questionList.subList(0, 5);
    }

    private void showNextQuestion() {
        if (currentQuestionIndex < questionList.size()) {
            currentQuestion = questionList.get(currentQuestionIndex);

            questionTextView.setText(currentQuestion.getQuestion());
            option1RadioButton.setText(currentQuestion.getOption1());
            option2RadioButton.setText(currentQuestion.getOption2());
            option3RadioButton.setText(currentQuestion.getOption3());

            option1RadioButton.setTextColor(Color.BLACK);
            option2RadioButton.setTextColor(Color.BLACK);
            option3RadioButton.setTextColor(Color.BLACK);
            option1RadioButton.setChecked(false);
            option2RadioButton.setChecked(false);
            option3RadioButton.setChecked(false);

            submitButton.setEnabled(true);

            progressBar.setProgress(currentQuestionIndex + 1);
            updateProgressText();
        } else {
            Intent intent = new Intent(QuizActivity.this, EndActivity.class);
            intent.putExtra("PLAYER_NAME", getIntent().getStringExtra("PLAYER_NAME"));
            intent.putExtra("SCORE", score); // Pass score to EndActivity
            startActivity(intent);
            finish();
        }
    }

    private void checkAnswer() {
        int selectedOption = getSelectedOption();
        int correctOption = currentQuestion.getCorrectOption();

        disableOptionsAndButton();

        if (selectedOption == correctOption) {
            markAnswerCorrect(correctOption);
            score++;
        } else {
            markAnswerIncorrect(correctOption);
        }

        updateScore();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                currentQuestionIndex++;
                showNextQuestion();
                enableOptionsAndButton();
            }
        }, 1000);
    }

    private void updateScore() {
        scoreTextView.setText("Score: " + score);
    }

    private void markAnswerCorrect(int correctOption) {
        switch (correctOption) {
            case 1:
                option1RadioButton.setTextColor(Color.GREEN);
                break;
            case 2:
                option2RadioButton.setTextColor(Color.GREEN);
                break;
            case 3:
                option3RadioButton.setTextColor(Color.GREEN);
                break;
        }
    }

    private void updateProgressText() {
        scoreTextView.setText(String.format(Locale.getDefault(), "%d/%d", currentQuestionIndex + 1, questionList.size()));
    }

    private void markAnswerIncorrect(int correctOption) {
        switch (correctOption) {
            case 1:
                option1RadioButton.setTextColor(Color.GREEN);
                break;
            case 2:
                option2RadioButton.setTextColor(Color.GREEN);
                break;
            case 3:
                option3RadioButton.setTextColor(Color.GREEN);
                break;
        }

        int selectedOption = getSelectedOption();
        switch (selectedOption) {
            case 1:
                option1RadioButton.setTextColor(Color.RED);
                break;
            case 2:
                option2RadioButton.setTextColor(Color.RED);
                break;
            case 3:
                option3RadioButton.setTextColor(Color.RED);
                break;
        }
    }

    private void disableOptionsAndButton() {
        option1RadioButton.setEnabled(false);
        option2RadioButton.setEnabled(false);
        option3RadioButton.setEnabled(false);
        submitButton.setEnabled(false);
    }

    private void enableOptionsAndButton() {
        option1RadioButton.setEnabled(true);
        option2RadioButton.setEnabled(true);
        option3RadioButton.setEnabled(true);
        submitButton.setEnabled(true);
    }

    private int getSelectedOption() {
        if (option1RadioButton.isChecked()) {
            return 1;
        } else if (option2RadioButton.isChecked()) {
            return 2;
        } else if (option3RadioButton.isChecked()) {
            return 3;
        } else {
            return -1;
        }
    }


    public class Question {
        private String question;
        private String option1;
        private String option2;
        private String option3;
        private int correctOption;

        public Question(String question, String option1, String option2, String option3, int correctOption) {
            this.question = question;
            this.option1 = option1;
            this.option2 = option2;
            this.option3 = option3;
            this.correctOption = correctOption;
        }

        public String getQuestion() {
            return question;
        }

        public String getOption1() {
            return option1;
        }

        public String getOption2() {
            return option2;
        }

        public String getOption3() {
            return option3;
        }

        public int getCorrectOption() {
            return correctOption;
        }
    }
}