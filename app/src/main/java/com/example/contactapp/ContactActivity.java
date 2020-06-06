package com.example.contactapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import ktpackage.*;

public class ContactActivity extends AppCompatActivity {

    private ContactData myContacts = MainActivity.MyContacts;
    private ContactModel eContact;
    EditText firstName;
    EditText lastName;
    EditText eMail;
    ImageView photoView;
    Button btnSave;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Intent intent = getIntent();
        eContact = myContacts.getById(intent.getIntExtra("ID", -1));

        firstName = (EditText)findViewById(R.id.first_name);
        lastName = (EditText)findViewById(R.id.last_name);
        eMail = (EditText)findViewById(R.id.mail);
        photoView = (ImageView)findViewById(R.id.photo);
        btnSave = (Button)findViewById(R.id.action_save);
        btnCancel = (Button)findViewById(R.id.action_cancel);

        firstName.setText(eContact.getFirstName());
        lastName.setText(eContact.getLastName());
        eMail.setText(eContact.getMail());

        int img_id = getApplication().getApplicationContext().getResources().getIdentifier(eContact.getPhoto(),
                "mipmap",
                getApplication().getApplicationContext().getPackageName());
        photoView.setBackground( getApplicationContext().getResources().getDrawable(img_id));

        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                eContact.setFirstName(firstName.getText().toString());
                eContact.setLastName(lastName.getText().toString());
                eContact.setMail(eMail.getText().toString());

                myContacts.update(eContact);

                setResult(RESULT_OK, null);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_CANCELED, null);
                finish();
            }
        });
    }
}