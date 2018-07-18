package vn.tase.shealthcare.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import vn.tase.shealthcare.R;
import vn.tase.shealthcare.fragment.ChatFragment;
import vn.tase.shealthcare.fragment.DashboardFragment;
import vn.tase.shealthcare.fragment.MyProfileFragment;
import vn.tase.shealthcare.fragment.ScheduleFragment;

public class MenuActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new DashboardFragment();
                    break;
                case R.id.navigation_chat:
                    fragment = new ChatFragment();

                    break;
                case R.id.navigation_schedule:
                    fragment = new ScheduleFragment();

                    break;
                case R.id.navigation_profile:
                    fragment = new MyProfileFragment();
                    break;

            }
            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.main_container, fragment).commit();
            return true;        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        fragmentManager = getSupportFragmentManager();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

}
