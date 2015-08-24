package tutosandroidfrance.com.toolbaranddrawer.network;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyManager {

	private static final String TAG = "VolleyManager";
	private static VolleyManager mVolleyManager;
	private RequestQueue mRequestQueue;
	private Context mContext;
	
	private VolleyManager(Context context) {
		mContext = context;
		mRequestQueue = Volley.newRequestQueue(mContext);
	}
	
	public static VolleyManager getInstance(Context context){
		if (mVolleyManager == null) {
			mVolleyManager = new VolleyManager(context);
		}
		return mVolleyManager;
	}

//	public RequestQueue getRequestQueue() {
//		if (mRequestQueue == null) {
//			mRequestQueue = Volley.newRequestQueue(mContext);
//		}
//		return mRequestQueue;
//	}


	public <T> void addToRequestQueue(Request<T> req, String tag) {
		
		// set the default tag if tag is empty
		req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
		req.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 30, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		mRequestQueue.add(req);
	}

	public void cancelPendingRequests(Object tag) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}

}
