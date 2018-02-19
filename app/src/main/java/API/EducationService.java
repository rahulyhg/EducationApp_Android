package API;


import APIObject.LoginEntity;
import APIResponse.LoginResponse;
import APIResponse.RegistrationResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by User on 8/8/2017.
 */

public interface EducationService {

    @Multipart
    @POST("signup")
    Call<RegistrationResponse> REGISTRATION_RESPONSE_CALL(
            @Part("name") RequestBody name,
            @Part("email") RequestBody email,
            @Part("password") RequestBody password,
            @Part("confirmPassword") RequestBody confirmpassword,
            @Part("deviceType") RequestBody deviceType,
            @Part("deviceToken") RequestBody deviceToken,
            @Part("university") RequestBody university,
            @Part MultipartBody.Part profilePic
    );

    @POST("login")
    Call <RegistrationResponse> caLoginResponseCall(
            LoginEntity loginEntity
    );


}
