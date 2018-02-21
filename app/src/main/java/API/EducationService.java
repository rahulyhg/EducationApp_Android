package API;


import APIObject.CourseEntity;
import APIObject.EditProfileEntity;
import APIObject.ExamSearchEntity;
import APIObject.ForgotEntity;
import APIObject.HistObj;
import APIObject.LoginEntity;
import APIObject.UpdateExamEntity;
import APIObject.UserIdObj;
import APIResponse.CommonResponse;
import APIResponse.CoursesResponse;
import APIResponse.ExamResponse;
import APIResponse.LoginResponse;
import APIResponse.MyExamresponse;
import APIResponse.RegistrationResponse;

import APIResponse.UpdateExamResponse;
import butterknife.BindView;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

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
    Call <RegistrationResponse> loginResponseCall(
            @Body LoginEntity loginEntity
    );

    @POST("forgetPassword")
    Call <CommonResponse> forgotPasswordCall(
            @Body ForgotEntity forgotEntity
    );

    // 1 for search and 0 for save
    @POST("courses")
    Call <CoursesResponse> COURSES_RESPONSE_CALL(
            @Body CourseEntity courseEntity
    );

    @POST("getExams")
    Call <ExamResponse> EXAM_RESPONSE_CALL(
            @Body ExamSearchEntity courseEntity
    );
    @POST("addExams")
    Call <UpdateExamResponse> mUpdateExamResponseCall(
            @Body UpdateExamEntity courseEntity
    );
    @Multipart
    @POST("editProfile")
    Call <RegistrationResponse> editProfilCall(
            @Part("name") RequestBody name,
            @Part("dateOfBirth") RequestBody dateOfBirth,
            @Part("university") RequestBody university,
            @Part("userId") RequestBody userId,
            @Part MultipartBody.Part profilePic
    );

    @POST("lastLogin")
    Call<CommonResponse> mLastLoginApi(
            @Body UserIdObj userIdObj
    );
    @POST("hits")
    Call<CommonResponse> mHits(
            @Body HistObj histObj
    );
    @POST("myExams")
    Call<MyExamresponse> mMyExamCall(
           @Body UserIdObj userIdObj
    );

    @Multipart
    @POST("examImages")
    Call <CommonResponse> addImage(
            @Part("examId") RequestBody name,
            @Part MultipartBody.Part images
    );

}
