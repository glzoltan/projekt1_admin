package com.example.projekt1_admin;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceTasks;
    private List<Fire_Task> tasks=new ArrayList<>();
    public static final String SHARED_PREFS="sharedPrefs";
    String context;

    public interface DataStatus{
        void DataIsLoaded(List<Fire_Task> tasks,List<String>keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }
    public FirebaseDatabaseHelper(String context) {
        mDatabase=FirebaseDatabase.getInstance();
        mReferenceTasks=mDatabase.getReference("task");
        this.context=context;

    }
    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
    }

    public void readTasks(final DataStatus dataStatus){
        mReferenceTasks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tasks.clear();
                List<String> keys=new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Fire_Task task = keyNode.getValue(Fire_Task.class);

                    //SharedPreferences sharedPreferences = getPrefs("groupcode",)
                    if(task.getGROUP().equals(context)==true) {
                        tasks.add(task);


                    }
                }
                dataStatus.DataIsLoaded(tasks,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
