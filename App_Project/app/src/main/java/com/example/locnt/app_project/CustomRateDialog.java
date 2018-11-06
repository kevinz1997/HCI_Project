package com.example.locnt.app_project;

import android.app.Dialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class CustomRateDialog extends Dialog {

    public Dialog dialog;
    public Button btnRate;
    public Context mContext;

    TextView mRatingScale, nameRate;
    EditText mFeedback;
    TextView mSendFeedback, mCancelRate;
    RatingBar mRatingBar;

    public CustomRateDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_rate_dialog);
        mRatingBar = findViewById(R.id.ratingBarHistory);
        rate();
        LayerDrawable stars = (LayerDrawable) mRatingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(ContextCompat.getColor(mContext, R.color.yellow), PorterDuff.Mode.SRC_ATOP);
    }

    private void rate() {
        nameRate = findViewById(R.id.txtNameRate);
        nameRate.setText("Sân bóng đá cỏ nhân tạo" + "\n" + " Đạt Đức");
        mRatingScale = findViewById(R.id.txtRating);
        mFeedback = findViewById(R.id.edt);
        mSendFeedback = findViewById(R.id.btnRate);
        mCancelRate = findViewById(R.id.btnCancelRate);
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
//                    SharedPreferences pre = getSharedPreferences("feedback", MODE_PRIVATE);
//                    SharedPreferences.Editor edit = pre.edit();
//                    edit.putString("datafeedback",mFeedback.getText().toString());
//                    edit.putString("ratefeedback",mRatingScale.getText().toString());
//                    edit.commit();
                Toast.makeText(mContext, "Cảm ơn bạn đã đánh giá.", Toast.LENGTH_SHORT).show();
                mFeedback.setText("");
                mRatingBar.setRating(5);
                dismiss();
            }
        });

        mCancelRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
