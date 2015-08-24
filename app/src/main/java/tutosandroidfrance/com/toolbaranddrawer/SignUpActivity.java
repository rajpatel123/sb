package tutosandroidfrance.com.toolbaranddrawer;


import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import tutosandroidfrance.com.toolbaranddrawer.constants.AppConstants;
import tutosandroidfrance.com.toolbaranddrawer.model.UserProfile;
import tutosandroidfrance.com.toolbaranddrawer.utils.Utils;


public class SignUpActivity extends Activity implements View.OnClickListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    /* Is there a ConnectionResult resolution in progress? */

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */


    private static final String TAG = "FragmentRegister";
    private static final int RC_SIGN_IN = 0;

    private Button sign_in_btn;

    //private UiLifecycleHelper uiHelper;
    private Button btnSignInGP;
    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;
    /**
     * A flag indicating that a PendingIntent is in progress and prevents us
     * from starting further intents.
     */
    private boolean mIntentInProgress;
    private ConnectionResult mConnectionResult;
    private boolean mSignInClicked;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Button mSignUp;

    private SignInButton mGplusSignIn;

    private EditText mFName, mLName, mPassword, mEmail, mPhone, mDob;
    TextView mSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.good_find_sign_up);


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PROFILE))
                .build();


        initialize();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in:
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                finish();

                break;
            case R.id.sign_up:

                if (TextUtils.isEmpty(mFName.getText().toString())) {
                    mFName.setError("Enter first name!");
                    return;
                }
                if (TextUtils.isEmpty(mLName.getText().toString())) {
                    mLName.setError("Enter last name!");
                    return;
                }
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

                if (TextUtils.isEmpty(mPhone.getText().toString())) {
                    mPhone.setError("Enter password!");
                    return;
                }

                if (TextUtils.isEmpty(mDob.getText().toString())) {
                    mDob.setError("Enter date of birth!");
                    return;
                }

                if (Utils.isConnectedToInternet(SignUpActivity.this)) {
                   // showProgressDialog("Please wait...");

                   // new NetworkUtils(this, null, this).execute(prepareJson().toString());
                } else {
                    Utils.message(SignUpActivity.this, "Please check your internet connectivity");
                }
                break;

            case R.id.g_login:
                signInWithGplus();
                break;

        }

    }


    public interface OnClickListener {
        public void onClicked(int id);

       // public void showProfile(UserProfile userProfile);

    }


    /**
     * Sign-in into google
     */
    private void signInWithGplus() {
        if (!mGoogleApiClient.isConnecting()) {
            mSignInClicked = true;
            //showProgressDialog("Signing In...");
            mGoogleApiClient.connect();
           // resolveSignInError();
        }else{
            mGoogleApiClient.connect();
        }
    }


    /**
     * Method to resolve any signin errors
     */
    private void resolveSignInError() {
        if (mConnectionResult.hasResolution()) {
            try {
                mIntentInProgress = true;
                mConnectionResult.startResolutionForResult(SignUpActivity.this,
                        RC_SIGN_IN);
            } catch (IntentSender.SendIntentException e) {
                mIntentInProgress = false;
                mGoogleApiClient.connect();
            }
        }
    }

    private void initialize() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        hideSoftKeyBoard();
        mGplusSignIn = (SignInButton) findViewById(R.id.g_login);

        mSignIn = (TextView) findViewById(R.id.sign_in);
        mSignUp = (Button) findViewById(R.id.sign_up);
        mFName = (EditText) findViewById(R.id.fName);
        mLName = (EditText) findViewById(R.id.lName);
        mEmail = (EditText) findViewById(R.id.emailId);
        mPassword = (EditText) findViewById(R.id.password);
        mDob = (EditText) findViewById(R.id.dob);
        mPhone = (EditText) findViewById(R.id.phone);

        mGplusSignIn.setOnClickListener(this);
        mSignIn.setOnClickListener(this);
        mSignUp.setOnClickListener(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
       // mGoogleApiClient.connect();

    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }

    }


    @Override
    public void onConnected(Bundle bundle) {
        mSignInClicked = false;
        //removeProgressDialog();

        // Get user's information

        UserProfile userProfile=getProfileInformation();
        if(userProfile!=null){

            if (Utils.isConnectedToInternet(SignUpActivity.this)) {
                //showProgressDialog("Please wait...");

                //new NetworkUtils(this, null, this).execute(prepareJson(userProfile));
            } else {
                Utils.message(SignUpActivity.this, "Please check your internet connectivity");
            }
        }else{
          Utils.message(SignUpActivity.this,"Error occured");
        }

    }

    private String prepareJson(UserProfile userProfile)
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(AppConstants.ACTION, AppConstants.REGISTER_USER);
            jsonObject.put(AppConstants.REFERRAL_ID, "");
            jsonObject.put(AppConstants.FIRST_NAME, userProfile.getName());
            jsonObject.put(AppConstants.LAST_NAME, "");
            jsonObject.put(AppConstants.EMAIL, userProfile.getEmail());
            jsonObject.put(AppConstants.PASSWORD, "");
            jsonObject.put(AppConstants.PHONE, "");
            jsonObject.put(AppConstants.DOB, userProfile.getDob());
            jsonObject.put(AppConstants.SOURCE, "Google");
            jsonObject.put(AppConstants.CREATED_AT, new Date().toString());
            jsonObject.put(AppConstants.GCM_REG_ID, "daddddsffdfsfsfsfsf");
            jsonObject.put(AppConstants.DEVICE_ID, "13ww34413w12");
            jsonObject.put(AppConstants.PLATFORM, "Android");
        } catch (JSONException e) {
            Log.d(TAG, "Preparing json" + e.getMessage());
        }

        return jsonObject.toString();



    }
    @Override
    public void onConnectionSuspended(int i) {

        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (!result.hasResolution()) {
            GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(),
                    SignUpActivity.this, 0).show();
            return;
        }

        if (!mIntentInProgress) {
            // Store the ConnectionResult for later usage
            mConnectionResult = result;

            if (mSignInClicked) {
                // The user has already clicked 'sign-in' so we attempt to
                // resolve all
                // errors until the user is signed in, or they cancel.
                resolveSignInError();
            }
        }
    }

    private String prepareJson() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(AppConstants.ACTION, AppConstants.REGISTER_USER);
            jsonObject.put(AppConstants.REFERRAL_ID, "");
            jsonObject.put(AppConstants.FIRST_NAME, mFName.getText().toString());
            jsonObject.put(AppConstants.LAST_NAME, mLName.getText().toString());
            jsonObject.put(AppConstants.EMAIL, mEmail.getText().toString());
            jsonObject.put(AppConstants.PASSWORD, mPassword.getText().toString());
            jsonObject.put(AppConstants.PHONE, mPhone.getText().toString());
            jsonObject.put(AppConstants.DOB, mDob.getText().toString());
            jsonObject.put(AppConstants.SOURCE, "android");
            jsonObject.put(AppConstants.CREATED_AT, "2015-07-31 04:05:34");
            jsonObject.put(AppConstants.GCM_REG_ID, "daddddsffdfsfsfsfsf");
            jsonObject.put(AppConstants.DEVICE_ID, "13ww34413w12");
            jsonObject.put(AppConstants.PLATFORM, "Android");
        } catch (JSONException e) {
            Log.d(TAG, "Preparing json" + e.getMessage());
        }

        return jsonObject.toString();
    }

//    @Override
//    public void updateView(String responseString, boolean isSuccess, int reqType){
//
//    }


    /**
     * Fetching user's information name, email, profile pic
     * */
    private UserProfile getProfileInformation() {
        try {
            if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
                Person currentPerson = Plus.PeopleApi
                        .getCurrentPerson(mGoogleApiClient);

                UserProfile userProfile = new UserProfile();
                userProfile.setName(currentPerson.getDisplayName());
                userProfile.setDob(currentPerson.getBirthday());
                String url = currentPerson.getImage().getUrl();
                userProfile.setSn_id( currentPerson.getId());
                userProfile.setEmail(Plus.AccountApi.getAccountName(mGoogleApiClient));
                userProfile.setUser_type("google");
                userProfile.setImg_url(url.replace("=50","=200"));
//				userProfile.img_url = str[0]+"?sz=150";
                System.out.println("-->> Hii " + userProfile.getImg_url());
                return userProfile;

            } else {
                Toast.makeText(SignUpActivity.this, "Person information is null",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

