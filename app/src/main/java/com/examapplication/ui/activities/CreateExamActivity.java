package com.examapplication.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.examapplication.R;
import com.examapplication.utility.DateTimeUtility;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.Date;

public class CreateExamActivity extends ParentActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener
{

    private Context mContext;
    private CardView cardView1, cardView2, cardView3, cardView4;
    private TextInputEditText inputExamName, inputMainCategory, inputSubCategory, inputSection, inputStartDate,
            inputEndDate, inputSellingPrice, inputOfferPrice, inputTotalQuestion, inputExamTime, inputMaxMarks,
            inputPassingMarks, inputResultDate, inputDescription, inputShortDescription, inputNote, inputInstructionSet;
    private Button btnCancel, btnNext, btnPrevious1, btnOk, btnPrevious2, btnNextQuestion, btnPrevious3, btnSave;
    private RelativeLayout relativeStartDate, relativeEndDate, relativeResultDate;
    private TextView txtTotalQuestion, txtMaxMarks, txtExamTime;

    private String totalQuestions = "", maxMarks = "", examTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exam);

        mContext = this;

        cardView1 = (CardView)findViewById(R.id.card_view1);
        cardView1.setVisibility(View.VISIBLE);
        cardView2 = (CardView)findViewById(R.id.card_view2);
        cardView2.setVisibility(View.GONE);
        cardView3 = (CardView)findViewById(R.id.card_view3);
        cardView3.setVisibility(View.GONE);
        cardView4 = (CardView)findViewById(R.id.card_view4);
        cardView4.setVisibility(View.GONE);

        inputExamName = (TextInputEditText)findViewById(R.id.input_exam_name);
        inputMainCategory = (TextInputEditText)findViewById(R.id.input_main_category);
        inputSubCategory = (TextInputEditText)findViewById(R.id.input_sub_category);
        inputSection = (TextInputEditText)findViewById(R.id.input_section);
        inputStartDate = (TextInputEditText)findViewById(R.id.input_start_date);
        inputEndDate = (TextInputEditText)findViewById(R.id.input_end_date);
        inputSellingPrice = (TextInputEditText)findViewById(R.id.input_selling_price);
        inputOfferPrice = (TextInputEditText)findViewById(R.id.input_offer_price);

        inputTotalQuestion = (TextInputEditText)findViewById(R.id.input_total_questions);
        inputExamTime = (TextInputEditText)findViewById(R.id.input_exam_time);
        inputMaxMarks = (TextInputEditText)findViewById(R.id.input_max_marks);
        inputPassingMarks = (TextInputEditText)findViewById(R.id.input_passing_mark);
        inputResultDate = (TextInputEditText)findViewById(R.id.input_result_date);

        inputDescription = (TextInputEditText)findViewById(R.id.input_description);
        inputShortDescription = (TextInputEditText)findViewById(R.id.input_short_description);
        inputNote = (TextInputEditText)findViewById(R.id.input_note);
        inputInstructionSet = (TextInputEditText)findViewById(R.id.input_instruction_set);

        btnCancel = (Button)findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);
        btnNext = (Button)findViewById(R.id.btn_next);
        btnNext.setOnClickListener(this);
        btnPrevious1 = (Button)findViewById(R.id.btn_previous_details1);
        btnPrevious1.setOnClickListener(this);
        btnOk = (Button)findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(this);
        btnPrevious2 = (Button)findViewById(R.id.btn_previous);
        btnPrevious2.setOnClickListener(this);
        btnNextQuestion = (Button)findViewById(R.id.btn_next_question);
        btnNextQuestion.setOnClickListener(this);
        btnPrevious3 = (Button)findViewById(R.id.btn_previous_details);
        btnPrevious3.setOnClickListener(this);
        btnSave = (Button)findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);

        relativeStartDate = (RelativeLayout)findViewById(R.id.relative_start_date);
        relativeStartDate.setOnClickListener(this);
        relativeEndDate = (RelativeLayout)findViewById(R.id.relative_end_date);
        relativeEndDate.setOnClickListener(this);
        relativeResultDate = (RelativeLayout)findViewById(R.id.relative_end_date);
        relativeResultDate.setOnClickListener(this);

        txtTotalQuestion = (TextView)findViewById(R.id.txt_total_question);
        txtMaxMarks = (TextView)findViewById(R.id.txt_max_marks);
        txtExamTime = (TextView)findViewById(R.id.txt_exam_time);

        inputTotalQuestion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                totalQuestions = inputTotalQuestion.getText().toString().trim();
            }

            @Override
            public void afterTextChanged(Editable s)
            {}
        });

        inputExamTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                examTime = inputExamTime.getText().toString().trim();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputMaxMarks.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                maxMarks = inputMaxMarks.getText().toString().trim();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void onClick(View v)
    {
        if(v == relativeStartDate)
        {
            showCalendar();
        }

        if(v == relativeEndDate)
        {

        }

        if(v == relativeResultDate)
        {

        }

        if(v == btnCancel)
        {
            finish();
        }

        if(v == btnNext)
        {
            cardView1.setVisibility(View.GONE);
            cardView2.setVisibility(View.VISIBLE);
        }

        if(v == btnPrevious1)
        {
            cardView1.setVisibility(View.VISIBLE);
            cardView2.setVisibility(View.GONE);
        }

        if(v == btnOk)
        {
            cardView2.setVisibility(View.GONE);
            cardView3.setVisibility(View.VISIBLE);
            txtTotalQuestion.setText(totalQuestions);
            txtMaxMarks.setText(maxMarks);
            txtExamTime.setText(examTime);
        }

        if(v == btnPrevious2)
        {
            cardView2.setVisibility(View.VISIBLE);
            cardView3.setVisibility(View.GONE);
        }

        if(v == btnNextQuestion)
        {
            cardView3.setVisibility(View.GONE);
            cardView4.setVisibility(View.VISIBLE);
        }

        if(v == btnPrevious3)
        {
            cardView3.setVisibility(View.VISIBLE);
            cardView4.setVisibility(View.GONE);
        }

        if(v == btnSave)
        {

        }
    }

    private void showCalendar()
    {
        Calendar now = Calendar.getInstance();
        Date date = new Date();
        now.setTime(date);
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                CreateExamActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setMinDate(now);
        dpd.setThemeDark(false);
        dpd.vibrate(true);
        dpd.dismissOnPause(false);
        dpd.showYearPickerFirst(false);
        dpd.setAccentColor(getResources().getColor(R.color.colorPrimaryDark));
        dpd.setTitle("Select Date");
        dpd.show(this.getFragmentManager(), "Date");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth)
    {
        inputStartDate.setText(DateTimeUtility.displayDate());
    }
}
