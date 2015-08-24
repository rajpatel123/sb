package tutosandroidfrance.com.toolbaranddrawer.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Utils {

    public static void CopyStream(InputStream is, OutputStream os) {
        final int buffer_size = 1024;
        try {
            byte[] bytes = new byte[buffer_size];
            for (; ; ) {
                int count = is.read(bytes, 0, buffer_size);
                if (count == -1)
                    break;
                os.write(bytes, 0, count);
            }
        } catch (Exception ex) {
        }
    }

    private static final int MAX_ATTEMPTS = 5;
    private static final int BACKOFF_MILLI_SECONDS = 2000;

    static final String DISPLAY_MESSAGE_ACTION =
            "com.androidhive.pushnotifications.DISPLAY_MESSAGE";

    static final String EXTRA_MESSAGE = "message";


    static void displayMessage(Context context, String message) {
        Intent intent = new Intent(DISPLAY_MESSAGE_ACTION);
        intent.putExtra(EXTRA_MESSAGE, message);
        context.sendBroadcast(intent);
    }
//	static void register(final Context context, final String regId) {
//		SharedDataUtils dataUtils = new SharedDataUtils(context);
//      
//		 JSONObject root = new JSONObject();
//		 try {
//		 root.put("userfbid", dataUtils.getUserId());
//		 root.put("username", dataUtils.getFbName());
//		 root.put("devicetype","android");
//		 root.put("notificationid",regId);
//		 root.put("appkey", Constants.SERVER_APP_KEY);
//		 root.put("apimethod", Constants.SERVER_ACTION_LOGIN);
//		
//		 } catch (JSONException e) {
//		 // TODO Auto-generated catch block
//		 System.out.println("--->> error : "+e);
//		 e.printStackTrace();
//		 }
//		 POST(Constants.LINK_SERVER_POST, root.toString());
//         GCMRegistrar.setRegisteredOnServer(context, true);
//
////        System.out.println("Hello \"Bhanu\"");
//    }

    public synchronized static String POST(String url, String data) {
        InputStream inputStream = null;
        String result = "";
        try {


            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            // 5. set json to StringEntity
//			 StringEntity se = new StringEntity(data);


            JSONObject jsonObject = new JSONObject();
//            try {
//               // jsonObject.put(AppConstants.ACTION, "checking");
//
//
//            } catch (JSONException e) {
//            }
            List<NameValuePair> nvp = new ArrayList<NameValuePair>(2);
            nvp.add(new BasicNameValuePair("name", "Manoj"));


          //  httpPost.addHeader("name","Manoj");

            // // 6. set httpPost Entity
			// httpPost.setEntity(se);

            httpPost.setEntity(new UrlEncodedFormEntity(nvp, "UTF-8"));

            // 7. Set some headers to inform server about the type of the
            // content
            // httpPost.setHeader("Accept", "application/json");
            // httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // 10. convert inputstream to string
            if (inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream)
            throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public static String stringFormat(int value) {
        int k = 1024;
        if (value <= k)
            return value + " bytes ";
        else if (value > k && value <= k * k)
            return value / k + " KB ";
        else if (value > k * k && value <= k * k * k)
            return value / (k * k) + " MB ";
        else if (value > k * k * k && value <= k * k * k * k)
            return value / (k * k * k) + " GB ";
        return null;

    }

//	public static File moveFile(String inputPath, Context context) {
//
//		InputStream in = null;
//		OutputStream out = null;
//		File final_file = null;
//		try {
//
//			File input_filt = new File(inputPath);
//			// create output directory if it doesn't exist
//			File dir = getCatchItFolder(inputPath);
//
//			if (dir == null) {
//				return dir;
//			}
//			if (!dir.exists()) {
//				dir.mkdirs();
//			}
//
//			in = new FileInputStream(inputPath);
//			System.out.println("Bhanu File name " + input_filt.getName());
//			if (input_filt.getName().startsWith("contact_")
//					&& new Validator().validate(input_filt.getName())) {
//				final_file = new File(dir, "contact_"
//						+ new SharedDataUtils(context).getUserId() + "_"
//						+ System.currentTimeMillis()
//						+ getFileExtension(inputPath));
//			} else
//				final_file = new File(dir, "File_"
//						+ new SharedDataUtils(context).getUserId() + "_"
//						+ System.currentTimeMillis()
//						+ getFileExtension(inputPath));
//			out = new FileOutputStream(final_file);
//
//			byte[] buffer = new byte[1024];
//			int read;
//			while ((read = in.read(buffer)) != -1) {
//				out.write(buffer, 0, read);
//			}
//			in.close();
//			in = null;
//
//			// write the output file
//			out.flush();
//			out.close();
//			out = null;
//
//			// // delete the original file
//			// new File(inputPath + inputFile).delete();
//
//		}
//
//		catch (FileNotFoundException fnfe1) {
//			Log.e("tag", fnfe1.getMessage());
//		} catch (Exception e) {
//			Log.e("tag", e.getMessage());
//		}
//		return final_file;
//	}

    /**
     * @return Number of bytes available on External storage
     */
    @SuppressWarnings("deprecation")
    public static long getAvailableSpaceInBytes() {
        long availableSpace = -1L;
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory()
                .getPath());
        availableSpace = (long) stat.getAvailableBlocks()
                * (long) stat.getBlockSize();

        return availableSpace;
    }

    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    public static String getFileExtension(String name) {
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }

//	public static File getCatchItFolder(String looks_for) {
//		int type = getFileType(looks_for);
//		String file_name = Constants.FOLDER_OTHER;
//		switch (type) {
//		case Constants.FILE_TYPE_IMAGE:
//			file_name = Constants.FOLDER_IMAGES;
//			break;
//		case Constants.FILE_TYPE_AUDIO:
//			file_name = Constants.FOLDER_AUDIO;
//			break;
//		case Constants.FILE_TYPE_VIDEO:
//			file_name = Constants.FOLDER_VIDEO;
//			break;
//		default:
//			file_name = Constants.FOLDER_OTHER;
//			break;
//		}
//
//		if (isExternalStorageAvailable() && !isExternalStorageReadOnly()) {
//			File folder = new File(file_name);
//			if (!folder.exists())
//				folder.mkdirs();
//			return folder;
//
//		}
//
//		return null;
//
//	}

//	public static int getFileType(String path) {
//		String extntn = getFileExtension(path);
//		if (extntn.equalsIgnoreCase(".png") || extntn.equalsIgnoreCase(".jpeg")
//				|| extntn.equalsIgnoreCase(".jpg")
//				|| extntn.equalsIgnoreCase(".gif")
//				|| extntn.equalsIgnoreCase(".bmp")
//				|| extntn.equalsIgnoreCase(".webp")) {
//			return Constants.FILE_TYPE_IMAGE;
//		} else if (extntn.equalsIgnoreCase(".3gp")
//				|| extntn.equalsIgnoreCase(".mp4")
//				|| extntn.equalsIgnoreCase(".avi")
//				|| extntn.equalsIgnoreCase(".mov")
//				|| extntn.equalsIgnoreCase(".m4v")) {
//			return Constants.FILE_TYPE_VIDEO;
//		} else if (extntn.equalsIgnoreCase(".mp3")
//				|| extntn.equalsIgnoreCase(".wav")
//				|| extntn.equalsIgnoreCase(".amr")
//				|| extntn.equalsIgnoreCase(".ogg")
//				|| extntn.equalsIgnoreCase(".m4a")) {
//			return Constants.FILE_TYPE_AUDIO;
//		}
//		return Constants.FILE_TYPE_OTHER;
//	}

    public static String getFormattedDate(long timeInMili) {
        Date date = new Date(timeInMili);
        // SimpleDateFormat outputFormat = new
        // SimpleDateFormat("MM/dd/yyyy hh:mm");
        SimpleDateFormat outputFormat = new SimpleDateFormat(
                "E hh:mm a dd/MM/yyyy");
        return outputFormat.format(date);

    }

    public static String getDateOnly(long timeInMili) {
        Date date = new Date(timeInMili);
        // SimpleDateFormat outputFormat = new
        // SimpleDateFormat("MM/dd/yyyy hh:mm");
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMM d");
        return outputFormat.format(date);

    }

    public static String getTimeOnly(long timeInMili) {
        Date date = new Date(timeInMili);
        // SimpleDateFormat outputFormat = new
        // SimpleDateFormat("MM/dd/yyyy hh:mm");
        SimpleDateFormat outputFormat = new SimpleDateFormat("hh:mm a");
        return outputFormat.format(date);

    }


    public static long getDateInMili(long timeInMili) {
        Date date = new Date(timeInMili);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.AM_PM, Calendar.AM);
        return cal.getTimeInMillis();
    }

    public static boolean isContact(String url) {
        return new File(url).getName().startsWith("contact_");
    }

    public static String getFileSize(String path) {
        File file = new File(path);
        long fileSize = file.length();
        return (fileSize / 1024.0) + " KB";
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        System.out.println("Bhanu bitmap after " + output.getWidth());
        return output;
    }

    public static void addToContactBook(String filepath, Context activity) {

        String DisplayName = "";
        String MobileNumber = "";
        String emailID = "";

        File file = new File(filepath);

        // Read text from file
        StringBuilder text = new StringBuilder();
        String jsonStr;

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }

            jsonStr = text.toString();
            JSONObject reader = new JSONObject(jsonStr);
            DisplayName = reader.getString("displayname");
            MobileNumber = reader.getString("phonenumber");
            emailID = reader.getString("emailid");

        } catch (IOException e) {
            // You'll need to add proper error handling here
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);

        // Just two examples of information you can send to pre-fill out
        // data for the
        // user. See android.provider.ContactsContract.Intents.Insert for
        // the complete
        // list.
        intent.putExtra(ContactsContract.Intents.Insert.NAME, DisplayName);
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, MobileNumber);
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, emailID);

        // Send with it a unique request code, so when you get called back,
        // you can
        // check to make sure it is from the intent you launched (ideally
        // should be
        // some public static final so receiver can check against it)
        int PICK_CONTACT = 100;
        activity.startActivity(intent);

    }

    public static void openMaps(String src, String dst, Context context) {

        Uri uriii = Uri.parse("http://maps.google.com/maps?saddr=" + src
                + "&daddr=" + dst);
        Intent intent = new Intent(Intent.ACTION_VIEW, uriii);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static boolean isAppForeground(Context context) {

        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//	    List<RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        List<ActivityManager.RunningTaskInfo> taskInfo = activityManager.getRunningTasks(1);
        ComponentName componentInfo = taskInfo.get(0).topActivity;
        System.out.println("Bhanu foreground " + componentInfo.getPackageName() + " My " + context.getPackageName());
        if (componentInfo.getPackageName().equals(context.getPackageName()))
            return true;
        else
            return false;

    }

    public static boolean isConnectedToInternet(Context _context) {
        ConnectivityManager connectivity = (ConnectivityManager) _context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }

    public static void message(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();

    }

    public static boolean isEmailValid(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

// onClick of button perform this simplest code.
        if (email.matches(emailPattern)) {
            return true;
        } else {
            return false;
        }
    }


}
