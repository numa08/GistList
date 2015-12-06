package net.numa08.gistlist.realm.models;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Owner extends RealmObject {
    private long userAccountID;

    @PrimaryKey
    private String primaryKey;

    private String login;
    private long id;
    private String avatarUrl;

    public Owner() {}

    public Owner(JSONObject json) throws JSONException {
        login = json.getString("login");
        id = json.getLong("id");
        avatarUrl = json.getString("avatar_url");
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
