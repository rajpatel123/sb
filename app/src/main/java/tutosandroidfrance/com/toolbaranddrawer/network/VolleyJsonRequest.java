package tutosandroidfrance.com.toolbaranddrawer.network;

import android.support.v4.BuildConfig;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import tutosandroidfrance.com.toolbaranddrawer.listener.UpdateListener;

public class VolleyJsonRequest extends JsonObjectRequest {

	private JSONObject mRequestparams;
	private Map<String, String>	mRequestheader;
	private final int REQUEST_TIME_OUT = 15000;

	private VolleyJsonRequest(int method, String url, UpdateListener updateListener, HashMap<String, String> params) {
		
		super(url,new JSONObject(params), updateListener, updateListener);
		setRetryPolicy(new DefaultRetryPolicy(REQUEST_TIME_OUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
	}
	
	private VolleyJsonRequest(int method, String url, UpdateListener updateListener) {
		
		super(method,url,null, updateListener);
		setRetryPolicy(new DefaultRetryPolicy(REQUEST_TIME_OUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
	}

	public static VolleyJsonRequest doPost(String url, UpdateListener updateListener,HashMap<String, String> params) {
		if (BuildConfig.DEBUG) {
			Log.i("Request Url-------->", url);
			Log.i("Request Params------->", params.toString());
		}
		return new VolleyJsonRequest(Method.POST, url, updateListener, params);
	}
	
	public static VolleyJsonRequest doget(String url, UpdateListener updateListener) {
		if (BuildConfig.DEBUG) {
			Log.i("Request Url------->", url);
		}
//		Log.i("ResponseJonu-------------->", new VolleyJsonRequest(Method.POST, url, updateListener).toString());
		return new VolleyJsonRequest(Method.POST, url, updateListener);
	}

}