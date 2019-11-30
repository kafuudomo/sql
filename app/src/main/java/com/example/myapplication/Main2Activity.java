package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    private Button btnSave;
    private EditText edtName,edtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        findViews();
        setListeners();


    }

    private void findViews(){

        btnSave=findViewById(R.id.save);
        edtName=findViewById(R.id.name);
        edtPhone=findViewById(R.id.phoneNumber);

    }

    private void setListeners(){

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtName.getText().toString().isEmpty() && !edtPhone.getText().toString().isEmpty()){

                    ContactsDBHelper dbHelper=new ContactsDBHelper(Main2Activity.this);
                    Contact contact=new Contact();
                    contact.setName(edtName.getText().toString());
                    contact.setPhoneNumber(edtPhone.getText().toString());
                    dbHelper.insertContact(contact);
                    Main2Activity.this.finish();
                }else{

                    if(edtName.getText().toString().isEmpty()){

                        edtName.setError("Cannot be empty");
                    }

                    if(edtPhone.getText().toString().isEmpty()){

                        edtPhone.setError("Cannot be empty");
                    }

                }
            }
        });

    }
}
