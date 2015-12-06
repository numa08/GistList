package net.numa08.gistlist.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import net.numa08.gistlist.R;
import net.numa08.gistlist.load.LoadGistService_;
import net.numa08.gistlist.realm.models.Gist;
import net.numa08.gistlist.realm.providers.RealmProvider;
import net.numa08.gistlist.realm.providers.RealmProviderImpl;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

import io.realm.Realm;
import io.realm.RealmResults;

@EFragment(R.layout.fragment_gist_list)
public class GistListFragment extends ListFragment {
    @Bean(RealmProviderImpl.class)
    RealmProvider realmProvider;

    Realm realm;
    Picasso picasso;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        picasso = Picasso.with(getContext());
        LoadGistService_.intent(getContext()).loadPublicGist(0).start();
    }

    @Override
    public void onDestroy() {
        if (realm != null) {
            realm.close();
        }
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final RealmResults<Gist> gists = getRealm().allObjects(Gist.class);
        final GistListAdapter adapter = new GistListAdapter(getContext(), gists, picasso);
        setListAdapter(adapter);
    }

    private Realm getRealm() {
        if (realm == null) {
            realm = realmProvider.getRealm();
        }
        return realm;
    }
}
