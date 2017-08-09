package com.examapplication.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.examapplication.R;
import com.examapplication.models.DemoModel1;
import com.examapplication.models.DemoModel2;
import com.examapplication.ui.adapters.RecyclerViewDataAdapter;

import java.util.ArrayList;

public class  DemoActivity extends AppCompatActivity {

    private Toolbar toolbar;


    ArrayList<DemoModel2> allSampleData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        allSampleData = new ArrayList<DemoModel2>();

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitle("G PlayStore");

        }


        createDummyData();


        RecyclerView my_recycler_view = (RecyclerView) findViewById(R.id.my_recycler_view);

        my_recycler_view.setHasFixedSize(true);

        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(this, allSampleData);

        my_recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        my_recycler_view.setAdapter(adapter);


    }

    public void createDummyData() {
        for (int i = 1; i <= 5; i++) {

            DemoModel2 dm = new DemoModel2();

            dm.setHeaderTitle("Section " + i);

            ArrayList<DemoModel1> singleItem = new ArrayList<DemoModel1>();
            for (int j = 0; j <= 1; j++) {
                singleItem.add(new DemoModel1("Item " + j, "URL " + j));
            }

            dm.setAllItemsInSection(singleItem);

            allSampleData.add(dm);

        }
    }
}