package vn.tase.shealthcare.activity.rest;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import vn.tase.shealthcare.utils.AppConstant;

public interface LoginClient {
    @FormUrlEncoded
    @POST(AppConstant.ServerURL+AppConstant.login)
    retrofit2.Call<ResponseBody> loginapi(@Field("email") String username, @Field("password") String password);
}
