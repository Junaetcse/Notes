package com.example.user.notes;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class InsertNotes extends AppCompatActivity {
    EditText edtTitle, edtSubject, edtDescription;
    static Button btnDate;
    Button btnAdd;
    DatabaseHandler dbHelper;
    List<DatabaseModel> dbList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_notes);
        edtTitle = (EditText) findViewById(R.id.edtTitle);
        edtSubject = (EditText) findViewById(R.id.edtSubject);
        edtDescription = (EditText) findViewById(R.id.edtDescription);
        btnDate = (Button) findViewById(R.id.btnDate);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment df = new DatePickerFragment();
                df.show(getSupportFragmentManager(), "datepicker");
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addTitle=edtTitle.getText().toString();
                Date sDate = null;
                try {
                    sDate = new SimpleDateFormat("").parse(btnDate.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String addSubject=edtSubject.getText().toString();
                String addDescription=edtDescription.getText().toString();
                dbHelper = new DatabaseHandler(InsertNotes.this);
                dbHelper.insertInformation(addTitle, sDate, addSubject, addDescription);
                edtTitle.setText("");
                edtSubject.setText("");
                edtDescription.setText("");
                btnDate.setText("");
                Toast.makeText(InsertNotes.this, " successfully inserted", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(InsertNotes.this,MainActivity.class);

                startActivity(intent);
                finish();

            }
        });
    }


    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);


            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            btnDate.setText(day + "/" + month + 1 + "/" + year);
        }
    }
}
