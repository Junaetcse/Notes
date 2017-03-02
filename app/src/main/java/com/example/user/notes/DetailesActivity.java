package com.example.user.notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DetailesActivity extends AppCompatActivity {
    DatabaseHandler handler;
    List<DatabaseModel> dblist;
    int position;
TextView viewTitle,viewDate,viewSubject,viewdescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailes);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        position=bundle.getInt("position");
        viewTitle= (TextView) findViewById(R.id.viewTitle);
        viewDate= (TextView) findViewById(R.id.viewDate);
        viewSubject= (TextView) findViewById(R.id.viewSubject);
        viewdescription= (TextView) findViewById(R.id.viewDetails);
        handler=new DatabaseHandler(this);
        dblist=new ArrayList<DatabaseModel>();
        dblist=handler.getInformations();
        if (dblist.size()>0){

            String vTitle= dblist.get(position).getTitle();
            String vDate= dblist.get(position).getDate();
            String vSubject= dblist.get(position).getSubject();
            String vDescriptiom= dblist.get(position).getDetails();
            viewTitle.setText(vTitle);
            viewDate.setText(vDate);
            viewSubject.setText(vSubject);
            viewdescription.setText(vDescriptiom);



        }
    }
}
