package vn.tase.shealthcare.activity.rest;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.tase.shealthcare.activity.MainActivity;
import vn.tase.shealthcare.listeners.OnGetResultLogin;
import vn.tase.shealthcare.utils.AppConstant;

public class LoginClientIml {
    private static LoginClientIml instance;
    private LoginClient client;
    private Activity subLogin;
    private Retrofit retrofit;

    public static LoginClientIml getInstance(Activity at) {
        if (instance == null) {
            instance = new LoginClientIml(at);
        }
        return instance;
    }

    public LoginClientIml(Activity ac) {
        retrofit = new Retrofit.Builder().baseUrl(AppConstant.ServerURL).build();
        this.subLogin = ac;
    }

    public void login(String username, String password, OnGetResultLogin onGetResultLogin) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header(AppConstant.Authorization,AppConstant.Base)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });
        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl(AppConstant.ServerURL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit=builder.build();
        LoginClient client =retrofit.create(LoginClient.class);
        retrofit2.Call<ResponseBody> ret=client.loginapi(username,password);
        ret.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                try {
                    String s = response.body().string();
                    //server tra thong tin ve
                    JSONObject jsonObject = new JSONObject(s);
                    int status = jsonObject.getInt("status");
                    if (status == 200) {
                        Intent intent = new Intent(subLogin, MainActivity.class);
                        subLogin.startActivity(intent);
                        //Toast.makeText(subLogin, status, Toast.LENGTH_SHORT).show();

                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {

            }
        });

    }
}
