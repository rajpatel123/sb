package tutosandroidfrance.com.toolbaranddrawer.tuts;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedDataUtils {

	private SharedPreferences appPrefs;
	private Editor editor;

	public SharedDataUtils(Context context) {
		// TODO Auto-generated constructor stub
		appPrefs = (SharedPreferences) context.getSharedPreferences(
				"GOOD_FIND", Context.MODE_PRIVATE);
		editor = appPrefs.edit();
	}

	public void clearAll()
	{
		editor.clear().commit();
	}
	
	public boolean isLocationUpdate()
	{
		return appPrefs.getBoolean("location_status", false);
	}
	
	public void setLocationUpdate(boolean status)
	{
		editor.putBoolean("location_status", status);
		editor.commit();
	}
	
	

	public void setAppVersion(int version) {
		editor.putInt("version", version);
		editor.commit();
	}

	public int getAppVersion() {
		return appPrefs.getInt("version", 0);
	}
	

	public void setUserEmail(String name) {
		editor.putString("email_id", name);
		editor.commit();
	}

	public String getUserEmail() {
		return appPrefs.getString("email_id", null);
	}

	public void setUserMobile(String name) {
		editor.putString("phonenumber", name);
		editor.commit();
	}

	public String getUserMobile() {
		return appPrefs.getString("phonenumber", null);
	}

	public void setUserID(String id) {
		editor.putString("fb_id", id);
		editor.commit();
	}

	public String getUserId() {
		return appPrefs.getString("fb_id", null);
	}

	public void setFbName(String name) {
		editor.putString("fb_name", name);
		editor.commit();
	}

	public String getFbName() {
		return appPrefs.getString("fb_name", null);
	}




	public boolean isShowTutorial() {
		System.out.println("isTut "+appPrefs.getBoolean("isTut", true));
		return appPrefs.getBoolean("isTut", true);
	}
	
	public void setShowTutorial(boolean isShow) {
		System.out.println("setTut "+isShow);
		editor.putBoolean("isTut",isShow);
		editor.commit();
	}
	

	public void setReferrerCode(String code) {
		editor.putString("referrer", code);
		editor.commit();
	}

	public String getReferrerCode() {
		return appPrefs.getString("referrer", null);
	}

}
