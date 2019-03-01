package te_compa.mcoe_news_portal_user;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    ImageView logoimage;
    TextView logotext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        logoimage = findViewById(R.id.splashscreenimage);
        logotext = findViewById(R.id.textView2);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.left);
        Animation animation1 = AnimationUtils.loadAnimation(this,R.anim.right);
        logotext.startAnimation(animation1);
        logoimage.startAnimation(animation);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent signinIntent = new Intent(SplashScreen.this,GoogleSignIn.class);
                startActivity(signinIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
