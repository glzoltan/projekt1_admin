package com.example.projekt1_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Create_grp extends AppCompatActivity {
    DatabaseReference db;
    EditText name,code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_grp);
    }
    public void add(View view){
        name=(EditText)findViewById(R.id.editText2);
        code=(EditText)findViewById(R.id.editText3);
        String fname=name.getText().toString();
        String fcode=code.getText().toString();
        db = FirebaseDatabase.getInstance().getReference("groups");
        Fire_Group group = new Fire_Group(fname,fcode);
        db.child(fcode).setValue(fname);
        Toast.makeText(this, "GROUP IS CREATED!", Toast.LENGTH_SHORT).show();
        back_to_main(view);
    }
    public void back_to_main(View view){
        finish();
    }
}
