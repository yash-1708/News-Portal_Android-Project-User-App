package te_compa.mcoe_news_portal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    ListView mNewsListView;
    TextView noNews;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private ChildEventListener mChildEventListener;
    private NewsAdapter newsAdapter;
     ArrayList<newsData> newslist;
    private ProgressBar mProgressBar;

    public MainActivity() {
        newslist = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Submissions.class);
                startActivity(i);
            }
        });
        mNewsListView = (ListView) findViewById(R.id.newslistview);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        mProgressBar.setVisibility(ProgressBar.GONE);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.notices);
        navigationView.getMenu().performIdentifierAction(R.id.notices, 0);
        noNews=findViewById(R.id.nonewstextview);
        noNews.setVisibility(TextView.GONE);
        mNewsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mProgressBar.getVisibility()== ProgressBar.GONE){
                    newsData news=newslist.get(position);
                    //Toast.makeText(MainActivity.this,obj.getName(),Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(MainActivity.this,NewsDetails.class);
                    i.putExtra("newslist", newslist);
                    i.putExtra("position", position);
                    startActivity(i);
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent i = new Intent(this,AboutUs.class);
        newslist = new ArrayList<>();

        if (id == R.id.notices) {
            newslist.clear();
            mNewsListView.setAdapter(newsAdapter);
            toolbar.setTitle(R.string.General);
            mProgressBar.setVisibility(ProgressBar.VISIBLE);
            myRef.child("General Notice").addValueEventListener(readFromDatabase());


        } else if (id == R.id.sports) {
            toolbar.setTitle(R.string.Sports);
            newslist.clear();
            mNewsListView.setAdapter(newsAdapter);
            mProgressBar.setVisibility(ProgressBar.VISIBLE);

            myRef.child("Sports News").addValueEventListener(readFromDatabase());

        } else if (id == R.id.cultural) {
            toolbar.setTitle(R.string.Cultural);
            newslist.clear();
            mNewsListView.setAdapter(newsAdapter);
            mProgressBar.setVisibility(ProgressBar.VISIBLE);
            myRef.child("Cultural Programs").addValueEventListener(readFromDatabase());
        }
        else if (id == R.id.technical) {
            toolbar.setTitle(R.string.Technical);
            newslist.clear();
            mProgressBar.setVisibility(ProgressBar.VISIBLE);
            myRef.child("Technical Programs").addValueEventListener(readFromDatabase());
        } else if (id == R.id.achievements) {
            toolbar.setTitle(R.string.Academic);
            newslist.clear();
            mProgressBar.setVisibility(ProgressBar.VISIBLE);
            myRef.child("Academic Achievements").addValueEventListener(readFromDatabase());
        } else if (id == R.id.AboutUs) {
            startActivity(i);
        } else if (id == R.id.nav_share) {
            Intent shareintent = new Intent(android.content.Intent.ACTION_SEND);
            shareintent.setType("text/plain");
            String shareBodyText = "Your shearing message goes here";
            shareintent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Mcoe News Portal App");
            shareintent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
            startActivity(Intent.createChooser(shareintent, "Choose sharing method"));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private ValueEventListener readFromDatabase() {
        return new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //List newsList = new ArrayList<newsData>();
                newslist.clear();
                for (DataSnapshot newsDataSnapshot : dataSnapshot.getChildren()) {
                    newsData news = newsDataSnapshot.getValue(newsData.class);
                    Log.w("NewsData1", news.getNewsTitle() + news.getArticle());
                    newslist.add(news);
                }
                if(newslist.isEmpty())
                    noNews.setVisibility(TextView.VISIBLE);
                else
                    noNews.setVisibility(TextView.GONE);
                Collections.reverse(newslist);
                mProgressBar.setVisibility(ProgressBar.GONE);
                newsAdapter = new NewsAdapter(MainActivity.this, R.layout.item_news, newslist);
                mNewsListView.setAdapter(newsAdapter);
                // newsAdapter.updateList(newsList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

    }
}
