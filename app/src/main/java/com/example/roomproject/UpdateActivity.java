package com.example.roomproject;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {
    Button update;
    EditText editName,editPhone;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        update=findViewById(R.id.update);
        editName=findViewById(R.id.name);
        editPhone=findViewById(R.id.phone);
        id=getIntent().getIntExtra("id",0);
        new GetContact().execute();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=editName.getText().toString();
                String phone=editPhone.getText().toString();
                Contact newcontact =new Contact();
                newcontact.setName(name);
                newcontact.setPhone(phone);
                newcontact.setId(id);
                new UpdateContact().execute(newcontact);

            }
        });

    }
    public class GetContact extends AsyncTask<Void,Void,Contact>{

        @Override
        protected Contact doInBackground(Void... voids) {
            DatabaseClient databaseClient=new DatabaseClient(getApplicationContext());
            Contact contact=databaseClient.getAppDatabase().databaseDAO().getContact(id);
            return contact;
        }

        @Override
        protected void onPostExecute(Contact contact) {
            super.onPostExecute(contact);
            editName.setText(contact.getName());
            editPhone.setText(contact.getPhone());
        }
    }
    public class UpdateContact extends AsyncTask<Contact,Void,Void>{

        @Override
        protected Void doInBackground(Contact... contact) {
            DatabaseClient databaseClient=new DatabaseClient(getApplicationContext());
            databaseClient.getAppDatabase().databaseDAO().update(contact[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            finish();
        }
    }
    public class DeleteContact extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            DatabaseClient databaseClient=new DatabaseClient(getApplicationContext());
            databaseClient.getAppDatabase().databaseDAO().delete(databaseClient.getAppDatabase().databaseDAO().getContact(id));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                ShowAlert();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
   private void ShowAlert(){
       AlertDialog.Builder builder=new AlertDialog.Builder(this);
       builder.setTitle("confirmation")
               .setMessage("Are you sure")
               .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       new DeleteContact().execute();
                       finish();
                   }
               }).setNegativeButton("no ", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               dialog.dismiss();
           }
       });
        AlertDialog dialog=builder.create();
        dialog.show();
    }
}
