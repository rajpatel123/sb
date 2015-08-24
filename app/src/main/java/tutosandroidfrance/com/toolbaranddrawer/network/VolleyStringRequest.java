package tutosandroidfrance.com.toolbaranddrawer.network;

import android.support.v4.BuildConfig;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import tutosandroidfrance.com.toolbaranddrawer.listener.UpdateListener;

public class VolleyStringRequest extends StringRequest {

	private Map<String, String>	mRequestparams;
	private Map<String, String>	mRequestheader;

	private VolleyStringRequest(int method, String url, UpdateListener updateListener, Map<String, String> params) {
		super(method, url, null, updateListener);
		mRequestparams = params;
	}

	public static VolleyStringRequest doPost(String url, UpdateListener updateListener, Map<String, String> params) {
		if (BuildConfig.DEBUG) {
			Log.i("Request Url------->", url);
			Log.i("Request Params------>", params.toString());
		}
		return new VolleyStringRequest(Method.POST, url, updateListener, params);
	}

	public static VolleyStringRequest doGet(String url, UpdateListener updateListener) {
		return new VolleyStringRequest(Method.GET, url, updateListener, null);
	}

	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		return mRequestparams;
	}
	
	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		mRequestheader = new HashMap<String, String>();
		mRequestheader.put("Content-Type", "application/json");
		return mRequestheader;
	}

}
