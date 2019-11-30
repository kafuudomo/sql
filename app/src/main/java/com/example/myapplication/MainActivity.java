package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Button addButton;
    private ContactsDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();
        setListeners();
        setUpDatabse();
        setUpAdapter();


    }


    @Override
    protected void onResume() {
        super.onResume();
        Adapter adapter=(Adapter) listView.getAdapter();
        adapter.updateData(dbHelper.getAllContacts());
    }

    private void findView(){


        listView=findViewById(R.id.listView);
        addButton=findViewById(R.id.addBtn);





    }

    private void setListeners(){

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i);

            }
        });

    }

    private void setUpDatabse(){

            dbHelper = new ContactsDBHelper(this);

    }

    private void setUpAdapter(){
        ArrayList<Contact> contactList=new ArrayList<>();
        contactList=dbHelper.getAllContacts();

        Adapter a=new Adapter(this,contactList);
        listView.setAdapter(a);


    }
}
