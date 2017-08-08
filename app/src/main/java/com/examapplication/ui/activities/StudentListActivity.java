package com.examapplication.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.examapplication.R;
import com.examapplication.models.StudentListModel;
import com.examapplication.models.SubmittedExamModel;
import com.examapplication.ui.adapters.StudentListAdapter;
import com.examapplication.ui.adapters.SubmittedExamAdapter;

import java.util.ArrayList;

public class StudentListActivity extends ParentActivity implements View.OnClickListener
{

    private Context mContext;
    private Toolbar toolbar;

    private RecyclerView recyclerStudentList;
    private LinearLayoutManager layoutManager;
    private StudentListAdapter studentListAdapter;
    private StudentListModel studentListModel;

    private TextView txtTitle;
    private ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        mContext = this;
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        txtTitle = (TextView)findViewById(R.id.txt_name);
        txtTitle.setText(getString(R.string.student_list));
        imgBack = (ImageView)findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);

        recyclerStudentList = (RecyclerView)findViewById(R.id.recycler_student_list);
        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerStudentList.setLayoutManager(layoutManager);
        ArrayList<StudentListModel> studentListModels = new ArrayList<>();
        studentListModel = new StudentListModel();
        studentListAdapter = new StudentListAdapter(mContext, studentListModels, "");
        recyclerStudentList.setAdapter(studentListAdapter);
    }

    @Override
    public void onClick(View v)
    {
        if(v == imgBack)
        {
            finish();
        }
    }
}
