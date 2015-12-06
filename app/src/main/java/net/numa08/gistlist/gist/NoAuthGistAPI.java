package net.numa08.gistlist.gist;

import com.squareup.okhttp.OkHttpClient;

class NoAuthGistAPI extends GistAPIBase {
    NoAuthGistAPI(OkHttpClient httpClient) {
        super(httpClient);
    }
}
