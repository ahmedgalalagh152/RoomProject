package com.example.roomproject;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    EditText textName;
    EditText textPhone;
    Button add;
    String name;
    String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        textName=findViewById(R.id.name);
        textPhone=findViewById(R.id.phone);
        add=findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=textName.getText().toString();
                phone=textPhone.getText().toString();

                new SaveTask().execute();
            }
        });

    }


    public class SaveTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            Contact contact=new Contact();
            contact.setName(name);
            contact.setPhone(phone);

            DatabaseClient databaseClient=new DatabaseClient(getApplicationContext());
            databaseClient.getAppDatabase().databaseDAO().insertAll(contact);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            finish();
            Toast.makeText(AddActivity.this,"Saved",Toast.LENGTH_LONG).show();
        }
    }
}
