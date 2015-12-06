package net.numa08.gistlist.gist;

import com.squareup.okhttp.OkHttpClient;

public class GistAPIFactory {

    public static GistAPI instance() {
        return new NoAuthGistAPI(new OkHttpClient());
    }

}
