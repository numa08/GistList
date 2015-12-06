package net.numa08.gistlist.realm.models;

import android.os.Build;

import net.numa08.gistlist.BuildConfig;
import net.numa08.utils.IO;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP, manifest = Config.NONE)
public class GistTest {

    JSONArray json;

    @Before
    public void loadJson() throws IOException, JSONException {
        final InputStream is = GistTest.class.getClassLoader().getResourceAsStream("public_gist.json");
        final String json = IO.load(is);
        is.close();
        this.json = new JSONArray(json);
    }

    @Test
    public void parse() throws JSONException, ParseException {
        final JSONObject jsonObject = json.getJSONObject(0);
        final Gist gist = new Gist(jsonObject);

        assertThat(gist.getUrl(), is("https://api.github.com/gists/5b113f0f2f724df7267e"));
        assertThat(gist.getForksUrl(), is("https://api.github.com/gists/5b113f0f2f724df7267e/forks"));
        assertThat(gist.getCommitsUrl(), is("https://api.github.com/gists/5b113f0f2f724df7267e/commits"));
        assertThat(gist.getId(), is("5b113f0f2f724df7267e"));
        assertThat(gist.getDescription(), is("impress.jsのinit部分をコードリーディングする"));
        assertThat(gist.isPublic(), is(true));
        assertThat(gist.getComments(), is(0L));
        assertThat(gist.getCommentsUrl(), is("https://api.github.com/gists/5b113f0f2f724df7267e/comments"));
        assertThat(gist.getHtmlUrl(), is("https://gist.github.com/5b113f0f2f724df7267e"));
        assertThat(gist.getGitPullUrl(), is("https://gist.github.com/5b113f0f2f724df7267e.git"));
        assertThat(gist.getGitPushUrl(), is("https://gist.github.com/5b113f0f2f724df7267e.git"));
        final Date createdAt = DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.parse("2015-12-06T03:36:54Z");
        final Date updatedAt = DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.parse("2015-12-06T03:36:54Z");
        assertThat(gist.getCreatedAt(), is(createdAt));
        assertThat(gist.getUpdatedAt(), is(updatedAt));

        assertThat(gist.getFiles().size(), is(3));
        File file = null;
        for (File f : gist.getFiles()) {
            if (f.getName().equals("impress_init_01.js")) {
                file =f ;
            }
        }
        assertThat(file, is(not(nullValue())));
        assert file != null;
        assertThat(file.getSize(), is(880L));
        assertThat(file.getLanguage(), is("JavaScript"));
        assertThat(file.getType(), is("application/javascript"));
        assertThat(file.getRawUrl(), is("https://gist.githubusercontent.com/mitsuru793/5b113f0f2f724df7267e/raw/946a190b2d0070993247c9641caac5fab4ab2674/impress_init_01.js"));

        assertThat(gist.getOwner(), is(not(nullValue())));
        final Owner owner = gist.getOwner();
        assertThat(owner.getLogin(), is("mitsuru793"));
        assertThat(owner.getId(), is(8012508L));
        assertThat(owner.getAvatarUrl(), is("https://avatars.githubusercontent.com/u/8012508?v=3"));
    }
}
