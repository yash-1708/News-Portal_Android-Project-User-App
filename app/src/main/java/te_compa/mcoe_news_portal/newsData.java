package te_compa.mcoe_news_portal;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class newsData implements Serializable {
    String newsTitle;
    String article;
    boolean approved;
    Date date;
    String imgurl;

    public newsData(){

    }

    public newsData(String title, String art, boolean approved1, String imageurl) {
        newsTitle = title;
        article = art;
        approved = approved1;
        this.date = Calendar.getInstance().getTime();
        imgurl = imageurl;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String art) {
        article = art;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String name) {
        newsTitle = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public boolean getApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
