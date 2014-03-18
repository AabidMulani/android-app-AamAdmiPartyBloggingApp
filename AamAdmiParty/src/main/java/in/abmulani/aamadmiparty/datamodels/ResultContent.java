package in.abmulani.aamadmiparty.datamodels;

import com.google.gson.annotations.Expose;

public class ResultContent {


    @Expose
    private String image_path;
    @Expose
    private String created_on;
    @Expose
    private String id;
    @Expose
    private String title;
    @Expose
    private String content;
    @Expose
    private String subheading;
    private String extra1;
    private String extra2;

    public String getExtra2() {
        return extra2;
    }

    public void setExtra2(String extra2) {
        this.extra2 = extra2;
    }

    public String getPost_id() {
        return id;
    }

    public void setPost_id(String post_id) {
        this.id = post_id;
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
        return extra1;
    }

    public void setExtra1(String extra1) {
        this.extra1 = extra1;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public String getImage_url() {
        return image_path;
    }

    public void setImage_url(String image_url) {
        this.image_path = image_url;
    }
}