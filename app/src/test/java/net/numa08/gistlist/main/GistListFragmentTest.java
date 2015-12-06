package net.numa08.gistlist.main;

import android.os.Build;

import net.numa08.gistlist.BuildConfig;
import net.numa08.gistlist.realm.models.Gist;
import net.numa08.gistlist.test.TestActivity;
import net.numa08.gistlist.widget.GistView;
import net.numa08.test.CustomRobolectricTestRunner;
import net.numa08.utils.IO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import io.realm.MockRealm;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(CustomRobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP, manifest = "src/main/AndroidManifest.xml")
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*", "org.json.*"})
@PrepareForTest({Realm.class, RealmResults.class})
public class GistListFragmentTest {

    @Rule
    public PowerMockRule rule = new PowerMockRule();

    Realm testRealm;
    GistListFragment fragment;
    List<Gist> gists;

    @Before
    public void initRealm() throws IOException, JSONException, ParseException {
        testRealm = MockRealm.mockRealm();
        final InputStream is = GistListFragmentTest.class.getClassLoader().getResourceAsStream("public_gist.json");
        final String json = IO.load(is);
        final JSONArray jsonArray = new JSONArray(json);
        gists = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            final JSONObject jsonObject = jsonArray.getJSONObject(i);
            final Gist gist = new Gist(jsonObject);
            gists.add(gist);
        }
        final RealmResults results = MockRealm.mockRealmResult(testRealm, gists);
        when(testRealm.allObjects((Class<RealmObject>) any())).thenReturn(results);

        fragment = GistListFragment_.builder().build();
        fragment.realm = testRealm;
    }

    @Test
    public void loadGists(){
        final ActivityController<TestActivity> activityController= Robolectric.buildActivity(TestActivity.class).create().start();
        activityController.get().getSupportFragmentManager()
                .beginTransaction()
                .add(fragment, "gist_list")
                .commit();
        activityController.resume();

        assertThat(fragment.getListView(), is(not(nullValue())));
        assertThat(fragment.getListAdapter(), is(not(nullValue())));
        final GistListAdapter adapter = (GistListAdapter) fragment.getListAdapter();
        for (int i = 0; i < gists.size(); i++) {
            final GistView view = (GistView) adapter.getView(i, null, fragment.getListView());
            assertThat(view, is(not(nullValue())));
            final Gist g = view.getGist();
            assertThat(g.getId(), is(gists.get(i).getId()));
        }
    }
}
