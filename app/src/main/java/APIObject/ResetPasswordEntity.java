package APIObject;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 2/22/2018.
 */

public class ResetPasswordEntity {
    @SerializedName("userId")
    String userId;
    @SerializedName("oldPassword")
    String oldPassword;
    @SerializedName("newPassword")
    String newPassword;
    @SerializedName("confirmPassword")
    String confirmPassword;

    public ResetPasswordEntity(String userId, String oldPassword, String newPassword, String confirmPassword) {
        this.userId = userId;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
