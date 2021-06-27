package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText emailEditText;
    private EditText passEditText;
    private EditText repassEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText= findViewById(R.id.emailEditText);
        passEditText= findViewById(R.id.passEditText);
        repassEditText= findViewById(R.id.repassEditText);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        Log.i("User:",""+currentUser);
    }


    public void createUserWithEmailAndPassword(String email,String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("EXITO", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);

                            //mandar una nueva vista
                            startActivity(new Intent(MainActivity.this,HomeActivity.class));
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("ERROR", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Datos Erroneos.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }


    public void submitRegister(View view){

        String email = emailEditText.getText().toString();
        String password = passEditText.getText().toString();
        String re_password = repassEditText.getText().toString();

        if(!email.isEmpty()&&!password.isEmpty()&&!re_password.isEmpty()){

            if(password.equals(re_password)){
                
                if(password.length()>5){
                    createUserWithEmailAndPassword(email,password);
                }else{
                    Toast.makeText(this, "La contraseña debe ser minimo 6 digitoas", Toast.LENGTH_SHORT).show();
                }

            }else {
                Toast.makeText(this, "Contraseñas no coinciden", Toast.LENGTH_SHORT).show();

            }


        }else{
            Toast.makeText(this,"Complete los Campos",Toast.LENGTH_SHORT).show();
        }

    }


    public void buttonTengoCuenta(View view){
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }

}