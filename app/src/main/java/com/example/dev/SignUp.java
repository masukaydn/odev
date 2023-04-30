package com.example.dev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.dev.databinding.ActivitySignUpBinding;

public class SignUp extends AppCompatActivity {

    ActivitySignUpBinding binding;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        dataBaseHelper=new DataBaseHelper(this);

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= binding.signupEmail.getText().toString();
                String password= binding.signupPassword.getText().toString();
                String confirmPassowrd= binding.signupConfirm.getText().toString();

                if (email.equals("")||password.equals("")||confirmPassowrd.equals(""))
                    Toast.makeText(SignUp.this,"All fields are mandatory.",Toast.LENGTH_SHORT).show();
                else{
                    if(password.equals(confirmPassowrd)){
                        Boolean checkEmail= dataBaseHelper.checkEmail(email);
                        if(checkEmail==false){

                            Boolean insert=dataBaseHelper.insertData(email,password);

                            if(true){
                                Toast.makeText(SignUp.this,"Signup Succesful!",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Login.class);
                                startActivity(intent);

                            }else{
                                Toast.makeText(SignUp.this,"Signup Failed!",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(SignUp.this,"User already exists!",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SignUp.this,"Invalid Password",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        binding.loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });

    }
}