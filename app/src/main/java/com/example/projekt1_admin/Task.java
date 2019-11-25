package com.example.projekt1_admin;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Task.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Task#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Task extends Fragment {

    TextView group;
    EditText task;
    Switch switch1;
    Button add;
    FirebaseDatabase db;
    DatabaseReference dbtask;
    public static final String TAG = "YOUR-TAG-NAME";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_task, container, false);
        group=(TextView)v.findViewById(R.id.textView2);
        add=(Button)v.findViewById(R.id.button);
        task=(EditText)v.findViewById(R.id.editText);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(task.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Name is empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                addTask();
                Toast.makeText(getActivity(), "TASK IS ADDED!", Toast.LENGTH_SHORT).show();
            }
        });
        readfromdb();
        return v;
    }
    public void addTask(){
        dbtask = FirebaseDatabase.getInstance().getReference("task");
        String id=dbtask.push().getKey();
        group=(TextView) getView().findViewById(R.id.textView2);
        task=(EditText) getView().findViewById(R.id.editText);
        switch1=(Switch)getView().findViewById(R.id.switch1);
        String fgroup=group.getText().toString();
        String ftask=task.getText().toString();
        String fstatus;
        if(switch1.isChecked()==true)
        {
             fstatus ="activ";
        }
        else
        {
             fstatus ="inactiv";
        }
        Fire_Task Task=new Fire_Task(id,fgroup,ftask,fstatus);
        dbtask.child(id).setValue(Task);
    }
    public void readfromdb(){
        db=FirebaseDatabase.getInstance();
        dbtask=db.getReference("groups");
        Intent intent = getActivity().getIntent();
        String message = intent.getStringExtra("elso");
        dbtask.child(message).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                group=(TextView)getActivity().findViewById(R.id.textView2);
                group.setText(value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }


}
