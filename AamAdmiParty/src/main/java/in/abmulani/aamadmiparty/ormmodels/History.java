package in.abmulani.aamadmiparty.ormmodels;

import android.content.Context;

import com.orm.SugarRecord;

import in.abmulani.aamadmiparty.datamodels.ResultContent;

public class History extends SugarRecord<History> {

    private String image_url;
    private String created_on;
    private String post_id;
    private String title;
    private String content;
    private String subheading;
    private String extra_a;
    private String extra_b;

    public History(Context context, ResultContent content) {
        super(context);
        this.image_url = content.getImage_url();
        this.created_on = content.getCreated_on();
        this.post_id = content.getPost_id();
        this.title = content.getTitle();
        this.content = content.getContent();
        this.subheading = content.getSubheading();
        this.extra_a = "extra1";
        this.extra_b = "extra2";

    }

    public String getExtra2() {
        return extra_b;
    }

    public void setExtra2(String extra2) {
        this.extra_b = extra2;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubheading() {
        return subheading;
    }

    public void setSubheading(String subheading) {
        this.subheading = subheading;
    }

    public String getExtra1() {
        return extra_a;
    }

    public void setExtra1(String extra1) {
        this.extra_a = extra1;
    }

    public History(Context context) {
        super(context);
    }
}