package vn.tase.shealthcare.activity.presenter;

import vn.tase.shealthcare.activity.LoginSubActivity;
import vn.tase.shealthcare.activity.rest.LoginClientIml;
import vn.tase.shealthcare.listeners.OnGetResultLogin;

public class LoginPresenter {
    private LoginSubActivity mActivity;
    private LoginClientIml client;

    public LoginPresenter(LoginSubActivity mainActivity) {
        client = LoginClientIml.getInstance(mainActivity);
        mActivity = mainActivity;
    }
    public void login(String username, String password) {
        client.login(username,password, new OnGetResultLogin() {
            @Override
            public void onSuccess(String s) {

            }

            @Override
            public void onFalied(String s) {

            }
        });
    }
}
