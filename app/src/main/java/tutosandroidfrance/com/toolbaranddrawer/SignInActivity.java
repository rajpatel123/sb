package tutosandroidfrance.com.toolbaranddrawer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import tutosandroidfrance.com.toolbaranddrawer.constants.AppConstants;
import tutosandroidfrance.com.toolbaranddrawer.utils.Utils;


public class SignInActivity extends Activity implements View.OnClickListener {
    private TextView mSignUp;
    private Button mSignIn;
    private EditText mEmail, mPassword;
    private String LOG_TAG = "SignInActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        initialize();


    }

    private void initialize() {
       // hideSoftKeyBoard11();
        mSignIn = (Button) findViewById(R.id.sign_in);
        mSignUp = (TextView) findViewById(R.id.sign_up);
        mEmail = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        mSignIn.setOnClickListener(this);
        mSignUp.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in:
               // showProgressDialog("Please wait...");
                if (TextUtils.isEmpty(mEmail.getText().toString())) {
                    mEmail.setError("Enter valid email!");
                    return;
                } else if (!Utils.isEmailValid(mEmail.getText().toString())) {
                    mEmail.setError("Enter valid email!");
                    return;
                }

                if (TextUtils.isEmpty(mPassword.getText().toString())) {
                    mPassword.setError("Enter password!");
                    return;
                }


                if (Utils.isConnectedToInternet(SignInActivity.this)) {
                    //showProgressDialog("Please wait...");


                   // startActivity(new Intent(SignInActivity.this,HomeActivity.class));
                    //new NetworkUtils(this,null,this).execute(prepareJson().toString());
                } else {
                    Utils.message(SignInActivity.this, "Please check your internet connectivity");
                }

                break;
            case R.id.sign_up:
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
                finish();
                break;
        }
    }


//    @Override
//    public void updateView(String responseString, boolean isSuccess, int reqType){
//        //removeProgressDialog();
//       // startActivity(new Intent(SignInActivity.this, HomeActivity.class));
//        finish();
//    }

    private String prepareJson() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(AppConstants.ACTION, AppConstants.REGISTER_USER);
            jsonObject.put(AppConstants.EMAIL, mEmail.getText().toString());
            jsonObject.put(AppConstants.PASSWORD, mPassword.getText().toString());
        } catch (JSONException e) {
            Log.d(LOG_TAG, "Preparing json" + e.getMessage());
        }
        return jsonObject.toString();
    }

}

