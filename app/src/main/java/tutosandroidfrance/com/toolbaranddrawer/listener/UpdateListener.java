package tutosandroidfrance.com.toolbaranddrawer.listener;

import android.support.v4.BuildConfig;
import android.util.Log;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import tutosandroidfrance.com.toolbaranddrawer.network.VolleyExceptionUtil;

public class UpdateListener implements Listener<JSONObject>, ErrorListener {

	private int						reqType;
	private onUpdateViewListener	onUpdateViewListener;

	public interface onUpdateViewListener {
		public void updateView(String responseString, boolean isSuccess, int reqType);
	}

	public UpdateListener(onUpdateViewListener onUpdateView, int reqType) {
		this.reqType = reqType;
		this.onUpdateViewListener = onUpdateView;
	}

	@Override
	public void onErrorResponse(VolleyError error) {
		onUpdateViewListener.updateView(VolleyExceptionUtil.getErrorMessage(error), false, reqType);
	}

	
	@Override
	public void onResponse(JSONObject response) {
		// TODO Auto-generated method stub
		if (BuildConfig.DEBUG) {
			Log.i("Respone-------------->", response.toString());
		}
		onUpdateViewListener.updateView(response.toString(), true, reqType);
	}

}
