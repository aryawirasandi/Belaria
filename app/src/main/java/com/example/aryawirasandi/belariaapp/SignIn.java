package com.example.aryawirasandi.belariaapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aryawirasandi.belariaapp.Common.Common;
import com.example.aryawirasandi.belariaapp.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {
    EditText edtPhone, edtPassword;
    Button SignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigin);

        edtPhone = findViewById(R.id.edtPhone);
        edtPassword = findViewById(R.id.edtPassword);

        SignIn = findViewById(R.id.btnSignIn);

        // Callback Firebase

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(com.example.aryawirasandi.belariaapp.SignIn.this);
                mDialog.setMessage("Please Wait...");
                mDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // If user doesn't exist in firebase
                        if(dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                            // Trying getting information from firebase
                            mDialog.dismiss();
                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            user.setPhone(edtPhone.getText().toString()); //set Phone
                            if (user.getPassword().equals(edtPassword.getText().toString())) {
                                Intent homIntent = new Intent(SignIn.this, Home.class);
                                Common.currentUser = user;
                                startActivity(homIntent);
                                finish();
                                Toast.makeText(com.example.aryawirasandi.belariaapp.SignIn.this, "SignIN Success", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(com.example.aryawirasandi.belariaapp.SignIn.this, "SignIn Failed!!!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            mDialog.dismiss();
                            Toast.makeText(SignIn.this, "User Doesn't Exist", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
