package net.numa08.gistlist.realm.models;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmObject;

public class File extends RealmObject {
    private String name;
    private long size;
    private String rawUrl;
    private String type;
    private String language;

    public File() {}

    public File(String name, JSONObject json) throws JSONException {
        this.name = name;
        size = json.getLong("size");
        rawUrl = json.getString("raw_url");
        type = json.getString("type");
        language = json.getString("language");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getRawUrl() {
        return rawUrl;
    }

    public void setRawUrl(String rawUrl) {
        this.rawUrl = rawUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
