package com.example.birch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;         // Firebase Authentication
    private DatabaseReference mDatabaseRef;     // Real time Firebase Connection
    private EditText mEtEmail, mEtPassword,mEtPasswordConf,mPhoneNum;     // Input for email and password
    private Button mBtRegister;                 // Input from Register Button
    private CheckBox mCheckTerm, mCheckPriv;

    // Function to encode the email since Realtime DB doesn't support periods.
    static String encodeEmail(String user) {
        return user.replace(".",",");
    }

    // Function to decode the email.
    static String decodeEmail(String user) {
        return user.replace(",", ".");
    }

    // Function to check if an email is of valid type.
    boolean validEmail(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regiester);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://birch-finanancial-literacy-app-default-rtdb.firebaseio.com/");

        mEtEmail = findViewById(R.id.et_email);
        mEtPassword = findViewById(R.id.et_password);
        mEtPasswordConf = findViewById(R.id.et_passwordConf);
        mBtRegister = findViewById(R.id.bt_create);
        mCheckTerm = findViewById(R.id.check_terms);
        mCheckPriv = findViewById(R.id.check_privacy);
        mPhoneNum = findViewById(R.id.et_phone);

        mBtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Registration process (app >> database)

                String ipt_Email = encodeEmail(mEtEmail.getText().toString());
                String ipt_Pwd = mEtPassword.getText().toString();
                String ipt_PwdConf = mEtPasswordConf.getText().toString();
                // String ipt_Phone = mPhoneNum.getText().toString();
                // String ipt_CompletePhoneNum = "+" + mCCP.getFullNumber() + ipt_Phone;

                // ALL CHECKS HERE
                /**
                 * If I missed any checks, make an else if loop to add another check before the else loop
                 * - Rikki Martin
                 */

                // Checks if fields are empty
                if(ipt_Email.isEmpty() || ipt_Pwd.isEmpty() || ipt_PwdConf.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please fill all the fields.", Toast.LENGTH_SHORT).show();
                }

                // Checks if email is valid format
                if(!validEmail(decodeEmail(ipt_Email))) {
                    Toast.makeText(RegisterActivity.this, "Please enter valid email.", Toast.LENGTH_SHORT).show();
                }

                // Checks if passwords are matching
                else if(!ipt_Pwd.equals(ipt_PwdConf)) {
                    Toast.makeText(RegisterActivity.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                }

                // Checks if password contains at least 8 characters
                else if(ipt_Pwd.length() < 8) {
                    Toast.makeText(RegisterActivity.this, "Password must be more than 8 characters.", Toast.LENGTH_SHORT).show();
                }

                // Checks if the terms of service checkbox is checked
                else if(!mCheckTerm.isChecked()) {
                    Toast.makeText(RegisterActivity.this, "You must agree to the Terms and Conditions.", Toast.LENGTH_SHORT).show();
                }

                // Checks if privacy checkbox is checked
                else if(!mCheckPriv.isChecked()) {
                    Toast.makeText(RegisterActivity.this, "You must agree to the Privacy Policy. ", Toast.LENGTH_SHORT).show();
                }

                // If all checks are passed besides email check, database is accessed here
                else {
                    // References the Realtime DB. addListenerSingleValueEvent is used to check if child (email) already exists and if it doesn't then it registers the user.
                    mDatabaseRef.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            // Check if user is registered.
                            if(snapshot.hasChild(ipt_Email)) {
                                Toast.makeText(RegisterActivity.this, "User already exists! Redirecting to login page.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                            // Registers the user and then moves to login page.
                            else {
                                // mDatabaseRef.child("users").child(ipt_Email).child("password").setValue(ipt_Pwd);
                                mDatabaseRef.child("users").child(ipt_Email).child("password").setValue(ipt_Pwd);
                                mDatabaseRef.child("users").child(ipt_Email).child("accessToken").setValue("");

                                Toast.makeText(RegisterActivity.this, "User registered successfully.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });
    }


}