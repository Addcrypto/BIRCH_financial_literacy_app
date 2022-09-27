package com.example.birch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;         // Firebase Authentication
    private DatabaseReference mDatabaseRef;     // Real time Firebase Connection
    private EditText mEtEmail, mEtPassword;     // Input for email and password
    private Button bt_register, bt_login;

    // Function to encode the email since Realtime DB doesn't support periods.
    static String encodeEmail(String user) {
        return user.replace(".",",");
    }

    // Function to decode the email.
    static String decodeEmail(String user) {
        return user.replace(",", ".");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://birch-finanancial-literacy-app-default-rtdb.firebaseio.com/");

        mEtEmail = findViewById(R.id.et_inputEmail);
        mEtPassword = findViewById(R.id.et_inputPassword);

        //Login Button (Authentication Request)
        bt_login = findViewById(R.id.bt_login);
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ipt_Email = encodeEmail(mEtEmail.getText().toString());
                String ipt_Pwd = mEtPassword.getText().toString();

                // ALL CHECKS HERE
                /**
                 * If I missed any checks, make an else if loop to add another check before the else loop
                 * - Rikki Martin
                 */

                // Checks if fields are empty
                if(ipt_Email.isEmpty() || ipt_Pwd.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please fill all the fields.", Toast.LENGTH_SHORT).show();
                }
                else {
                    mDatabaseRef.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            // Check if user exists in Realtime DB
                            if(snapshot.hasChild(ipt_Email)) {
                                // If user exists, fetch the password.
                                final String fetchPassword = snapshot.child(ipt_Email).child("password").getValue(String.class);

                                // Match password with database then login if it matches.
                                if(fetchPassword.equals(ipt_Pwd)) {
                                    Toast.makeText(LoginActivity.this, "Successful login!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }

                                // Password does not match.
                                else {
                                    Toast.makeText(LoginActivity.this, "Email/password is invalid.", Toast.LENGTH_SHORT).show();
                                }
                            }

                            // User with this email does not exist.
                            else {
                                Toast.makeText(LoginActivity.this, "Email/password is invalid.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        // Move to Registration Screen
        bt_register = findViewById(R.id.bt_register);
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }


}