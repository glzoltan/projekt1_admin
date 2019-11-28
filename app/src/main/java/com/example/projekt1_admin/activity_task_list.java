package com.example.projekt1_admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class activity_task_list extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview_tasks);
        new FirebaseDatabaseHelper().readTasks(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Fire_Task> tasks, List<String> keys) {
                new RecyclerView_Config().setConfig(mRecyclerView,activity_task_list.this,tasks,keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
}
