package Infrastructure;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TimePicker;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.nktheuniversitycollage.tunicol.R;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.MODE_PRIVATE;

public class AppComman {
    public static AppComman mInstance = null;
    static Context mContext;

    public static AppComman getInstance(Context _Context) {
        if (mInstance == null) {
            mInstance = new AppComman();
        }
        mContext = _Context;
        return mInstance;
    }

    public void showDialog(final Activity mActivity, String error) {
        if (!mActivity.isFinishing()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
            builder.setCancelable(false);
            builder.setTitle(mContext.getString(R.string.app_name));
            builder.setMessage(error);
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();
        }

    }

    public void showDialogWithFinish(final Activity mActivity, String error) {
        if (!mActivity.isFinishing()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
            builder.setCancelable(false);
            builder.setTitle(mContext.getString(R.string.app_name));
            builder.setMessage(error);
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    mActivity.finish();
                }
            });
            builder.show();
        }

    }

    public boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public void setNonTouchableFlags(Activity activity) {
        if (activity != null) {
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }

    public void clearNonTouchableFlags(Activity mActivity) {
        if (mActivity != null) {
            mActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }

    public boolean isConnectingToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
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

    public void setUserLogin(String userId) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE).edit();
        editor.putBoolean(MyPreference.login, true);
        editor.putString(MyPreference.userId, userId);
        editor.apply();
    }

    public boolean isUserLogIn() {
        SharedPreferences prefs = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE);
        return prefs.getBoolean(MyPreference.login, false);
    }

    public String getUserID() {
        SharedPreferences prefs = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE);
        return prefs.getString(MyPreference.userId, "");
    }

    public void setNotificationEnabled(String isPushNotificationsEnabled) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE).edit();
        editor.putString(MyPreference.isPushNotificationsEnabled, isPushNotificationsEnabled);
        editor.apply();
    }

    public String getpushNotification() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE);
        return mSharedPreferences.getString(MyPreference.isPushNotificationsEnabled, "");
    }

    public void setVerified(String rating) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE).edit();
        editor.putString(MyPreference.verify, rating);
        editor.apply();
    }

    public String getVerified() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE);
        return mSharedPreferences.getString(MyPreference.verify, "");
    }

    public void setLocation(String latitude, String longitude) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE).edit();
        editor.putString(MyPreference.latitude, latitude);
        editor.putString(MyPreference.longitude, longitude);
        editor.apply();
    }

    public String getLatitude() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE);
        return mSharedPreferences.getString(MyPreference.latitude, "");
    }

    public String getLongitude() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE);
        return mSharedPreferences.getString(MyPreference.longitude, "");
    }

    public void setEmail(String email) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE).edit();
        editor.putString(MyPreference.email, email);
        editor.apply();
    }

    public String getEmail() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE);
        return mSharedPreferences.getString(MyPreference.email, null);
    }

    public void setFirstName(String userName) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE).edit();
        editor.putString(MyPreference.fname, userName);
        editor.apply();
    }

    public String getFirstName() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE);
        return mSharedPreferences.getString(MyPreference.fname, "");
    }

    public void setLastName(String userName) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE).edit();
        editor.putString(MyPreference.lname, userName);
        editor.apply();
    }

    public String getLastName() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE);
        return mSharedPreferences.getString(MyPreference.lname, "");
    }

    public void setContactNumber(String phoneNumber) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE).edit();
        editor.putString(MyPreference.phoneNumber, phoneNumber);
        editor.apply();
    }

    public String getContactNumber() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE);
        return mSharedPreferences.getString(MyPreference.phoneNumber, "");
    }

    public void setCurrentUser(String en) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE).edit();
        editor.putString(MyPreference.language, en);
        editor.apply();
    }

    public String getCurrentUser() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE);
        return mSharedPreferences.getString(MyPreference.language, "en");
    }

    public void setTokenId(String tokenId) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mDeviceToken, MODE_PRIVATE).edit();
        editor.putString(MyPreference.tokenId, tokenId);
        editor.apply();
    }

    public String getTokenId() {
        SharedPreferences prefs = mContext.getSharedPreferences(MyPreference.mDeviceToken, MODE_PRIVATE);
        String token = prefs.getString(MyPreference.tokenId, null);
        return token;
    }

    public void clearPreference() {
        SharedPreferences.Editor editorLogin = mContext.getSharedPreferences(MyPreference.mUserLogin, MODE_PRIVATE).edit();
        editorLogin.clear();
        editorLogin.apply();
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mDeviceToken, MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();
        SharedPreferences.Editor editorPreferences = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE).edit();
        editorPreferences.clear();
        editorPreferences.apply();

    }

    public void updateRefreshToken(String refreshedToken) {
        if (isUserLogIn()) {
            if (getTokenId().equals(refreshedToken)) {

            } else {
                //      callUpdateTokenAPI(refreshedToken, getUserID());
            }
        }
    }

    public void setUserName(String userName) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE).edit();
        editor.putString(MyPreference.userName, userName);
        editor.apply();
    }

    public String getUserName() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE);
        return mSharedPreferences.getString(MyPreference.userName, "");
    }


    public void setApartmentNumber(String unit) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE).edit();
        editor.putString(MyPreference.unit, unit);
        editor.apply();
    }

    public String getApartmentNumber() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE);
        return mSharedPreferences.getString(MyPreference.unit, "");
    }

    public void setStreetAddress(String streetAddress) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE).edit();
        editor.putString(MyPreference.streetAddress, streetAddress);
        editor.apply();
    }

    public String getStreetAddress() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE);
        return mSharedPreferences.getString(MyPreference.streetAddress, "");
    }

    public void setSuburb(String suburb) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE).edit();
        editor.putString(MyPreference.suburb, suburb);
        editor.apply();
    }

    public String getSuburb() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE);
        return mSharedPreferences.getString(MyPreference.suburb, "");
    }

    public void setState(String state) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE).edit();
        editor.putString(MyPreference.state, state);
        editor.apply();
    }

    public String getState() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE);
        return mSharedPreferences.getString(MyPreference.state, "");
    }

    public void setPostcode(String post_code) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE).edit();
        editor.putString(MyPreference.post_code, post_code);
        editor.apply();
    }

    public String getPostcode() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE);
        return mSharedPreferences.getString(MyPreference.post_code, "");
    }

    public void setProfilePicture(String profileImageUrl) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE).edit();
        editor.putString(MyPreference.profileImageUrl, profileImageUrl);
        editor.apply();
    }

    public String getProfilePicture() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE);
        return mSharedPreferences.getString(MyPreference.profileImageUrl, "");
    }

    public void setAboutMe(String aboutMe) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE).edit();
        editor.putString(MyPreference.aboutMe, aboutMe);
        editor.apply();
    }

    public String getAboutMe() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE);
        return mSharedPreferences.getString(MyPreference.aboutMe, "");
    }

    public void setTitle(String title) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE).edit();
        editor.putString(MyPreference.title, title);
        editor.apply();
    }

    public String getTitle() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE);
        return mSharedPreferences.getString(MyPreference.title, "");
    }

    public void setCountry(String country) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE).edit();
        editor.putString(MyPreference.country, country);
        editor.apply();
    }

    public String getCountry() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE);
        return mSharedPreferences.getString(MyPreference.country, "");
    }

    public void setAuthenticateToken(String authToken) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE).edit();
        editor.putString(MyPreference.authToken, authToken);
        editor.apply();
    }

    public String getAuthenticateToken() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE);
        return mSharedPreferences.getString(MyPreference.authToken, "");
    }

    public void setSingUpData(String objstr) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.SingUpPreferences, MODE_PRIVATE).edit();
        editor.putString(MyPreference.singUp, objstr);
        editor.apply();
    }

    public String getSingUpData() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MyPreference.SingUpPreferences, MODE_PRIVATE);
        return mSharedPreferences.getString(MyPreference.singUp, "");
    }

    public boolean validation(String text) {
        boolean flag = false;
        if (text.isEmpty()) {
            flag = true;
        }
        return flag;

    }

    public String getAbn() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE);
        return mSharedPreferences.getString(MyPreference.abn, "");
    }

    public void setAbn(String abn) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE).edit();
        editor.putString(MyPreference.abn, abn);
        editor.apply();
    }

    public void onHideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void setUserObject(String loginObject) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE).edit();
        editor.putString(MyPreference.loginObj, loginObject);
        editor.apply();
    }

    public String getUserObject() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE);
        return mSharedPreferences.getString(MyPreference.loginObj, "");
    }

    public void setPassword(String password) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE).edit();
        editor.putString(MyPreference.password, password);
        editor.apply();
    }

    public String getPassword() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE);
        return mSharedPreferences.getString(MyPreference.password, "");
    }

    public String getTimeElapsed(String timeElapsed) {
        String result;
        Long mSec = System.currentTimeMillis();
        long serverTime = Long.parseLong(timeElapsed) * 1000L;
        long diffrence = mSec - serverTime;
        long hrMiliSec = 3600000;
        if (checkDate(mSec, serverTime)) {
            if (diffrence < hrMiliSec) {
                result = String.valueOf(TimeUnit.MILLISECONDS.toMinutes(diffrence)) + " " + " min ago";
            } else {
                result = callTimeFormat(serverTime, "hh:mm aaa");
            }
        } else {
            result = callTimeFormat(serverTime, "hh:mm aaa");
            // result = callTimeFormat(serverTime, "h:mm a , dd.MMM");
        }
        return result;
    }

    private boolean checkDate(Long current, long serverTime) {
        String dateFormate = "dd.MMM.yyyy";
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormate);
        Calendar currentCalender = Calendar.getInstance();
        currentCalender.setTimeInMillis(current);
        Calendar serverCalendar = Calendar.getInstance();
        serverCalendar.setTimeInMillis(serverTime);
        return (formatter.format(currentCalender.getTime()).equals(formatter.format(serverCalendar.getTime())));
    }

    public String callTimeFormat(long diffrence, String mm) {
        SimpleDateFormat formatter = new SimpleDateFormat(mm);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(diffrence);
        return formatter.format(calendar.getTime());
    }

    public static DraweeController getDraweeController(DraweeView imageView, String imageUrl, int size) {
        Uri uri = Uri.parse(imageUrl);

        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(size, size))
                .setProgressiveRenderingEnabled(false)
                .build();

        return Fresco.newDraweeControllerBuilder()
                .setOldController(imageView.getController())
                .setImageRequest(request)
                .build();
    }

    public void setLocalUserId(String userId) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE).edit();
        editor.putString(MyPreference.localUserId, userId);
        editor.apply();
    }

    public String getLocalUserId() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MyPreference.mPreferences, MODE_PRIVATE);
        return mSharedPreferences.getString(MyPreference.localUserId, "");
    }

    public void showTimePicker(Activity activity, final EditText timeTextView) {
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR);
        int mMinute = c.get(Calendar.MINUTE);
        final TimePickerDialog timePickerDialog = new TimePickerDialog(activity,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        String AM_PM;
                        AM_PM = (hourOfDay < 12) ? "AM" : "PM";
                        int hour = (hourOfDay < 12) ? hourOfDay : hourOfDay - 12;
                        timeTextView.setText(setTimeEditText(hour, minute, AM_PM));
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    private String setTimeEditText(int selectedHour, int selectedMinute, String AM_PM) {
        if (selectedHour == 00) {
            selectedHour = 12;
        } else
            selectedHour = (selectedHour <= 12) ? selectedHour : selectedHour - 12;
        if (selectedHour < 10) {
            if (selectedMinute < 10) {

                return ("0" + selectedHour + ":" + "0" + selectedMinute + " " + AM_PM);
            } else
                return ("0" + selectedHour + ":" + selectedMinute + " " + AM_PM);
        } else {
            if (selectedMinute < 10) {
                return (selectedHour + ":" + "0" + selectedMinute + " " + AM_PM);
            } else
                return (selectedHour + ":" + selectedMinute + " " + AM_PM);
        }
    }


}
