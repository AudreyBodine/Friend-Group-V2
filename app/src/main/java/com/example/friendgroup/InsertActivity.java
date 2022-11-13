package com.example.friendgroup;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InsertActivity extends AppCompatActivity {

    private DatabaseManager dbManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        setContentView(R.layout.activity_insert);
    }

    public void insert(View view) {

        // Retrieve name and email

        Log.w("InsertActivity", "Insert Button Pushed");
        EditText firstET = findViewById(R.id.first_nameET);
        EditText lastET = findViewById(R.id.last_nameET);
        EditText emailET = findViewById(R.id.et_1);
        String fName = firstET.getText().toString();
        String lName = lastET.getText().toString();
        String emailString = emailET.getText().toString();

        // Insert into database
            //String email = String.valueOf(emailString);
            Friends friend = new Friends(0, fName, lName, emailString);
            dbManager.insert(friend);
            Toast.makeText(this, "Friends Added", Toast.LENGTH_SHORT).show();

        // Clear data
        firstET.setText("");
        lastET.setText("");
        emailET.setText("");
    }

    public void goBack(View view) {
        this.finish();

    }
}


