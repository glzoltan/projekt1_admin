package com.example.projekt1_admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    DatabaseReference db;
    public static final String TAG = "YOUR-TAG-NAME";
    public static final String SHARED_PREFS="sharedPrefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void login_check(View view){
        EditText et1=(EditText)findViewById(R.id.login_name);
        EditText et2=(EditText)findViewById(R.id.group_code);
        String name =et1.getText().toString();
        String code = et2.getText().toString();
        if(name.isEmpty()){
            Toast.makeText(this, "Name is empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(code.isEmpty()){
            Toast.makeText(this, "Code is empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        db= FirebaseDatabase.getInstance().getReference("groups");
        db.child(code).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                if(dataSnapshot.exists()==false)
                {

                    Toast.makeText(getApplicationContext() , "Group is not exist!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    EditText et1=(EditText)findViewById(R.id.login_name);
                    EditText et2=(EditText)findViewById(R.id.group_code);
                    String name =et1.getText().toString();
                    String code = et2.getText().toString();
                    db = FirebaseDatabase.getInstance().getReference("admins");
                    Fire_Admin admin= new Fire_Admin(name);
                    db.child(name).setValue(name);
                    SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("groupcode",code);

                    Intent intent = new Intent(getApplicationContext(), TaskActivity.class);
                    intent.putExtra("elso", code);

                    startActivity(intent);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }

    public void create_group(View view) {
        Intent intent = new Intent(this, Create_grp.class);
        startActivity(intent);
    }
    public void fin()
    {
        return;
    }
}
