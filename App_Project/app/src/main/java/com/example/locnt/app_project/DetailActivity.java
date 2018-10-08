package com.example.locnt.app_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    RatingBar mRatingBar;
    TextView mRatingScale;
    EditText mFeedback;
    Button mSendFeedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        createView();
        rate();
    }

    private void rate() {
        mRatingBar = findViewById(R.id.ratingBar);
        mRatingScale = findViewById(R.id.txtRating);
        mFeedback = findViewById(R.id.edt);
        mSendFeedback = findViewById(R.id.btnSubmit);
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRatingScale.setText(String.valueOf(v));
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        mRatingScale.setText("Ghét");
                        break;
                    case 2:
                        mRatingScale.setText("Không thích");
                        break;
                    case 3:
                        mRatingScale.setText("OK");
                        break;
                    case 4:
                        mRatingScale.setText("Thích");
                        break;
                    case 5:
                        mRatingScale.setText("Rất thích");
                        break;
                    default:
                        mRatingScale.setText("");
                }
            }
        });

        mSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFeedback.getText().toString().isEmpty()) {
                    mSendFeedback.setEnabled(false);
                } else {
                    SharedPreferences pre = getSharedPreferences("feedback", MODE_PRIVATE);
                    SharedPreferences.Editor edit = pre.edit();
                    edit.putString("datafeedback",mFeedback.getText().toString());
                    edit.putString("ratefeedback",mRatingScale.getText().toString());
                    edit.commit();
                    mFeedback.setText("");
                    mRatingBar.setRating(0);
                    Toast.makeText(DetailActivity.this, "Cảm ơn bạn đã đánh giá.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void createView() {
        Intent intent = this.getIntent();
        String id = intent.getStringExtra("item");
        Toast.makeText(this, "item = " + id, Toast.LENGTH_SHORT).show();
    }
}
