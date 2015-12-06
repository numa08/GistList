package net.numa08.gistlist.gist;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import net.numa08.gistlist.gist.APIException;
import net.numa08.gistlist.gist.GistAPI;
import net.numa08.gistlist.realm.models.Gist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

abstract class GistAPIBase implements GistAPI {

    private final OkHttpClient httpClient;

    GistAPIBase(OkHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public List<Gist> getPublic() throws APIException {
        final Request request = new Request.Builder()
                .url(PUBLIC_LIST)
                .get()
                .build();
        try {
            final Response response = httpClient.newCall(request).execute();
            final String json = response.body().string();
            final JSONArray jsonArray = new JSONArray(json);
            final List<Gist> gists = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    final JSONObject jsonObject = jsonArray.getJSONObject(i);
                    gists.add(new Gist(jsonObject));
                } catch (JSONException e) {
                    throw new APIException("failed parse json at " + i, e);
                }
            }
            return gists;
        } catch (JSONException e) {
            throw new APIException("failed parse json", e);
        } catch (IOException e) {
            throw new APIException("failed get public gist",e);
        } catch (ParseException e) {
            throw new APIException("failed parse date", e);
        }

    }
}
