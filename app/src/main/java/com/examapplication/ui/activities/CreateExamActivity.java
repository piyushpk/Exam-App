package com.examapplication.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.examapplication.R;
import com.examapplication.models.QuestionListModel;
import com.examapplication.ui.adapters.QuestionListAdapter;
import com.examapplication.utility.DateTimeUtility;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CreateExamActivity extends ParentActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener
{

    private Context mContext;
    private CardView cardView1, cardView2, cardView3, cardView4;
    private TextInputEditText inputExamName, inputMainCategory, inputSubCategory, inputSection, inputStartDate,
            inputEndDate, inputSellingPrice, inputOfferPrice, inputTotalQuestion, inputExamTime, inputMaxMarks,
            inputPassingMarks, inputResultDate, inputDescription, inputShortDescription, inputNote, inputInstructionSet;
    private Button btnCancel, btnNext, btnPrevious1, btnOk, btnPrevious2, btnNextQuestion, btnPrevious3, btnSave, btnAdd;
    private TextView txtTotalQuestion, txtMaxMarks, txtExamTime;

    private String setDate = "";
    private int totalQuestions = 0;
    private ImageView imgStartDate, imgEndDate, imgResultDate;

    int addSubQue = 1;
    private RecyclerView recyclerQuestionList;
    private LinearLayoutManager layoutManager;
    private QuestionListAdapter questionListAdapter;
    private ArrayList<QuestionListModel> questionListModels;

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
        inputStartDate.setEnabled(false);
        inputEndDate = (TextInputEditText)findViewById(R.id.input_end_date);
        inputEndDate.setEnabled(false);
        inputSellingPrice = (TextInputEditText)findViewById(R.id.input_selling_price);
        inputOfferPrice = (TextInputEditText)findViewById(R.id.input_offer_price);

        inputTotalQuestion = (TextInputEditText)findViewById(R.id.input_total_questions);
        inputExamTime = (TextInputEditText)findViewById(R.id.input_exam_time);
        inputMaxMarks = (TextInputEditText)findViewById(R.id.input_max_marks);
        inputPassingMarks = (TextInputEditText)findViewById(R.id.input_passing_mark);
        inputResultDate = (TextInputEditText)findViewById(R.id.input_result_date);
        inputResultDate.setEnabled(false);

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
        btnAdd = (Button)findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);

        txtTotalQuestion = (TextView)findViewById(R.id.txt_total_question);
        txtMaxMarks = (TextView)findViewById(R.id.txt_max_marks);
        txtExamTime = (TextView)findViewById(R.id.txt_exam_time);

        imgStartDate = (ImageView)findViewById(R.id.img_start_date);
        imgStartDate.setOnClickListener(this);
        imgEndDate = (ImageView)findViewById(R.id.img_end_date);
        imgEndDate.setOnClickListener(this);
        imgResultDate = (ImageView)findViewById(R.id.img_result_date);
        imgResultDate.setOnClickListener(this);

        inputTotalQuestion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count != 0) {
                    totalQuestions = Integer.parseInt(inputTotalQuestion.getText().toString().trim());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        recyclerQuestionList = (RecyclerView)findViewById(R.id.recycler_question_list);
        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerQuestionList.setLayoutManager(layoutManager);
        questionListModels = new ArrayList<>();

    }

    @Override
    public void onClick(View v)
    {
        if(v == imgStartDate)
        {
            setDate = getString(R.string.start_date_exam);
            showCalendar();
        }

        if(v == imgEndDate)
        {
            setDate = getString(R.string.end_date_exam);
            showCalendar();
        }

        if(v == imgResultDate)
        {
            setDate = getString(R.string.result_date);
            showCalendar();
        }

        if(v == btnAdd && totalQuestions > 0)
        {
            int i = questionListModels.size();

            if(i >= 0 && i < totalQuestions )
            {
                showMessageDialog(++i);
            }
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
            txtTotalQuestion.setText(inputTotalQuestion.getText().toString().trim());
            txtMaxMarks.setText(inputMaxMarks.getText().toString().trim());
            txtExamTime.setText(inputExamTime.getText().toString().trim());
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
        if(setDate.equals(getString(R.string.start_date_exam)))
        {
            int month1 = monthOfYear;
            String date1 = dayOfMonth + " " + DateTimeUtility.getMonthInString(month1) + " " + year;
            inputStartDate.setText(date1);
        }
        if(setDate.equals(getString(R.string.end_date_exam)))
        {
            int month1 = monthOfYear;
            String date1 = dayOfMonth + " " + DateTimeUtility.getMonthInString(month1) + " " + year;
            inputEndDate.setText(date1);
        }
        if(setDate.equals(getString(R.string.result_date)))
        {
            int month1 = monthOfYear;
            String date1 = dayOfMonth + " " + DateTimeUtility.getMonthInString(month1) + " " + year;
            inputResultDate.setText(date1);
        }
    }

    private void addToRecycler(int position, QuestionListModel model)
    {
        questionListModels.add(position, model);
        questionListAdapter = new QuestionListAdapter(mContext, questionListModels, "");
        recyclerQuestionList.setAdapter(questionListAdapter);
    }

    public void showMessageDialog(final int questionNo)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View promptView = layoutInflater.inflate(R.layout.dialog_add_questions, null);
        final android.app.AlertDialog alert = new android.app.AlertDialog.Builder(this).create();
        final TextView txtQuestionNo, txtUpload, txtSubQuestionA, txtSubQuestionB, txtSubQuestionC, txtSubQuestionD,
                txtSubQuestionE, txtSubQuestionF;
        final EditText edtQuestion, edtSubQueA, edtSubQueB, edtSubQueC, edtSubQueD, edtSubQueE, edtSubQueF;
        final LinearLayout linearSubQueA, linearSubQueB, linearSubQueC, linearSubQueD, linearSubQueE, linearSubQueF;
        TextView txtCrossA, txtCrossB, txtCrossC, txtCrossD, txtCrossE, txtCrossF;
        ImageView imgClose, imgPlus, imgUploaded1, imgUploaded2, imgUploaded3, imgUploaded4, imgUploaded5,imgUploaded6;
        Button btnAdd;

        txtQuestionNo = (TextView)promptView.findViewById(R.id.txt_question_no);
        txtQuestionNo.setText("Q."+questionNo);
        edtQuestion = (EditText)promptView.findViewById(R.id.edt_question);
        txtUpload = (TextView)promptView.findViewById(R.id.txt_uplaod);

        txtSubQuestionA = (TextView)promptView.findViewById(R.id.txt_sub_question_a);
        txtSubQuestionB = (TextView)promptView.findViewById(R.id.txt_sub_question_b);
        txtSubQuestionC = (TextView)promptView.findViewById(R.id.txt_sub_question_c);
        txtSubQuestionD = (TextView)promptView.findViewById(R.id.txt_sub_question_d);
        txtSubQuestionE = (TextView)promptView.findViewById(R.id.txt_sub_question_e);
        txtSubQuestionF = (TextView)promptView.findViewById(R.id.txt_sub_question_f);

        edtSubQueA = (EditText)promptView.findViewById(R.id.edt_sub_question_a);
        edtSubQueB = (EditText)promptView.findViewById(R.id.edt_sub_question_b);
        edtSubQueC = (EditText)promptView.findViewById(R.id.edt_sub_question_c);
        edtSubQueD = (EditText)promptView.findViewById(R.id.edt_sub_question_d);
        edtSubQueE = (EditText)promptView.findViewById(R.id.edt_sub_question_e);
        edtSubQueF = (EditText)promptView.findViewById(R.id.edt_sub_question_f);

        linearSubQueA = (LinearLayout)promptView.findViewById(R.id.linear_sub_question_a);
        linearSubQueB = (LinearLayout)promptView.findViewById(R.id.linear_sub_question_b);
        linearSubQueC = (LinearLayout)promptView.findViewById(R.id.linear_sub_question_c);
        linearSubQueD = (LinearLayout)promptView.findViewById(R.id.linear_sub_question_d);
        linearSubQueE = (LinearLayout)promptView.findViewById(R.id.linear_sub_question_e);
        linearSubQueF = (LinearLayout)promptView.findViewById(R.id.linear_sub_question_f);

        txtCrossA = (TextView)promptView.findViewById(R.id.txt_cross_a);
        txtCrossB = (TextView)promptView.findViewById(R.id.txt_cross_b);
        txtCrossC = (TextView)promptView.findViewById(R.id.txt_cross_c);
        txtCrossD = (TextView)promptView.findViewById(R.id.txt_cross_d);
        txtCrossE = (TextView)promptView.findViewById(R.id.txt_cross_e);
        txtCrossF = (TextView)promptView.findViewById(R.id.txt_cross_f);

        imgUploaded1 = (ImageView)promptView.findViewById(R.id.img_uploaded1);
        imgUploaded2 = (ImageView)promptView.findViewById(R.id.img_uploaded2);
        imgUploaded3 = (ImageView)promptView.findViewById(R.id.img_uploaded3);
        imgUploaded4 = (ImageView)promptView.findViewById(R.id.img_uploaded4);
        imgUploaded5 = (ImageView)promptView.findViewById(R.id.img_uploaded5);
        imgUploaded6 = (ImageView)promptView.findViewById(R.id.img_uploaded6);

        imgClose = (ImageView)promptView.findViewById(R.id.img_close);
        imgPlus = (ImageView)promptView.findViewById(R.id.img_plus);
        btnAdd = (Button)promptView.findViewById(R.id.btn_add);

        imgPlus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("sdhfofiosj","fosh"+addSubQue);
                if(addSubQue == 1 && !edtQuestion.getText().toString().trim().equals("")){
                    linearSubQueA.setVisibility(View.VISIBLE);
                    addSubQue = 2;
                }

                if(addSubQue == 2 && !edtSubQueA.getText().toString().trim().equals("")){
                    linearSubQueB.setVisibility(View.VISIBLE);
                    addSubQue = 3;
                }

                if(addSubQue == 3 && !edtSubQueB.getText().toString().trim().equals("")){
                    linearSubQueC.setVisibility(View.VISIBLE);
                    addSubQue = 4;
                }

                if(addSubQue == 4 && !edtSubQueC.getText().toString().trim().equals("")){
                    linearSubQueD.setVisibility(View.VISIBLE);
                    addSubQue = 5;
                }

                if(addSubQue == 5 && !edtSubQueD.getText().toString().trim().equals("")){
                    linearSubQueE.setVisibility(View.VISIBLE);
                    addSubQue = 6;
                }

                if(addSubQue == 6 && !edtSubQueE.getText().toString().trim().equals("")){
                    linearSubQueF.setVisibility(View.VISIBLE);
                    addSubQue = 1;
                }
            }
        });

        txtCrossA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearSubQueA.setVisibility(View.GONE);
                edtSubQueA.setText("");
                addSubQue = 1;
            }
        });
        txtCrossB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearSubQueB.setVisibility(View.GONE);
                edtSubQueB.setText("");
                addSubQue = 2;
            }
        });
        txtCrossC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearSubQueC.setVisibility(View.GONE);
                edtSubQueC.setText("");
                addSubQue = 3;
            }
        });
        txtCrossD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearSubQueD.setVisibility(View.GONE);
                edtSubQueD.setText("");
                addSubQue = 4;
            }
        });
        txtCrossE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearSubQueE.setVisibility(View.GONE);
                edtSubQueE.setText("");
                addSubQue = 5;
            }
        });
        txtCrossF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearSubQueF.setVisibility(View.GONE);
                edtSubQueF.setText("");
                addSubQue = 6;
            }
        });

        imgClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alert.dismiss();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Log.d("shgodhgohd","dsd"+edtQuestion.getText().toString().trim());
                if(edtQuestion.getText().toString().trim().equals(""))
                {

                }
                else
                {
                    QuestionListModel questionListModel = new QuestionListModel();

                    questionListModel.setQuestion(txtQuestionNo.getText().toString() + " "
                            + edtQuestion.getText().toString().trim());
                    if(!edtSubQueA.getText().toString().trim().equals("")) {
                        questionListModel.setSubQuestion1(txtSubQuestionA.getText().toString() + " "
                                + edtSubQueA.getText().toString().trim());
                    }
                    else{
                        questionListModel.setSubQuestion1("");
                    }
                    if(!edtSubQueB.getText().toString().trim().equals("")) {
                        questionListModel.setSubQuestion2(txtSubQuestionB.getText().toString() + " "
                                + edtSubQueB.getText().toString().trim());
                    }
                    else{
                        questionListModel.setSubQuestion2("");
                    }
                    if(!edtSubQueC.getText().toString().trim().equals("")) {
                        questionListModel.setSubQuestion3(txtSubQuestionC.getText().toString() + " "
                                + edtSubQueC.getText().toString().trim());
                    }
                    else{
                        questionListModel.setSubQuestion3("");
                    }
                    if(!edtSubQueD.getText().toString().trim().equals("")) {
                        questionListModel.setSubQuestion4(txtSubQuestionD.getText().toString() + " "
                                + edtSubQueD.getText().toString().trim());
                    }
                    else{
                        questionListModel.setSubQuestion4("");
                    }
                    if(!edtSubQueE.getText().toString().trim().equals("")) {
                        questionListModel.setSubQuestion5(txtSubQuestionE.getText().toString() + " "
                                + edtSubQueE.getText().toString().trim());
                    }
                    else{
                        questionListModel.setSubQuestion5("");
                    }
                    if(!edtSubQueF.getText().toString().trim().equals("")) {
                        questionListModel.setSubQuestion6(txtSubQuestionF.getText().toString() + " "
                                + edtSubQueF.getText().toString().trim());
                    }
                    else{
                        questionListModel.setSubQuestion6("");
                    }
                    addSubQue = 1;
                    alert.dismiss();
                    addToRecycler(questionNo -1 , questionListModel);
                }
            }
        });

        alert.setView(promptView);
        alert.setCancelable(false);
        alert.show();
    }
}
