package te_compa.mcoe_news_portal_user;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

public class NewsAdapter extends ArrayAdapter<newsData> {

    public NewsAdapter(Context context, int resource, List<newsData> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater()
                    .inflate(R.layout.item_news, parent,
                            false);
        }

        TextView newsTitle = (TextView) convertView.findViewById(R.id.newsTitleView);
        TextView dateview = (TextView) convertView.findViewById(R.id.dateTextView);
        TextView introview = (TextView) convertView.findViewById(R.id.introTextView);
        newsData news = getItem(position);

        /* TO BE USED FOR ADMIN APP. APPROVED COLOURATION.

        if(news.getApproved()){
            convertView.setBackgroundColor(Color.rgb(125, 214, 115));
        }
        else
        {
            convertView.setBackgroundColor(Color.rgb(221, 119, 119));
        }

         */



        int artLen = news.article.length();
        if(artLen > 25){
            artLen = 25;
        }

                newsTitle.setText(news.newsTitle);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("E, MMM d 'at' h:m a");
        dateview.setText(dateFormatter.format(news.date));
        introview.setText(news.article.substring(0,artLen)+"...");

        return convertView;
    }
}
