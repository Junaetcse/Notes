package com.example.user.notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class ModifyActivity extends AppCompatActivity {
    DatabaseHandler handler;
    List<DatabaseModel> dblist;
    int position;
    EditText modifyTitle,modifyDate,modifySubject,modifyDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        position = bundle.getInt("uposition");

        modifyTitle= (EditText) findViewById(R.id.modifyTitle);
        modifyDate= (EditText) findViewById(R.id.modifyDate);
        modifySubject= (EditText) findViewById(R.id.modifySubject);
        modifyDescription= (EditText) findViewById(R.id.modifyDescription);

        handler = new DatabaseHandler(this);
        dblist = new ArrayList<DatabaseModel>();
        dblist = handler.getInformations();
        if (dblist.size() > 0) {

            String mTitle = dblist.get(position).getTitle();
            String mDate = dblist.get(position).getDate();
            String mSubject = dblist.get(position).getSubject();
            String mDescription = dblist.get(position).getDetails();

            modifyTitle.setText(mTitle);
            modifyDate.setText(mDate);
            modifySubject.setText(mSubject);
            modifyDescription.setText(mDescription);
        }

    }

    public void changeData(View view)
    {
        String cTitle = modifyTitle.getText().toString();
        String cDate = modifyDate.getText().toString();
        String cSubject = modifySubject.getText().toString();
        String cDetails = modifyDescription.getText().toString();

        handler.updateInformation((position + 1), cTitle, cDate, cSubject, cDetails);
        finish();
    }

    public void deleteData(View view)
    {
        handler.deleteInfo((position+1));


        Intent intent1=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent1);
        finish();
    }

}
