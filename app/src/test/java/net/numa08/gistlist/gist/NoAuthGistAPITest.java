package net.numa08.gistlist.gist;

import android.os.Build;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import net.numa08.gistlist.BuildConfig;
import net.numa08.gistlist.realm.models.Gist;
import net.numa08.mock.Mock;
import net.numa08.utils.IO;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP, manifest = Config.NONE)
public class NoAuthGistAPITest {

    Request publicGistRequest;
    Response publicGist;

    @Before
    public void loadPublicGist() throws IOException {
        InputStream input = NoAuthGistAPITest.class.getClassLoader().getResourceAsStream("public_gist.json");
        final String json = IO.load(input);
        input.close();
        publicGistRequest = new Request.Builder()
                .url(GistAPI.PUBLIC_LIST)
                .get()
                .build();
        publicGist = Mock.mockResponse(publicGistRequest, json);
    }

    @Test
    public void getPublic() throws IOException, APIException {
        final OkHttpClient client = Mock.mockClient(publicGist);
        final GistAPI api = new NoAuthGistAPI(client);
        final List<Gist> gists = api.getPublic();

        assertThat(gists.size(), is(30));
    }
}
