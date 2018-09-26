package te_compa.mcoe_news_portal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DevInfo extends AppCompatActivity {

    private TextView mTextMessage;
    ImageView colImage;
    private TextView mTextMessage2;
    Toolbar toolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    colImage.setImageResource(R.mipmap.aboutusimage);
                    mTextMessage2.setText(R.string.devIntro);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    colImage.setImageResource(R.mipmap.collegebuilding);
                    mTextMessage2.setText(R.string.colIntro);
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        //toolbar.setTitle("News");

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        colImage = findViewById(R.id.DevImage);

        mTextMessage = (TextView) findViewById(R.id.message);
        mTextMessage2=findViewById(R.id.DevIntro);
        mTextMessage.setText(R.string.title_home);
        colImage.setImageResource(R.mipmap.aboutusimage);
        mTextMessage2.setText(R.string.devIntro);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
