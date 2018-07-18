package vn.tase.shealthcare.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import vn.tase.shealthcare.R;
import vn.tase.shealthcare.activity.presenter.LoginPresenter;

public class LoginSubActivity extends AppCompatActivity {

    private LoginPresenter presenter;
    EditText email,pass;
    Button btnDangNhapAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sub);
        addControls();
        presenter=new LoginPresenter(this);

        btnDangNhapAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(presenter!=null)
                {
                    String username = email.getText().toString();
                    String password= pass.getText().toString();
                    presenter.login(username,password);
                }
            }
        });
    }
    private void addControls(){
        email = findViewById(R.id.edtEmail);
        pass = findViewById(R.id.edtPass);
        btnDangNhapAPI = findViewById(R.id.btnDangNhapAPI);
    }
}
