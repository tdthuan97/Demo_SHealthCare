package vn.tase.shealthcare.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.tase.shealthcare.R;

public class MainActivity extends AppCompatActivity {

    ViewPager vpBacSi;
    LinearLayout layout_Dots;
    SliderAdapter sliderAdapter;
    Spinner spinnernn;
    ArrayList<String> arrayList;
    ArrayAdapter adapter;
    Button btnNext;

    TextView[] mDots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        sliderAdapter = new SliderAdapter(this);
        vpBacSi.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        vpBacSi.addOnPageChangeListener(viewliser);

        arrayList = new ArrayList<>();
        arrayList.add("VI");
        arrayList.add("EN");

        adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnernn.setAdapter(adapter);

        spinnernn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, arrayList.get(i), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


    }

    public void Anhxa(){
        btnNext=(Button) findViewById(R.id.btnNEXT);
        vpBacSi =(ViewPager) findViewById(R.id.viewpagerBacSi);
        layout_Dots =(LinearLayout) findViewById(R.id.dots);
        spinnernn =(Spinner) findViewById(R.id.spinnerNN);
    }

    public void addDotsIndicator(int position){
        mDots = new TextView[3];
        layout_Dots.removeAllViews();

        for(int i=0;i<mDots.length;i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorPrimary));

            layout_Dots.addView(mDots[i]);
        }

        if(mDots.length>0)
        {
            mDots[position].setTextColor(getResources().getColor(R.color.colorAccent));
        }
    }

    ViewPager.OnPageChangeListener viewliser = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
