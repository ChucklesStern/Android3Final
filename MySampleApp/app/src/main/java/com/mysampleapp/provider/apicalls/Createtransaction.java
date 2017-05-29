package com.mysampleapp.provider.apicalls;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by root on 5/29/17.
 */

public class Createtransaction extends AppCompatActivity {
    /*
    @BindView(R.id.transaction_description)
    EditText Description;
    @BindView(R.id.transaction_amount) EditText Amount;
    @BindView(R.id.btn_signup)
    Button signUp;
*/
    //interface for callback
    public interface AsyncResponse {
        void processFinish(String output);
    }

/*
    String responseString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createtransaction);
        ButterKnife.bind(this);

        signUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    @OnClick(R.id.getAllTransaction)
    public void getAllTransaction(View view) {
        Intent intent = new Intent(this, getAllTransactions.class);
        startActivity(intent);
    }

    public void login() {

        if (validate() == true) {
            String Description_ = Description.getText().toString();
            String preAmount = Amount.getText().toString();

            String type = "createtransaction";
            signUp.setEnabled(false);

            BackgroundApi backgroundApi = new BackgroundApi(new Createtransaction.AsyncResponse() {
                @Override
                public void processFinish(String output) {
                    Log.d("response", output);
                    responseString = output;
                    final String responseTest = "User not found";
                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                public void run() {
                                    // On complete call either onLoginSuccess or onLoginFailed
                                    if (!(responseString.equals(responseTest))) {
                                        onLoginSuccess();
                                        Log.d("Response from Server", (String) responseString);
                                    } else if (responseString.equals(responseTest)) {
                                        Log.d("Response String Null!", (String) responseString);
                                        onLoginFailed();
                                    }
                                }
                            }, 3000);

                }

            });
            backgroundApi.execute(type, Description_, preAmount);
        } else{
            Toast.makeText(getBaseContext(), "Valid Entry Required Thanks!", Toast.LENGTH_LONG).show();
        }
    }
    public void onLoginSuccess() {
        Toast.makeText(getBaseContext(), "TRANSACTION CREATED" +responseString+ "Thanks!", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, getAllTransactions.class);
        startActivity(intent);
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Failed to Create Transaction", Toast.LENGTH_LONG).show();


    }

    public boolean validate() {
        boolean valid = true;

        String description = Description.getText().toString();
        String amount = Amount.getText().toString();
        //  String email = _emailText.getText().toString();
        //String mobile = _mobileText.getText().toString();
        //String password = _passwordText.getText().toString();
        //String reEnterPassword = _reEnterPasswordText.getText().toString();

        if (description.isEmpty() || description.length() < 1) {
            Description.setError("at least 1 character");
            valid = false;
        } else {
            Description.setError(null);
        }

        if (amount.isEmpty()) {
            Amount.setError("Enter 0.00 for a free item");
            valid = false;
        } else {
            Amount.setError(null);
        }

        return valid;
    }
*/
}
