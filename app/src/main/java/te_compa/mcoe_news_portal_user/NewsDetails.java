package te_compa.mcoe_news_portal_user;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class NewsDetails extends AppCompatActivity {
    newsData news;
    TextView headline;
    TextView articleBody;
    ImageView newsImage;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_news_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        news = (newsData) getIntent().getSerializableExtra("news");
        headline = findViewById(R.id.newsDtitle);
        articleBody = findViewById(R.id.newsDarticle);
        newsImage = findViewById(R.id.newsDimage);
        headline.setText(news.getNewsTitle());
        articleBody.setText(news.getArticle());
        progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.GONE);
        if(news.getImgurl() != null)
        {
            progressBar.setVisibility(View.VISIBLE);
            Glide.with(this).load(news.getImgurl()).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(NewsDetails.this,"Could not Load Image. Please check connection !",Toast.LENGTH_LONG).show();
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    progressBar.setVisibility(View.GONE);
                    return false;
                }
            }).into(newsImage);
            newsImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse(news.getImgurl()),"image/*");
                    startActivity(intent);
                }
            });
        }
        else{
            newsImage.setImageResource(R.drawable.noimage);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}