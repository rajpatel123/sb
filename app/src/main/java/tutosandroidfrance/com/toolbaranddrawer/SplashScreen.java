package tutosandroidfrance.com.toolbaranddrawer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity{
	private boolean isTimerStarted = false;
	//private ActivationCounterClass mActivationTimer;
	ProgressDialog progressDialog;
	
	
	
	  @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_splash);



	//// METHOD 1
	//
//	        /****** Create Thread that will sleep for 5 seconds *************/
//	        Thread background = new Thread() {
//	            public void run() {
	//
//	                try {
//	                    // Thread will sleep for 5 seconds
//	                    sleep(5*1000);
	//
//	                    // After 5 seconds redirect to another intent
//	                    Intent i=new Intent(getBaseContext(),FirstScreen.class);
//	                    startActivity(i);
	//
//	                    //Remove activity
//	                    finish();
	//
//	                } catch (Exception e) {
	//
//	                }
//	            }
//	        };
	//
//	        // start thread
//	        background.start();

	//METHOD 2


	        new Handler().postDelayed(new Runnable() {

	            // Using handler with postDelayed called runnable run method

	            @Override
	            public void run() {
	                Intent i = new Intent(SplashScreen.this, TutsActivity.class);
	                startActivity(i);

	                // close this activity
	                finish();
	            }
	        }, 3*1000); //wait for 5 seconds

	    }
	
	
	
	

	
}
