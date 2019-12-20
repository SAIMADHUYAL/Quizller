package com.saimadhu.quizller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // TODO: Declare constants here

    final int PROGRESS_BAR_INCREMENT = 8;

    // TODO: Declare member variables here:
    Button mTrueButton;
    Button mFalseButton;

    ProgressBar mProgressBar;
    TextView mQuestionTextView;
    int mIndex,mScoreUpdate = 0;
    int mQuestion;
    TextView mScore;


    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mQuestionTextView = findViewById(R.id.question_text_view);
        mScore = findViewById(R.id.score);
        mProgressBar = findViewById(R.id.progress_bar);

        mQuestion = mQuestionBank[mIndex].getQuestionID();
        mQuestionTextView.setText(mQuestion);

        View.OnClickListener myListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.true_button) {

                 updateQuestion();
                 checkAnswer(true);
                }
                if(v.getId() == R.id.false_button){
                 updateQuestion();
                 checkAnswer(false);
                }
            }
        };

        mTrueButton.setOnClickListener(myListener);
        mFalseButton.setOnClickListener(myListener);


    }

    private void updateQuestion(){


        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        if(mIndex < mQuestionBank.length-1) {
            mIndex = mIndex + 1;
            mQuestion = mQuestionBank[mIndex].getQuestionID();
            mQuestionTextView.setText(mQuestion);
        }
        else{
            mQuestionTextView.setText("Quiz is completed");
            mFalseButton.setEnabled(false);
            mTrueButton.setEnabled(false);
        }
    }

    private void checkAnswer(boolean userSelection){
        boolean correctAnswer = mQuestionBank[mIndex].isAnswer();
        if(userSelection == correctAnswer){
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
            mScoreUpdate = mScoreUpdate +1;
            mScore.setText("Score: "+mScoreUpdate+" / "+mQuestionBank.length);
        }
        else{
            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }
    }
}