package com.example.roomproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button addContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.list);
        addContact=findViewById(R.id.addcontact);

        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });
        new GetContacts().execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact selected_contact= (Contact) parent.getItemAtPosition(position);
                Intent intent=new Intent(MainActivity.this,UpdateActivity.class);
                intent.putExtra("id",selected_contact.getId());
                startActivity(intent);
            }
        });

    }

    public class GetContacts extends AsyncTask<Void,Void, List<Contact>>{

        @Override
        protected List<Contact> doInBackground(Void... voids) {

            DatabaseClient databaseClient=new DatabaseClient(getApplicationContext());
            List<Contact> contacts=databaseClient.getAppDatabase().databaseDAO().getAll();
            return contacts;
        }

        @Override
        protected void onPostExecute(List<Contact> contacts) {
            super.onPostExecute(contacts);
            ContactAdpter adpter = new ContactAdpter(MainActivity.this,contacts);
            listView.setAdapter(adpter);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
       new GetContacts().execute();

    }
}
