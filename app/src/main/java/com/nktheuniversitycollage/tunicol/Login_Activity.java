package com.nktheuniversitycollage.tunicol;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import API.EducationService;
import API.ServiceGenerator;
import APIObject.LoginEntity;
import APIObject.SignUpObject;
import APIObject.SocialLoginObj;
import APIObject.UserIdObj;
import APIResponse.RegistrationResponse;
import Infrastructure.AppComman;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 2/14/2018.
 */

public class Login_Activity extends AppCompatActivity {
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.editTextEmailUserName)
    EditText editTextEmailUserName;
    @BindView(R.id.progressBar)
    AVLoadingIndicatorView progressBar;
    Call call;
    AccessToken accessToken;
    private static final String EMAIL = "email";
    CallbackManager callbackManager = CallbackManager.Factory.create();
    private int passwordNotVisible = 1;
    @BindView(R.id.login_button)
    LoginButton login_button;
    String fbEmail, facebookId, fbUserName , googleId;
    GoogleSignInOptions gso;
    GoogleApiClient mGoogleApiClient;
    AccessTokenTracker accessTokenTracker;
    private int RC_SIGN_IN = 4788;
    URL profilePictureUrl;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                accessToken = currentAccessToken;
            }
        };
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @OnClick(R.id.googleBtn)
    void setGoogoleLogin(){
        googleSignIn();
    }

    private void googleSignIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @OnClick(R.id.faceBookBtn)
    void setFaceBookBtn() {
        if (AccessToken.getCurrentAccessToken() != null) {
            LoginManager.getInstance().logOut();
        }
        authorizeFaceBook();
    }

    private void authorizeFaceBook() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile"));
        login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                accessToken = loginResult.getAccessToken();
                if (AccessToken.getCurrentAccessToken() != null) {
                    RequestData();
                }
            }

            @Override
            public void onCancel() {
                Toast.makeText(Login_Activity.this, "You Have Cancelled Login with facebook", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(Login_Activity.this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void RequestData() {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {

                JSONObject json = response.getJSONObject();
                System.out.println("Json data :" + json);
                if (json != null) {
                    try {
                        if (json.getString("email").equals("") || json.getString("email").equals(null)) {
                            //AppCommon.getInstance(SignUpActivity.this).callDailog(SignUpActivity.this, "EmailId not Found");
                        } else {
                            facebookId = json.getString("id");
                             profilePictureUrl = new URL("https://graph.facebook.com/" + facebookId + "/picture?type=normal");
                            // String uri = "https://graph.facebook.com/" + facebookId + "/picture?type=normal";
                            // String uri = json.getString("picture") + facebookId + "/picture?type=normal";
                            fbUserName = json.getString("name");
                            fbEmail = json.getString("email");
                            facebookId = json.getString("id");
                            callSocialCall(facebookId,"facebook",fbUserName,fbEmail, String.valueOf(profilePictureUrl));
                           // callRegistrationApi(fbUserName, "",fbEmail,"" ,"",facebookId, profilePictureUrl);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @OnClick(R.id.forgotBtn)
    void setForgotPassword() {
        startActivity(new Intent(this, ForgotPassword_Activity.class));
    }

    @OnClick(R.id.singInBtn)
    void setLogin() {
        String emailAddress = editTextEmailUserName.getText().toString().trim();
        String paw = password.getText().toString().trim();
        if (emailAddress.equals("")) {
            editTextEmailUserName.setError(getString(R.string.plzEnterEmailId));
        } else if (!AppComman.getInstance(this).isEmailValid(emailAddress)) {
            editTextEmailUserName.setError(getString(R.string.emailValidation));
        } else if (paw.equals("")) {
            password.setError(getString(R.string.passwordValidation));
        } else
            callLoginCall(paw, emailAddress);

    }

    private void callLoginCall(String paw, String emailAddress) {
        AppComman.getInstance(this).setNonTouchableFlags(this);
        progressBar.setVisibility(View.VISIBLE);
        if (AppComman.getInstance(this).isConnectingToInternet(this)) {
            EducationService educationService = ServiceGenerator.createService(EducationService.class);
            call = educationService.loginResponseCall(new LoginEntity(emailAddress , paw,"android" , ""));
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    progressBar.setVisibility(View.GONE);
                    AppComman.getInstance(Login_Activity.this).clearNonTouchableFlags(Login_Activity.this);
                    if (response != null) {
                        RegistrationResponse registrationResponse = (RegistrationResponse) response.body();
                        if (registrationResponse != null && registrationResponse.getSuccess().equals("1")) {
                            getData(registrationResponse.getResult());
                        } else {
                            if (registrationResponse != null)
                                AppComman.getInstance(Login_Activity.this).showDialog(Login_Activity.this, registrationResponse.getError());
                            else
                                AppComman.getInstance(Login_Activity.this).showDialog(Login_Activity.this, getResources().getString(R.string.serverError));
                        }

                    } else
                        AppComman.getInstance(Login_Activity.this).showDialog(Login_Activity.this, getResources().getString(R.string.serverError));
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    AppComman.getInstance(Login_Activity.this).clearNonTouchableFlags(Login_Activity.this);
                    AppComman.getInstance(Login_Activity.this).showDialog(Login_Activity.this, getResources().getString(R.string.serverError));
                }
            });
        } else {
            progressBar.setVisibility(View.GONE);
            AppComman.getInstance(this).clearNonTouchableFlags(this);
            AppComman.getInstance(this).showDialog(this, getResources().getString(R.string.internetFail));
        }
    }

    private void getData(SignUpObject result) {

        String userString = new Gson().toJson(result);
        AppComman.getInstance(this).setUserLogin(result.getId());
        AppComman.getInstance(this).setUserObject(userString);
        AppComman.getInstance(this).setUserObject(userString);
        AppComman.getInstance(this).setLoginType(result.getLoginType());
        callLastLoginApi(AppComman.getInstance(this).getUserID());
        startActivity(new Intent(this, Home_Activity.class));
        finishAffinity();
    }

    private void callLastLoginApi(String userID) {
        EducationService educationService = ServiceGenerator.createService(EducationService.class);
        call = educationService.mLastLoginApi(new UserIdObj(userID));
    call.enqueue(new Callback() {
        @Override
        public void onResponse(Call call, Response response) {
        }

        @Override
        public void onFailure(Call call, Throwable t) {

        }
    });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result != null) {
                if (result.isSuccess()) {
                    GoogleSignInAccount account = result.getSignInAccount();
                    fbUserName = account.getDisplayName();
                    fbEmail = account.getEmail();
                    Uri photo = account.getPhotoUrl();

                    googleId = account.getId();

                    try {
                        if (photo != null)
                            profilePictureUrl = new URL(photo.toString());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    callSocialCall(googleId,"gmail",fbUserName,fbEmail, String.valueOf(profilePictureUrl));
                   // signUpBySocial = true;
                    //callRegistrationApi(firstName, "", email, "", "", String.valueOf(password), userType,facebookId, googleId, deviceType, address , contectNo);
                   /* DownLoadImage downLoadImage = new DownLoadImage(LoginActivity.this);
                    downLoadImage.execute(url);*/
                }
            }
        }
    }

    @OnClick(R.id.showIcon)
    void setShowPassword() {
        if (passwordNotVisible == 1) {
            password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            passwordNotVisible = 0;
        } else {
            password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            passwordNotVisible = 1;
        }
    }

    @OnClick(R.id.singUp)
    void setSingUp() {

        startActivity(new Intent(this, SignUp_Activity.class));
    }


    private void callSocialCall(String socialId, String socialType, String name, String email, String profilePic) {
        AppComman.getInstance(this).setNonTouchableFlags(this);
        progressBar.setVisibility(View.VISIBLE);
        if (AppComman.getInstance(this).isConnectingToInternet(this)) {
            EducationService educationService = ServiceGenerator.createService(EducationService.class);
            call = educationService.mSocialLogin(new SocialLoginObj(socialId,socialType,name,email,profilePic,"","android"));
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    progressBar.setVisibility(View.GONE);
                    AppComman.getInstance(Login_Activity.this).clearNonTouchableFlags(Login_Activity.this);
                    if (response != null) {
                        RegistrationResponse registrationResponse = (RegistrationResponse) response.body();
                        if (registrationResponse != null && registrationResponse.getSuccess().equals("1")) {
                            getData(registrationResponse.getResult());
                        } else {
                            if (registrationResponse != null)
                                AppComman.getInstance(Login_Activity.this).showDialog(Login_Activity.this, registrationResponse.getError());
                            else
                                AppComman.getInstance(Login_Activity.this).showDialog(Login_Activity.this, getResources().getString(R.string.serverError));
                        }

                    } else
                        AppComman.getInstance(Login_Activity.this).showDialog(Login_Activity.this, getResources().getString(R.string.serverError));
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    AppComman.getInstance(Login_Activity.this).clearNonTouchableFlags(Login_Activity.this);
                    AppComman.getInstance(Login_Activity.this).showDialog(Login_Activity.this, getResources().getString(R.string.serverError));
                }
            });
        } else {
            progressBar.setVisibility(View.GONE);
            AppComman.getInstance(this).clearNonTouchableFlags(this);
            AppComman.getInstance(this).showDialog(this, getResources().getString(R.string.internetFail));
        }
    }
}
