package net.numa08.gistlist.load;

import net.numa08.gistlist.gist.APIException;
import net.numa08.gistlist.gist.GistAPI;
import net.numa08.gistlist.gist.GistAPIFactory;
import net.numa08.gistlist.realm.models.Gist;
import net.numa08.gistlist.realm.models.Owner;
import net.numa08.gistlist.realm.providers.RealmProvider;
import net.numa08.gistlist.realm.providers.RealmProviderImpl;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EIntentService;
import org.androidannotations.annotations.ServiceAction;
import org.androidannotations.api.support.app.AbstractIntentService;

import java.util.List;

import io.realm.Realm;

@EIntentService
public class LoadGistService extends AbstractIntentService{
    @Bean(RealmProviderImpl.class)
    RealmProvider realmProvider;

    public LoadGistService() {
        super("LoadGistService");
    }

    @ServiceAction
    void loadPublicGist(long userAccountID) {
        final GistAPI gistAPI = GistAPIFactory.instance();
        final Realm realm = realmProvider.getRealm();
        try {
            final List<Gist> gists = gistAPI.getPublic();
            for (Gist g : gists) {
                g.setUserAccountID(userAccountID);
                g.setPrimaryKey(userAccountID + ":" + g.getId());
                if (g.getOwner() != null) {
                    final Owner owner = g.getOwner();
                    owner.setUserAccountID(userAccountID);
                    owner.setPrimaryKey(userAccountID + ":" + owner.getId());
                }
            }
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(gists);
            realm.commitTransaction();
        } catch (APIException e) {
            e.printStackTrace();
        } finally {
            realm.close();
        }
    }
}
