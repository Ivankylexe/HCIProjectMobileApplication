package com.example.hciproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, contact, dob, age, email, occupation, status, ethnicity, address, gender;
    Button insert, update, delete, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //variables
        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        dob = findViewById(R.id.dob);
        age = findViewById(R.id.age);
        email = findViewById(R.id.email);
        occupation = findViewById(R.id.occupation);
        status = findViewById(R.id.status);
        ethnicity = findViewById(R.id.ethnicity);
        address = findViewById(R.id.address);
        gender = findViewById(R.id.gender);

        //buttons
        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();
                String ageTXT = age.getText().toString();
                String emailTXT = email.getText().toString();
                String occupationTXT = occupation.getText().toString();
                String statusTXT = status.getText().toString();
                String ethnicityTXT = ethnicity.getText().toString();
                String addressTXT = address.getText().toString();
                String genderTXT = gender.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(nameTXT, contactTXT, dobTXT, ageTXT, emailTXT, occupationTXT, statusTXT, ethnicityTXT, addressTXT, genderTXT);
                if(checkinsertdata==true)
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();
                String ageTXT = age.getText().toString();
                String emailTXT = email.getText().toString();
                String occupationTXT = occupation.getText().toString();
                String statusTXT = status.getText().toString();
                String ethnicityTXT = ethnicity.getText().toString();
                String addressTXT = address.getText().toString();
                String genderTXT = gender.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(nameTXT, contactTXT, dobTXT, ageTXT, emailTXT, occupationTXT, statusTXT, ethnicityTXT, addressTXT, genderTXT);
                if(checkupdatedata==true)
                    Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                Boolean checkudeletedata = DB.deletedata(nameTXT);
                if(checkudeletedata==true)
                    Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name: "+res.getString(0)+"\n");
                    buffer.append("Contact: "+res.getString(1)+"\n");
                    buffer.append("Date of Birth: "+res.getString(2)+"\n");
                    buffer.append("Age: "+res.getString(3)+"\n");
                    buffer.append("Email: "+res.getString(4)+"\n");
                    buffer.append("Occupation: "+res.getString(5)+"\n");
                    buffer.append("Status: "+res.getString(6)+"\n");
                    buffer.append("Ethnicity: "+res.getString(7)+"\n");
                    buffer.append("Address: "+res.getString(8)+"\n");
                    buffer.append("Gender: "+res.getString(9)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }}
