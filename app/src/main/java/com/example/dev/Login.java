package com.example.dev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.dev.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {

    ActivityLoginBinding binding;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dataBaseHelper=new DataBaseHelper(this);


        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= binding.loginEmail.getText().toString();
                String password= binding.loginPassword.getText().toString();

                if (email.equals("")||password.equals("")){
                    Toast.makeText(Login.this,"All fields are mandatory.",Toast.LENGTH_SHORT).show();}
                else{
                    Boolean checkCredentials=dataBaseHelper.checkEmailPassword(email,password);
                    if(checkCredentials==true){
                        Toast.makeText(Login.this,"Login Succesful",Toast.LENGTH_SHORT).show();

                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this,"İnvalid Credentials",Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });
        binding.signUpRedirectTexr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent (Login.this,SignUp.class);
                startActivity(intent);
            }
        });
    }
}