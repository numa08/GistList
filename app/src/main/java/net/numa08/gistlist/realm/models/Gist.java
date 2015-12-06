package net.numa08.gistlist.realm.models;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Gist extends RealmObject {
    private long userAccountID;
    @PrimaryKey
    private String primaryKey;


    private String url;
    private String forksUrl;
    private String commitsUrl;
    private String id;
    private String description;
    private boolean isPublic;
    private Owner owner;
    private RealmList<File> files;
    private long comments;
    private String commentsUrl;
    private String htmlUrl;
    private String gitPullUrl;
    private String gitPushUrl;
    private Date createdAt;
    private Date updatedAt;

    public Gist() {}

    public Gist(JSONObject json) throws JSONException, ParseException {
        url = json.getString("url");
        forksUrl = json.getString("forks_url");
        commitsUrl = json.getString("commits_url");
        id = json.getString("id");
        description = json.getString("description");
        isPublic = json.getBoolean("public");
        if (json.has("owner")) {
            owner = new Owner(json.getJSONObject("owner"));
        }
        files = new RealmList<>();
        final JSONObject files = json.getJSONObject("files");
        final Iterator<String> keys = files.keys();
        while(keys.hasNext()) {
            final String key = keys.next();
            this.files.add(new File(key, files.getJSONObject(key)));
        }
        comments = json.getLong("comments");
        commentsUrl = json.getString("comments_url");
        htmlUrl = json.getString("html_url");
        gitPullUrl = json.getString("git_pull_url");
        gitPushUrl = json.getString("git_push_url");
        createdAt = DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.parse(json.getString("created_at"));
        updatedAt = DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.parse(json.getString("created_at"));
    }

    public long getUserAccountID() {
        return userAccountID;
    }

    public void setUserAccountID(long userAccountID) {
        this.userAccountID = userAccountID;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getForksUrl() {
        return forksUrl;
    }

    public void setForksUrl(String forksUrl) {
        this.forksUrl = forksUrl;
    }

    public String getCommitsUrl() {
        return commitsUrl;
    }

    public void setCommitsUrl(String commitsUrl) {
        this.commitsUrl = commitsUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        this.isPublic = aPublic;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public RealmList<File> getFiles() {
        return files;
    }

    public void setFiles(RealmList<File> files) {
        this.files = files;
    }

    public long getComments() {
        return comments;
    }

    public void setComments(long comments) {
        this.comments = comments;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getGitPullUrl() {
        return gitPullUrl;
    }

    public void setGitPullUrl(String gitPullUrl) {
        this.gitPullUrl = gitPullUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getGitPushUrl() {
        return gitPushUrl;
    }

    public void setGitPushUrl(String gitPushUrl) {
        this.gitPushUrl = gitPushUrl;
    }
}
