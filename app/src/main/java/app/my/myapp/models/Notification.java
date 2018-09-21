package app.my.myapp.models;

import com.google.gson.annotations.SerializedName;

public class Notification {

    @SerializedName("_id")
    private String _id;
    @SerializedName("title")
    private String title;
    @SerializedName("notification")
    private String notification;
    @SerializedName("type")
    private String type;
    @SerializedName("is_read")
    private Boolean is_read;
    @SerializedName("created_at")
    private Long created_at;
    @SerializedName("update_at")
    private Long update_at;

    public Notification( String title, String notification, String type, Boolean is_read, Long created_at, Long update_at) {
        this.title = title;
        this.notification = notification;
        this.type = type;
        this.is_read = is_read;
        this.created_at = created_at;
        this.update_at = update_at;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIs_read() {
        return is_read;
    }

    public void setIs_read(Boolean is_read) {
        this.is_read = is_read;
    }

    public Long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Long created_at) {
        this.created_at = created_at;
    }

    public Long getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Long update_at) {
        this.update_at = update_at;
    }

    @Override
    public String toString() {
        return "Notification{" +
                ", title='" + title + '\'' +
                ", notification='" + notification + '\'' +
                ", type='" + type + '\'' +
                ", is_read=" + is_read +
                ", created_at=" + created_at +
                ", update_at=" + update_at +
                '}';
    }
}
