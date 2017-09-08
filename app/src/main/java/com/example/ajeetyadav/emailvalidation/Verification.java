package com.example.ajeetyadav.emailvalidation;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Verification extends AppCompatActivity  {
EditText d []=new EditText[6];
    final Context context = this;
    TextView tv,resend;
    private boolean isFormatting;
    private boolean deletingHyphen;
    private int hyphenStart;
    private boolean deletingBackward;
     int otp=0;
    String email1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }*/
        setTitle("");
        getSupportActionBar().setIcon(android.R.drawable.sym_action_email);
       getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_verification);
        Intent mIntent = getIntent();
        if (mIntent != null) {

             email1 = getIntent().getStringExtra("EmailID");
            otp = mIntent.getIntExtra("key", 0);

        }

        d[0]=(EditText)findViewById(R.id.d1);
        d[1]=(EditText)findViewById(R.id.d2);
        d[2]=(EditText)findViewById(R.id.d3);
        d[3]=(EditText)findViewById(R.id.d4);
        d[4]=(EditText)findViewById(R.id.d5);
        d[5]=(EditText)findViewById(R.id.d6);
        tv=(TextView)findViewById(R.id.tv);
        resend=(TextView)findViewById(R.id.resend);
        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title
                alertDialogBuilder.setTitle("Resend Mail");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Do you want tto send mail again?")
                        .setCancelable(false)
                        .setPositiveButton("Resend",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                sendEMail(email1,otp);

                            }
                        })
                        .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }

        });
        for(int i=0;i<6;i++)
        d[i].setTransformationMethod(new PasswordTransformationMethod());

        d[0].requestFocus();
        d[0].setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(final View v,
                                      final boolean hasFocus) {


                if (hasFocus) {
                    final InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    // only will trigger it if no physical keyboard is
                    // open
                    mgr.showSoftInput(d[0],
                            InputMethodManager.SHOW_IMPLICIT);
                }

            }
        });

        d[0].setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(final View v, final int keyCode,
                                 final KeyEvent event) {


                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_DEL) {
                        d[0].requestFocus();
                    } else {
                        d[1].requestFocus();
                    }
                }
                return false;
            }
        });

        d[1].setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(final View v, final int keyCode,
                                 final KeyEvent event) {


                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_DEL) {
                        d[0].requestFocus();
                    } else {
                        d[2].requestFocus();
                    }
                }
                return false;
            }
        });
        d[2].setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(final View v, final int keyCode,
                                 final KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_DEL) {
                        d[1].requestFocus();
                    } else {
                        d[3].requestFocus();
                    }

                }
                return false;
            }
        });

        d[3].setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(final View v, final int keyCode,
                                 final KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_DEL) {
                        d[2].requestFocus();
                    }
                    else {
                        d[4].requestFocus();
                    }

                }

                return false;

            }
        });
        d[4].setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(final View v, final int keyCode,
                                 final KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_DEL) {
                        d[3].requestFocus();
                    } else {
                        d[5].requestFocus();
                    }

                }
                return false;
            }
        });
        d[5].setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(final View v, final int keyCode,
                                 final KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_DEL) {
                        d[4].requestFocus();
                    }
                    else{


                        d[5].addTextChangedListener(new TextWatcher()
                        {
                            @Override
                            public void afterTextChanged(Editable mEdit)
                            {
                                String st=d[0].getText().toString()+d[1].getText().toString()+
                                        d[2].getText().toString()+d[3].getText().toString()+d[4].getText().toString();
                                 st += mEdit.toString();
                                int result = Integer.parseInt(st);
                                if(result==otp){
                                   // Toast.makeText(context,st+" successfully login "+otp, Toast.LENGTH_SHORT).show();
                                    Intent i=new Intent(Verification.this,Success.class);
                                    startActivity(i);
                                    finish();
                                    }
                                else{

                                     Toast.makeText(context,"Wrong OTP,Please try again", Toast.LENGTH_LONG).show();}
                            }

                            public void beforeTextChanged(CharSequence s, int start, int count, int after){

                            }

                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                            }
                        });







                    }

                }

                return false;
            }
        });















    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

            int itemID = item.getItemId();

            if(itemID == android.R.id.home) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                this.finish();
            }

            return super.onOptionsItemSelected(item);

    }

    private void sendEMail(String text,int otp) {

        SendMail sm = new SendMail(this, text,otp);
        sm.execute();
    }

}
