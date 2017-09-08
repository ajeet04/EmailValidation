package com.example.ajeetyadav.emailvalidation;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private Button next;
    private EditText email;
    Context context=null;
    ProgressDialog pg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        setContentView(R.layout.activity_main);



        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE|WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        next=(Button)findViewById(R.id.button);
        final TextInputLayout tilEmail = (TextInputLayout) findViewById(R.id.tilEmail);

        email=(EditText)findViewById(R.id.email);
        email.setHint("Email");

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rn = new Random();
                int max=999999;
                int min=100000;
                int range = max - min + 1;
                final int otp =  rn.nextInt(range) + min;

                String s=email.getText().toString().trim();
                if(s.isEmpty()|| !isEmail(s)){
                    tilEmail.setError("Invalid email id");

                }



                else{
                    sendEMail(email.getText().toString(),otp);
                    final Intent mainIntent = new Intent(getApplicationContext(), Verification.class);
                    mainIntent.putExtra("key",otp);
                    mainIntent.putExtra("EmailID",s);
                    startActivity(mainIntent);
                    finish();

                }


            }
        });





    }



    private void sendEMail(String text,int otp) {

        SendMail sm = new SendMail(this, text,otp);
        sm.execute();
    }
    public static boolean isEmail(String s) {
        {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(s);
            return matcher.matches();
        }
    }


    }




